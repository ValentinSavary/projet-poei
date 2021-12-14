import { Genre } from './../../../model/genre';
import { Observable } from 'rxjs';
import { AbstractControl, AsyncValidatorFn } from '@angular/forms';
import { ValidationErrors } from '@angular/forms';
import { debounceTime, map } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { MusicService } from './../../../services/music.service';
import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder,
} from '@angular/forms';
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
    private fb: FormBuilder,
    private musicService: MusicService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.form = this.fb.group({
      titleControl: this.fb.control('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{1,})*$/),
        Validators.maxLength(50),
      ]),
      durationControl: this.fb.control('', [
        Validators.pattern(/^[0-9]{1,4}$/),
        Validators.maxLength(4),
      ]),
      /*       musicFileControl: this.fb.control('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{1,})*$/),
      ]), */
      genreControl: this.fb.control('', []),
    });
  }

  ngOnInit(): void {}
  /*   this.activatedRoute.params.subscribe((params) => {
    if (!!params['music']) {
      this.musicService.byId(params['music']).subscribe((music) => {
        this.music = music;
      });
    }
  });
} */

  save() {
    this.musicService
      .insert(
        new Music(
          undefined,
          this.form.controls['titleControl'].value,
          this.form.controls['durationControl'].value,
          /*           this.form.controls['musicFileControl'].value, */
          this.form.controls['genreControl'].value
        )
      )
      .subscribe((music) => {
        this.router.navigate(['/music']);
      });
  }
}

/*   save(form: any) {
    if (!!this.music.id) {
      this.musicService.update(this.music).subscribe((result) => {
        this.router.navigate(['/music']);
      });
    } else {
      this.musicService.insert(this.music).subscribe((result) => {
        this.router.navigate(['/music']);
      });
    }
  } */
