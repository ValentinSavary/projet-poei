import { ActivatedRoute, Router } from '@angular/router';
import { Genre } from './../../../model/genre';
import { AlbumService } from './../../../services/album.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Album } from 'src/app/model/album';

@Component({
  selector: 'app-form-album',
  templateUrl: './form-album.component.html',
  styleUrls: ['./form-album.component.css'],
})
export class FormAlbumComponent implements OnInit {
  form: FormGroup;
  constructor(
    private albumService: AlbumService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.form = new FormGroup({
      name: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{1,})*$/),
        Validators.maxLength(200),
      ]),
      year: new FormControl('', [Validators.required]),

      cover: new FormControl(''),
    });
  }
  ngOnInit(): void {}

  save() {
    this.albumService
      .insert(
        new Album(
          undefined,
          this.form.controls['name'].value,
          this.form.controls['year'].value,
          this.form.controls['cover'].value
        )
      )
      .subscribe((album) => {
        console.log(album);
      });
    this.router.navigate(['/album']);
  }
}
