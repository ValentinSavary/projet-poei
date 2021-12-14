import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Artist } from './../model/artist';
import { Album } from './../model/album';

@Injectable({
  providedIn: 'root',
})
export class ArtistService {
  private static URL: string = 'http://localhost:8080/music/api/artist';

  constructor(private http: HttpClient) {}

  private get httpHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorization: 'Basic ' + sessionStorage.getItem('token'),
      'Content-Type': 'application/json',
    });
  }

  // Methode qui permet de recuperer tous les artistes
  public allArtist(): Observable<any[]> {
    return this.http.get<any[]>(ArtistService.URL);
  }

  // Methode qui permet de recuperer un artiste par son ID
  public byId(id: number): Observable<any> {
    return this.http.get<Artist[]>(`${ArtistService.URL}/${id}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer un artiste par son nom
  public byName(name: string): Observable<Artist[]> {
    return this.http.get<Artist[]>(`${ArtistService.URL}/name/${name}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer un/des artistes grace au nom de l album
  public byAlbum(name: string): Observable<Artist[]> {
    return this.http.get<Artist[]>(`${ArtistService.URL}/album/${name}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer un/des artistes grace au nom de la musique
  public byMusic(title: string): Observable<Artist> {
    return this.http.get<Artist>(`${ArtistService.URL}/music/${title}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer un/des artistes grace au genre
  public byGenre(genre: string): Observable<Artist> {
    return this.http.get<Artist>(`${ArtistService.URL}/genre/${genre}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de supprimer un artiste par son id
  public delete(id: number): Observable<any> {
    return this.http.delete<Artist[]>(`${ArtistService.URL}/${id}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de creer un artiste
  public insert(artist: Artist): Observable<Artist> {
    const a = {
      name: artist.name,
      country: artist.country,
    };
    return this.http.post<Artist>(ArtistService.URL, a, {
      headers: this.httpHeaders,
    });
  }

  // Methode pour la mise a jour d un artiste dans la BDD
  public update(artist: Artist): Observable<Artist> {
    return this.http.put<Artist>(`${ArtistService.URL}/${artist.id}`, artist, {
      headers: this.httpHeaders,
    });
  }

  // Methode pour ajouter un album a l artiste
  public addAlbum(album: Album, artist: Artist) {
    artist.albums?.push(album);
    this.update(artist);
  }
}
