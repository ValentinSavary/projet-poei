import { AccountType } from './../model/accountType';
import { User } from './../model/user';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private static URL: string = 'http://localhost:8080/music/api/user';

  constructor(private http: HttpClient) {}

  private get httpHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorization: 'Basic ' + sessionStorage.getItem('token'),
      'Content-Type': 'application/json',
    });
  }

  // Methode qui permet de recuperer tous les users
  public allUser(): Observable<any[]> {
    return this.http.get<any[]>(UserService.URL);
  }

  // Methode qui permet de recuperer un user par son ID
  public byId(id: number): Observable<any> {
    return this.http.get<User[]>(`${UserService.URL}/${id}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer un user par son Login
  public byLogin(login: string): Observable<any> {
    return this.http.get<User[]>(`${UserService.URL}/${login}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de recuperer un user par ses playlists
  public byPlaylist(title: string): Observable<User> {
    return this.http.get<User>(`${UserService.URL}/playlist/${name}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de supprimer un user par son id
  public delete(id: number): Observable<any> {
    return this.http.delete<User[]>(`${UserService.URL}/${id}`, {
      headers: this.httpHeaders,
    });
  }

  // Methode qui permet de creer un user
  public insert(user: User): Observable<User> {
    const a = {
      username: user.username,
      login: user.login,
      password: user.password,
      accountType: user.accountType,
    };
    return this.http.post<User>(UserService.URL, a);
  }

  // Methode pour la mise a jour d un user dans la BDD
  public update(user: User): Observable<User> {
    return this.http.put<User>(`${UserService.URL}/${user.id}`, user, {
      headers: this.httpHeaders,
    });
  }

  public checkLogin(login: string): Observable<boolean> {
    return this.http.get<boolean>(
      'http://localhost:8080/music/api/user/register/' + login
    );
  }

  public getAccountType(user: any): AccountType | undefined {
    return user.accountType;
  }
}
