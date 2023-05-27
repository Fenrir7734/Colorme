import {inject} from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import {AuthService} from "../services/auth.service";

export const authGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const auth: AuthService = inject(AuthService);
  const router: Router = inject(Router);

  if (auth.isLoggedIn) {
    return true;
  } else if (!auth.isLoadingPermissions) {
    auth.fetchPermissions().add(() => router.navigate([state.url]));
    return false;
  }

  router.navigate(['login']);
  return false;
};
