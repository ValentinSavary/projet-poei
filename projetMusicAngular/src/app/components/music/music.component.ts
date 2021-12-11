import { Component, OnInit } from '@angular/core';

import { Music } from './../../model/music';
import { MusicService } from './../../services/music.service';

@Component({
  selector: 'app-music',
  templateUrl: './music.component.html',
  styleUrls: ['./music.component.css'],
})
export class MusicComponent implements OnInit {
  musics: Music[] = [];

  constructor(private musicService: MusicService) {}

  ngOnInit(): void {
    this.initMusics();
  }

  initMusics() {
    this.musicService.allMusic().subscribe((result: any[]) => {
      this.musics = [];
      for (let value of result) {
        this.musics.push(
          new Music(
            value['id'],
            value['title'],
            value['duration'],
            value['musicFile'],
            value['genre']
          )
        );
      }
    });
  }

  delete(id: number | undefined) {
    if (!!id) {
      this.musicService.delete(id).subscribe((result) => {
        this.initMusics();
      });
    }
  }
}
