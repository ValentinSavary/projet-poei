import { Observable } from 'rxjs';
import { AbstractControl, AsyncValidatorFn } from '@angular/forms';
import { ValidationErrors } from '@angular/forms';
import { debounceTime, map } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { PlaylistService } from './../../../services/playlist.service';
import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder,
} from '@angular/forms';
import { Playlist } from 'src/app/model/playlist';

@Component({
  selector: 'app-form-playlist',
  templateUrl: './form-playlist.component.html',
  styleUrls: ['./form-playlist.component.css'],
})
export class FormPlaylistComponent implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private playlistService: PlaylistService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.form = this.fb.group({
      nameControl: this.fb.control('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{1,})*$/),
        Validators.maxLength(50),
      ]),
    });
  }

  ngOnInit(): void {}

  save() {
    this.playlistService
      .insert(
        new Playlist(
          undefined,
          this.form.controls['nameControl'].value,
          this.form.controls['typePrivateControl'].value,
          this.form.controls['musicsControl'].value
        )
      )
      .subscribe((playlist) => {
        this.router.navigate(['/playlist']);
      });
  }
}
