import { Music } from './../model/music';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class MusicService {
  private static URL: string = 'http://localhost:8080/music/api/music';

  constructor(private http: HttpClient) {}

  private get httpHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorization: 'Basic ' + sessionStorage.getItem('token'),
      'Content-Type': 'application/json',
    });
  }

  // Methode qui permet de recuperer toutes les musiques
  public allMusic(): Observable<any[]> {
    return this.http.get<any[]>(MusicService.URL);
  }

  // Methode qui permet de recuperer une musique par son ID
  public byId(id: number): Observable<Music> {
    return this.http.get<Music>(`${MusicService.URL}/${id}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer une musique par son nom
  public byTitle(title: string): Observable<Music> {
    return this.http.get<Music>(`${MusicService.URL}/title/${title}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer des musiques via le nom de l artiste
  public byArtist(name: string): Observable<any[]> {
    return this.http.get<any[]>(`${MusicService.URL}/artist/${name}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer des musiques grace au nom de l album
  public byAlbum(name: string): Observable<any[]> {
    return this.http.get<any[]>(`${MusicService.URL}/album/${name}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer des musiques grace au nom d une playlist
  public byPlaylist(name: string): Observable<any[]> {
    return this.http.get<any[]>(`${MusicService.URL}/playlist/${name}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer des musiques grace au genre
  public byGenre(genre: string): Observable<Music> {
    return this.http.get<Music>(`${MusicService.URL}/genre/${genre}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de supprimer une musique par son id
  public delete(id: number): Observable<any> {
    return this.http.delete<Music[]>(`${MusicService.URL}/${id}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de creer une musique
  public insert(music: Music): Observable<Music> {
    const a = {
      title: music.title,
      duration: music.duration,
      musicFile: null,
      genre: music.genre,
    };
    return this.http.post<Music>(MusicService.URL, a, {
      headers: this.httpHeaders,
    });
  }

  // Methode pour la mise a jour d une musique dans la BDD
  public update(music: Music): Observable<Music> {
    return this.http.put<Music>(`${MusicService.URL}/${music.id}`, music, {
      headers: this.httpHeaders,
    });
  }
}
