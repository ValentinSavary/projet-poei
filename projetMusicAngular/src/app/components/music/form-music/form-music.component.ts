import { ActivatedRoute, Router } from '@angular/router';
import { Genre } from './../../../model/genre';
import { MusicService } from './../../../services/music.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Music } from 'src/app/model/music';

@Component({
  selector: 'app-form-music',
  templateUrl: './form-music.component.html',
  styleUrls: ['./form-music.component.css'],
})
export class FormMusicComponent implements OnInit {
  genres = Genre;
  form: FormGroup;
  constructor(
    private musicService: MusicService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.form = new FormGroup({
      title: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{1,})*$/),
        Validators.maxLength(200),
      ]),
      duration: new FormControl('', [Validators.required]),

      genre: new FormControl('', [Validators.required]),

      musicFile: new FormControl('', [Validators.required]),
    });
  }
  ngOnInit(): void {}

  save() {
    this.musicService
      .insert(
        new Music(
          undefined,
          this.form.controls['title'].value,
          this.form.controls['duration'].value,
          this.form.controls['genre'].value,
          this.form.controls['musicFile'].value
        )
      )
      .subscribe((music) => {
        console.log(music);
      });
    this.router.navigate(['/music']);
  }
}
