import { Album } from './../model/album';
import { Music } from './../model/music';
import { Playlist } from './../model/playlist';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class PlaylistService {
  private static URL: string = 'http://localhost:8080/music/api/playlist';

  constructor(private http: HttpClient) {}

  private get httpHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorization: 'Basic ' + sessionStorage.getItem('token'),
      'Content-Type': 'application/json',
    });
  }

  public byId(id: number): Observable<any> {
    return this.http.get<Playlist[]>(`${PlaylistService.URL}/${id}`, {
      headers: this.httpHeaders,
    });
  }

  public byName(name: string): Observable<Playlist> {
    return this.http.get<Playlist>(`${PlaylistService.URL}/name/${name}`, {
      headers: this.httpHeaders,
    });
  }

  public byUser(username: string): Observable<any[]> {
    return this.http.get<any[]>(`${PlaylistService.URL}/user/${username}`, {
      headers: this.httpHeaders,
    });
  }

  public insert(playlist: Playlist): Observable<Playlist> {
    const a = {
      name: playlist.name,
      typePrivate: playlist.typePrivate,
    };
    return this.http.post<Playlist>(PlaylistService.URL, a, {
      headers: this.httpHeaders,
    });
  }

  public update(playlist: Playlist): Observable<Playlist> {
    return this.http.put<Playlist>(
      `${PlaylistService.URL}/${playlist.id}`,
      playlist,
      {
        headers: this.httpHeaders,
      }
    );
  }

  // Methode qui permet de supprimer une playlist par son id
  public delete(id: number): Observable<any> {
    return this.http.delete<Playlist[]>(`${PlaylistService.URL}/${id}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet d ajouter une musique dans une playlist
  public addMusic(music: Music, playlist: Playlist) {
    playlist.musics?.push(music);
    this.update(playlist);
  }

  // Methode qui permet d ajouter un album dans une playlist
  public addAlbum(album: Album, playlist: Playlist) {
    album.musics?.forEach((result) => {
      playlist.musics?.push(result);
    });
    this.update(playlist);
  }

  // Methode qui permet d enlever une musique dans une playlist
  public removeMusic(music: Music, playlist: Playlist) {
    playlist.musics?.splice(playlist.musics?.indexOf(music));
    this.update(playlist);
  }
}
