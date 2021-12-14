import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Music } from './../model/music';
import { Album } from './../model/album';
import { Artist } from './../model/artist';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AlbumService {
  private static URL: string = 'http://localhost:8080/music/api/album';

  constructor(private http: HttpClient) {}

  private get httpHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorization: 'Basic ' + sessionStorage.getItem('token'),
      'Content-Type': 'application/json',
    });
  }

  private get httpHeadersFile(): HttpHeaders {
    return new HttpHeaders({
      Authorization: 'Basic ' + sessionStorage.getItem('token'),
    });
  }

  // Methode qui permet de recuperer tous les albums
  public allAlbum(): Observable<any[]> {
    return this.http.get<any[]>(AlbumService.URL);
  }

  // Methode qui permet de recuperer un album par son ID
  public byId(id: number): Observable<any> {
    return this.http.get<Album[]>(`${AlbumService.URL}/${id}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer un album par son nom
  public byName(name: string): Observable<Album[]> {
    return this.http.get<Album[]>(`${AlbumService.URL}/name/${name}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer un/des albums grace au nom de l artiste
  public byArtist(name: string): Observable<any[]> {
    return this.http.get<any[]>(`${AlbumService.URL}/artist/${name}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer un/des albums grace au nom de la musique
  public byMusic(title: string): Observable<Album> {
    return this.http.get<Album>(`${AlbumService.URL}/music/${title}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer un/des albums grace au genre
  public byGenre(genre: string): Observable<Album[]> {
    return this.http.get<Album[]>(`${AlbumService.URL}/genre/${genre}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de supprimer un album par son id
  public delete(id: number): Observable<any> {
    return this.http.delete<Album[]>(`${AlbumService.URL}/${id}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de creer un album
  public insert(album: Album): Observable<Album> {
    const a = {
      name: album.name,
      year: album.year,
      cover: album.cover,
    };
    return this.http.post<Album>(AlbumService.URL, a, {
      headers: this.httpHeaders,
    });
  }

  public addCover(cover: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', cover);
    return this.http.post(`http://localhost:8080/music/album`, formData, {
      headers: this.httpHeadersFile,
    });
  }

  // Methode pour la mise a jour d un album dans la BDD
  public update(album: Album): Observable<Album> {
    return this.http.put<Album>(`${AlbumService.URL}/${album.id}`, album, {
      headers: this.httpHeaders,
    });
  }

  // Methode pour ajouter une musique a l album
  public addMusic(music: Music, album: Album) {
    album.musics?.push(music);
    this.update(album);
  }

  // Methode pour ajouter un artiste a l album
  public addArtist(artist: Artist, album: Album) {
    album.artists?.push(artist);
    this.update(album);
  }
}
