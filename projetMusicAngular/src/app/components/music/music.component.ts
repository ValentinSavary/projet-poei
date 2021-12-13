import { ArtistService } from './../../services/artist.service';
import { AlbumService } from './../../services/album.service';
import { Album } from './../../model/album';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Music } from './../../model/music';
import { MusicService } from './../../services/music.service';

@Component({
  selector: 'app-music',
  templateUrl: './music.component.html',
  styleUrls: ['./music.component.css'],
})
export class MusicComponent implements OnInit {
  musics: Music[] = [];
  chaine: string = '';

  constructor(
    private musicService: MusicService,
    private albumService: AlbumService,
    private artistService: ArtistService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      console.log(params['music']);
      if (!!params['music']) {
        this.listMusicByAlbum(params['music']);
      } else if (!!params['name']) {
        this.listMusicByPlaylist(params['name']);
      } else {
        this.initMusics();
      }
    });
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

  // Cette methode retourne toutes les musiques d un album
  listMusicByAlbum(username: string) {
    this.musicService.byAlbum(username).subscribe((result) => {
      console.log(result);
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

  // Cette methode retourne toutes les musiques d une playlist
  listMusicByPlaylist(name: string) {
    this.musicService.byPlaylist(name).subscribe((result) => {
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

  // path(id: number): string {
  //   this.musicService.byId(id).subscribe((music) => {
  //     this.artistService.byMusic(
  //       JSON.stringify(
  //         this.musicService.byId(id).subscribe((music) => {
  //           music.title;
  //         })
  //       )
  //     );

  //     music.title;
  //   });
  //   return 'ff';
  // }

  /*   filtre(): Music[] {
    return this.musics.filter((music) => {
      return music.name.indexOf(this.chaine) !== -1;
    });
  } */
}
