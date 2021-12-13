import { Component, OnInit } from '@angular/core';
import { Playlist } from './../../model/playlist';
import { PlaylistService } from './../../services/playlist.service';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css'],
})
export class PlaylistComponent implements OnInit {
  playlists: Playlist[] = [];
  login: string = '';

  constructor(private playlistService: PlaylistService) {}

  ngOnInit(): void {}

  initPlaylists() {
    //Recuperer la session de l utilisateur
    const userJson = sessionStorage.getItem('login');
    this.login = userJson !== null ? userJson : '';

    this.playlistService.byUser(this.login).subscribe((result: any[]) => {
      this.playlists = [];
      for (let value of result) {
        this.playlists.push(
          new Playlist(value['id'], value['name'], value['typePrivate'])
        );
      }
    });
  }

  delete(id: number | undefined) {
    if (!!id) {
      this.playlistService.delete(id).subscribe((result) => {
        this.initPlaylists();
      });
    }
  }
}
