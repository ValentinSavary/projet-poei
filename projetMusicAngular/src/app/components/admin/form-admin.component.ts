import { FileUploader } from 'ng2-file-upload';
import { Genre } from '../../model/genre';
import { Observable } from 'rxjs';
import { AbstractControl, AsyncValidatorFn } from '@angular/forms';
import { ValidationErrors } from '@angular/forms';
import { debounceTime, map } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { MusicService } from '../../services/music.service';
import { ArtistService } from '../../services/artist.service';
import { AlbumService } from '../../services/album.service';
import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder,
} from '@angular/forms';
import { Music } from 'src/app/model/music';
import { Album } from 'src/app/model/album';
import { Artist } from 'src/app/model/artist';

@Component({
  selector: 'app-form-admin',
  templateUrl: './form-admin.component.html',
})
export class FormAdminComponent implements OnInit {
  genres = Genre;
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private musicService: MusicService,
    private albumService: AlbumService,
    private artistService: ArtistService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.form = this.fb.group({
      nameArtistControl: this.fb.control('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{1,})*$/),
        Validators.maxLength(50),
      ]),
      countryControl: this.fb.control('', [
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{1,})*$/),
        Validators.maxLength(25),
      ]),
      albumsControl: this.fb.control('', []),

      nameAlbumControl: this.fb.control('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{1,})*$/),
        Validators.maxLength(50),
      ]),
      yearControl: this.fb.control('', [
        Validators.pattern(/^[0-9]{4}/),
        Validators.maxLength(4),
      ]),
      coverControl: this.fb.control('', []),
      titleControl: this.fb.control('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{1,})*$/),
        Validators.maxLength(50),
      ]),
      durationControl: this.fb.control('', [
        Validators.pattern(/^[0-9]{1,4}$/),
        Validators.maxLength(4),
      ]),
      musicFileControl: this.fb.control('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{1,})*$/),
      ]),
      genreControl: this.fb.control('', []),
    });
  }

  ngOnInit(): void {}

  save() {
    this.artistService.insert(
      new Artist(
        undefined,
        this.form.controls['nameControl'].value,
        this.form.controls['countryControl'].value,
        this.form.controls['albumsControl'].value
      )
    );
    this.albumService.insert(
      new Album(
        undefined,
        this.form.controls['nameControl'].value,
        this.form.controls['yearControl'].value,
        this.form.controls['coverControl'].value
      )
    );
    this.musicService
      .insert(
        new Music(
          undefined,
          this.form.controls['titleControl'].value,
          this.form.controls['durationControl'].value,
          this.form.controls['musicFileControl'].value,
          this.form.controls['genreControl'].value
        )
      )
      .subscribe((music) => {
        this.router.navigate(['/admin']);
      });
  }

  uploader: FileUploader = new FileUploader({
    url: 'http://localhost:8080/music/album/covers',
    removeAfterUpload: false,
    autoUpload: true,
  });
}
