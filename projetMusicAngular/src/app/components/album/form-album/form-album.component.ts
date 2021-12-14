import { FileUploader } from 'ng2-file-upload';
import { Observable } from 'rxjs';
import { AbstractControl, AsyncValidatorFn } from '@angular/forms';
import { ValidationErrors } from '@angular/forms';
import { debounceTime, map } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { AlbumService } from './../../../services/album.service';
import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder,
} from '@angular/forms';
import { Album } from 'src/app/model/album';

@Component({
  selector: 'app-form-album',
  templateUrl: './form-album.component.html',
  styleUrls: ['./form-album.component.css'],
})
export class FormAlbumComponent implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private albumService: AlbumService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.form = this.fb.group({
      nameControl: this.fb.control('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{1,})*$/),
        Validators.maxLength(50),
      ]),
      yearControl: this.fb.control('', [
        Validators.pattern(/^[0-9]{4}/),
        Validators.maxLength(4),
      ]),
      coverControl: this.fb.control('', []),
    });
  }

  ngOnInit(): void {}

  save() {
    this.albumService
      .insert(
        new Album(
          undefined,
          this.form.controls['nameControl'].value,
          this.form.controls['yearControl'].value,
          this.form.controls['coverControl'].value
        )
      )
      .subscribe((album) => {
        this.router.navigate(['/album']);
      });
  }

  uploader: FileUploader = new FileUploader({
    url: 'http://localhost:8080/music/album/covers',
    removeAfterUpload: false,
    autoUpload: true,
  });
}
