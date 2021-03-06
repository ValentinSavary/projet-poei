import { FormArtistComponent } from './components/artist/form-artist/form-artist.component';
import { FormPlaylistComponent } from './components/playlist/form-playlist/form-playlist.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FileUploadModule } from 'ng2-file-upload';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './login/login.component';
import { ArtistComponent } from './components/artist/artist.component';
import { AlbumComponent } from './components/album/album.component';
import { MusicComponent } from './components/music/music.component';
import { UserComponent } from './components/user/user.component';
import { PlaylistComponent } from './components/playlist/playlist.component';
import { FormMusicComponent } from './components/music/form-music/form-music.component';
import { FormAlbumComponent } from './components/album/form-album/form-album.component';
import { routes } from './routes';
import { RegisterComponent } from './components/user/register/register.component';
import { Login1Component } from './login1/login1.component';
import { FormAdminComponent } from './components/admin/form-admin.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ArtistComponent,
    AlbumComponent,
    MusicComponent,
    UserComponent,
    PlaylistComponent,
    FormMusicComponent,
    FormAlbumComponent,
    FormArtistComponent,
    FormPlaylistComponent,
    RegisterComponent,
    Login1Component,
    FormAdminComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    FileUploadModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
