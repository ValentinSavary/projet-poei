import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from './../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login1',
  templateUrl: './login1.component.html',
  styleUrls: ['./login1.component.css'],
})
export class Login1Component implements OnInit {
  login: string = '';
  password: string = '';
  showMessage = false;
  message = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private activatedroute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedroute.queryParams.subscribe((params) => {
      if (!!params['error']) {
        if (params['error']) {
          this.message = 'authentification requise';
          this.showMessage = true;
        }
      }
    });
  }

  check() {
    this.authService.auth(this.login, this.password).subscribe(
      (ok) => {
        this.showMessage = false;
        sessionStorage.setItem('token', btoa(this.login + ':' + this.password));
        sessionStorage.setItem('login', this.login);
        if (!!ok['free']) {
          sessionStorage.setItem('role', 'free');
        } else {
          sessionStorage.setItem('role', 'admin');
        }
        this.router.navigate(['/user']);
      },
      (error) => {
        this.message = 'Authentication error';
        this.showMessage = true;
      }
    );
  }
}
