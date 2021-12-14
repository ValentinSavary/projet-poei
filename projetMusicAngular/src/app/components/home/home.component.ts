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

  constructor() {}

  ngOnInit(): void {}

  search(text: string) {
    this.chaine = text;
    this.searchArtist;
  }

  searchArtist(): Artist[] {
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
