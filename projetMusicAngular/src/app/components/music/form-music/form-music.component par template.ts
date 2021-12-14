/* import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Genre } from './../../../model/genre';
import { MusicService } from './../../../services/music.service';
import { Component, OnInit } from '@angular/core';
import { Music } from 'src/app/model/music';
import { FileUploader } from 'ng2-file-upload';

@Component({
  selector: 'app-form-music',
  templateUrl: './form-music.component.html',
  styleUrls: ['./form-music.component.css'],
})
export class FormMusicComponent implements OnInit {
  music: Music = new Music();
  genres = Genre;
  URL = 'http://localhost:8080/music/music/musics';

  constructor(
    private activatedRoute: ActivatedRoute,
    private musicService: MusicService,
    private router: Router,
    private httpClient: HttpClient
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
    // var formData: any = new FormData();
    // formData.append(form.controls['file'].value);
    // this.httpClient.post(this.URL, formData);

    // const formData = new FormData(form['musicFileControl'].value);
    // formData.append('file', form.get('uploader').value);
    // this.httpClient.post<any>(this.URL, formData).subscribe(
    //   (res) => console.log(res),
    //   (err) => console.log(err)
    // );
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

  // handleFileInput(files: FileList) {
  //   this.file = files.item(0);
  // }

  // // uploadFileToActivity() {
  // //   this.fileUploadService.postFile(this.fileToUpload).subscribe(
  // //     (data) => {
  // //       console.log('upload successful');
  // //     },
  // //     (error) => {
  // //       console.log(error);
  // //     }
  // //   );
  // // }

  // uploader: FileUploader = new FileUploader({
  //   url: 'http://localhost:8080/music/music/musics',
  //   removeAfterUpload: false,
  //   autoUpload: false,
  // });

  goList() {
    this.router.navigate(['/music']);
  }
}
 */
