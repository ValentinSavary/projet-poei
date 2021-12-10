import { ActivatedRoute, Router } from '@angular/router';
import { ArtistService } from './../../../services/artist.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Artist } from 'src/app/model/artist';

@Component({
  selector: 'app-form-artist',
  templateUrl: './form-artist.component.html',
  styleUrls: ['./form-artist.component.css'],
})
export class FormArtistComponent implements OnInit {
  form: FormGroup;
  constructor(
    private artistService: ArtistService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.form = new FormGroup({
      name: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{1,})*$/),
        Validators.maxLength(200),
      ]),
      country: new FormControl('', [Validators.required]),
      albums: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {}

  save() {
    this.artistService
      .insert(
        new Artist(
          undefined,
          this.form.controls['name'].value,
          this.form.controls['country'].value,
          this.form.controls['albums'].value
        )
      )
      .subscribe((artist) => {
        console.log(artist);
      });
    this.router.navigate(['/artist']);
  }
}
