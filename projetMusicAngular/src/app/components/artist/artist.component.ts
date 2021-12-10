import { Component, OnInit } from '@angular/core';

import { Artist } from './../../model/artist';
import { ArtistService } from './../../services/artist.service';

@Component({
  selector: 'app-artist',
  templateUrl: './artist.component.html',
  styleUrls: ['./artist.component.css'],
})
export class ArtistComponent implements OnInit {
  artists: Artist[] = [];

  constructor(private artistService: ArtistService) {}

  ngOnInit(): void {
    this.initArtists();
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
}
