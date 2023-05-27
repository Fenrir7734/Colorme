import {inject} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot} from '@angular/router';
import {AuthService} from "../services/auth.service";

export const adminGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const auth: AuthService = inject(AuthService);

  if (auth.isAdmin) {
    return true;
  } else {
    return false;
  }
}
