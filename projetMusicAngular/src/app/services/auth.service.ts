import { Observable } from 'rxjs';
import { CanActivate, Router } from '@angular/router';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService implements CanActivate {
  constructor(private http: HttpClient, private router: Router) {}

  public canActivate(): boolean {
    if (!!sessionStorage.getItem('token')) {
      return true;
    } else {
      this.router.navigate(['/login1'], { queryParams: { error: 'auth' } });
    }
    return false;
  }

  public auth(login: string, password: string): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders({
      Authorization: 'Basic ' + btoa(login + ':' + password),
      'Content-Type': 'application/json',
    });
    return this.http.get('http://localhost:8080/music/api/auth', {
      headers: httpHeaders,
    });
  }
}
