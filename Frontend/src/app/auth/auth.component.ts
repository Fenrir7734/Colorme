import { Component } from '@angular/core';
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent {

  public onGithubLogin(): void {
    window.location.href = environment.githubLoginUrl;
  }

  public onGoogleLogin(): void {
    window.location.href = environment.googleLoginUrl;
  }

  public onFacebookLogin(): void {
    window.location.href = environment.facebookLoginUrl;
  }
}
