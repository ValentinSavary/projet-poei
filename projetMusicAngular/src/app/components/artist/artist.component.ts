import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';

import { Artist } from './../../model/artist';
import { ArtistService } from './../../services/artist.service';

@Component({
  selector: 'app-artist',
  templateUrl: './artist.component.html',
  styleUrls: ['./artist.component.css'],
})
export class ArtistComponent implements OnInit, OnChanges {
  artists: Artist[] = [];
  chaine: string = '';

  constructor(private artistService: ArtistService) {}

  ngOnInit(): void {
    this.initArtists();
    console.log('on est la');
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log('ng change');
    this.initArtists();
  }

  delete(id: number | undefined) {
    if (!!id) {
      this.artistService.delete(id).subscribe((result) => {
        this.initArtists();
      });
    }
  }

  initArtists() {
    this.artistService.allArtist().subscribe((result: any[]) => {
      this.artists = [];
      for (let value of result) {
        this.artists.push(
          new Artist(
            value['id'],
            value['name'],
            value['country'],
            value['albums[]']
          )
        );
      }
    });
  }

  filtre(): Artist[] {
    return this.artists.filter((artist) => {
      if (artist.name != undefined) {
        return (
          artist.name.toLowerCase().indexOf(this.chaine.toLowerCase()) !== -1
        );
      }
      return '';
    });
  }
}
