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

  constructor(private playlistService: PlaylistService) {}

  ngOnInit(): void {}
}
