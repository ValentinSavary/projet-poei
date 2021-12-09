import { LoginComponent } from './login/login.component';
import { UserComponent } from './components/user/user.component';
import { PlaylistComponent } from './components/playlist/playlist.component';
import { MusicComponent } from './components/music/music.component';
import { AlbumComponent } from './components/album/album.component';
import { ArtistComponent } from './components/artist/artist.component';
import { HomeComponent } from './components/home/home.component';
import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'artist', component: ArtistComponent },
  { path: 'album', component: AlbumComponent },
  { path: 'music', component: MusicComponent },
  { path: 'playlist', component: PlaylistComponent },
  { path: 'user', component: UserComponent },
  { path: 'login', component: LoginComponent },
];
