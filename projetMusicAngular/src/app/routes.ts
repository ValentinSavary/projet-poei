import { FormMusicComponent } from './components/music/form-music/form-music.component';
import { FormAlbumComponent } from './components/album/form-album/form-album.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './components/user/user.component';
import { PlaylistComponent } from './components/playlist/playlist.component';
import { MusicComponent } from './components/music/music.component';
import { AlbumComponent } from './components/album/album.component';
import { ArtistComponent } from './components/artist/artist.component';
import { HomeComponent } from './components/home/home.component';
import { Routes } from '@angular/router';
import { Login1Component } from './login1/login1.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'artist', component: ArtistComponent },
  { path: 'album', component: AlbumComponent },
  { path: 'album/form', component: FormAlbumComponent },
  { path: 'album/form/:id', component: FormAlbumComponent },
  { path: 'music', component: MusicComponent },
  { path: 'music/form', component: FormMusicComponent },
  {
    path: 'music/form/:id',
    component: FormMusicComponent,
  },
  { path: 'playlist', component: PlaylistComponent },
  { path: 'user', component: UserComponent },
  { path: 'login', component: LoginComponent },
  { path: 'login1', component: Login1Component },
];
