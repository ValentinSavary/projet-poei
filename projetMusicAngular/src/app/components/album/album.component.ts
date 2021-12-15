import { FileUploader } from 'ng2-file-upload';
import { Album } from './../../model/album';
import { Music } from './../../model/music';
import { AlbumService } from './../../services/album.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-album',
  templateUrl: './album.component.html',
  styleUrls: ['./album.component.css'],
})
export class AlbumComponent implements OnInit {
  albums: Album[] = [];
  chaine: string = '';

  constructor(
    private albumService: AlbumService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      console.log(params['album']);
      if (!!params['album']) {
        this.albumService.byArtist(params['album']).subscribe((result) => {
          console.log(result);
          this.albums = [];
          for (let value of result) {
            this.albums.push(
              new Album(
                value['id'],
                value['name'],
                value['cover'],
                value['year'],
                value['musics[]'],
                value['artists[]']
              )
            );
          }
        });
      } else {
        this.initAlbums();
      }
    });
  }

  delete(id: number | undefined) {
    if (!!id) {
      this.albumService.delete(id).subscribe((result) => {
        this.initAlbums();
      });
    }
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

  get logged(): boolean {
    return !!sessionStorage.getItem('token') ? true : false;
  }

  filtre(): Album[] {
    return this.albums.filter((album) => {
      if (album.name != undefined) {
        return (
          album.name.toLowerCase().indexOf(this.chaine.toLowerCase()) !== -1
        );
      }
      return '';
    });
  }
}
