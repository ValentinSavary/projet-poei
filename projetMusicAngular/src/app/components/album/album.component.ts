import { Album } from './../../model/album';
import { AlbumService } from './../../services/album.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-album',
  templateUrl: './album.component.html',
  styleUrls: ['./album.component.css'],
})
export class AlbumComponent implements OnInit {
  albums: Album[] = [];
  constructor(private albumService: AlbumService) {}

  ngOnInit(): void {
    this.initAlbums();
  }

  initAlbums() {
    this.albumService.allAlbum().subscribe((result: any[]) => {
      this.albums = [];
      for (let value of result) {
        this.albums.push(
          new Album(
            value['id'],
            value['name'],
            value['year'],
            value['cover'],
            value['musics[]'],
            value['artists[]']
          )
        );
        console.log(result);
      }
    });
  }
}
