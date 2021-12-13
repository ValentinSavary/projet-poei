import { Observable } from 'rxjs';
import { AbstractControl, AsyncValidatorFn } from '@angular/forms';
import { ValidationErrors } from '@angular/forms';
import { debounceTime, map } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { ArtistService } from './../../../services/artist.service';
import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder,
} from '@angular/forms';
import { Artist } from 'src/app/model/artist';

@Component({
  selector: 'app-form-artist',
  templateUrl: './form-artist.component.html',
  styleUrls: ['./form-artist.component.css'],
})
export class FormArtistComponent implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private artistService: ArtistService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.form = this.fb.group({
      nameControl: this.fb.control('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{1,})*$/),
        Validators.maxLength(50),
      ]),
      countryControl: this.fb.control('', [
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{1,})*$/),
        Validators.maxLength(25),
      ]),
      albumsControl: this.fb.control('', []),
    });
  }

  ngOnInit(): void {}

  save() {
    this.artistService
      .insert(
        new Artist(
          undefined,
          this.form.controls['nameControl'].value,
          this.form.controls['countryControl'].value,
          this.form.controls['albumsControl'].value
        )
      )
      .subscribe((artist) => {
        this.router.navigate(['/artist']);
      });
  }
}
