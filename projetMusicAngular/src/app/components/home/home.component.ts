import { Music } from './../../model/music';
import { MusicService } from './../../services/music.service';
import { AlbumService } from './../../services/album.service';
import { ArtistService } from './../../services/artist.service';
import { Album } from './../../model/album';
import { Artist } from './../../model/artist';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  chaine: string = '';
  artists: Artist[] = [];
  albums: Album[] = [];
  musics: Music[] = [];
  albumName: string = '';

  constructor(
    private artistService: ArtistService,
    private albumService: AlbumService,
    private musicService: MusicService
  ) {}

  ngOnInit(): void {}

  search(text: string) {
    this.chaine = text;
    this.searchArtist(text);
    this.searchAlbum(text);
    this.searchMusic(text);
  }

  searchArtist(name: string) {
    this.artistService.byName(name).subscribe((result: Artist[]) => {
      this.artists = [];
      for (let value of result) {
        this.artists.push(
          new Artist(value['id'], value['name'], value['country'])
        );
      }
    });
  }
  searchAlbum(name: string) {
    this.albumService.byName(name).subscribe((result: Album[]) => {
      this.albums = [];
      for (let value of result) {
        this.albums.push(
          new Album(
            value['id'],
            value['name'],
            value['year'],
            value['cover'],
            value['musics'],
            value['artists']
          )
        );
      }
    });
  }

  searchMusic(title: string) {
    this.musicService.byTitle(title).subscribe((result: Music[]) => {
      this.musics = [];
      for (let value of result) {
        this.musics.push(
          new Music(
            value['id'],
            value['title'],
            value['duration'],
            value['musicFile'],
            value['genre'],
            value['album']
          )
        );
      }
    });
  }
}
