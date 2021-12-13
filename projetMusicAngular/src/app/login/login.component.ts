import { AccountType } from './../model/accountType';
import { UserService } from './../services/user.service';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  showMessage = false;
  message = '';

  form: FormGroup;

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router
  ) {
    this.form = new FormGroup({
      login: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{2,})*$/),
        Validators.maxLength(200),
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.pattern(
          /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])([a-zA-Z0-9$@#_-]{5,25})$/
        ),
      ]),
    });
  }

  ngOnInit(): void {}

  submit() {
    if (
      this.form.get('login')!.hasError('required') ||
      this.form.get('login')!.hasError('pattern') ||
      this.form.get('password')!.hasError('required') ||
      this.form.get('password')!.hasError('pattern')
    ) {
      this.message = "erreur d'authentification";
      this.showMessage = true;
    } else {
      this.authService
        .auth(
          this.form.controls['login'].value,
          this.form.controls['password'].value
        )
        .subscribe(
          (ok) => {
            this.showMessage = false;
            sessionStorage.setItem(
              'token',
              btoa(
                this.form.controls['login'].value +
                  ':' +
                  this.form.controls['password'].value
              )
            );
            sessionStorage.setItem('login', this.form.controls['login'].value);
            sessionStorage.setItem(
              'role',
              JSON.stringify(
                this.userService
                  .byLogin(this.form.controls['login'].value)
                  .subscribe((user) => {
                    return user.AccountType;
                  })
              )
            );
            if (!!localStorage.getItem('valider')) {
              localStorage.removeItem('valider');
              this.router.navigate(['/valider']);
            } else {
              this.router.navigate(['/home']);
            }
          },
          (error) => {
            this.message = "erreur d'authentification";
            this.showMessage = true;
          }
        );
    }
  }
}
