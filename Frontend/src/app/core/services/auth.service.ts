import {Injectable} from '@angular/core';
import {UserPermissions} from "../models/user-permissions.model";
import {isEmpty} from "lodash";
import {Observable, Subscription} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Roles} from "../constants/roles";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _permissions: UserPermissions | null = null;
  private _isLoadingPermissions: boolean = false;

  constructor(private httpClient: HttpClient) {
  }

  get userId(): string | undefined {
    return this._permissions?.userId;
  }

  get userRoles(): string[] | undefined {
    return this._permissions?.roles;
  }

  get isLoggedIn(): boolean {
    return !!this.userId;
  }

  get isAdmin(): boolean {
    return (
      this.isAuthorized() &&
      this.hasAnyRole(Roles.ADMIN)
    );
  }

  private isAuthorized(): boolean {
    return (
      this.isLoggedIn &&
      !isEmpty(this.userRoles)
    );
  }

  private hasAnyRole(...roles: string[]): boolean {
    return (
      !isEmpty(this.userRoles) &&
      roles.some(role => this.userRoles!.indexOf(role) >= 0)
    );
  }

  get isLoadingPermissions(): boolean {
    return this._isLoadingPermissions;
  }

  fetchPermissions(): Subscription {
    this._isLoadingPermissions = true;
    return this.getLoggedUserPermissions()
      .subscribe((permissions: UserPermissions) => {
        this._permissions = permissions;
        this._isLoadingPermissions = false;
      });
  }

  redirectToGoogleLogin(): void {
    window.location.href = environment.googleLoginUrl;
  }

  redirectToFacebookLogin(): void {
    window.location.href = environment.facebookLoginUrl;
  }

  redirectToGithubLogin(): void {
    window.location.href = environment.githubLoginUrl;
  }

  private getLoggedUserPermissions(): Observable<UserPermissions> {
    return this.httpClient.get<UserPermissions>(`${environment.baseUrl}/api/v1/me/permissions`);
  }
}

