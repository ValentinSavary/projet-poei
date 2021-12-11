import { ActivatedRoute, Router } from '@angular/router';
import { Genre } from './../../../model/genre';
import { MusicService } from './../../../services/music.service';
import { Component, OnInit } from '@angular/core';
import { Music } from 'src/app/model/music';

@Component({
  selector: 'app-form-music',
  templateUrl: './form-music.component.html',
  styleUrls: ['./form-music.component.css'],
})
export class FormMusicComponent implements OnInit {
  music: Music = new Music();
  genres = Genre;

  constructor(
    private activatedRoute: ActivatedRoute,
    private musicService: MusicService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      console.log(params['music']);
      if (!!params['music']) {
        this.musicService.byId(params['music']).subscribe((music) => {
          this.music = music;
        });
      }
    });
  }

  save(form: any) {
    if (!!this.music.id) {
      this.musicService.update(this.music).subscribe((result) => {
        this.goList();
      });
    } else {
      this.musicService.insert(this.music).subscribe((result) => {
        this.goList();
      });
    }
  }
  goList() {
    this.router.navigate(['/music']);
  }
}
