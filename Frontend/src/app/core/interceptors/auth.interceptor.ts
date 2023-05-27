import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpInterceptor, HttpErrorResponse, HttpSentEvent, HttpHeaderResponse, HttpProgressEvent, HttpResponse, HttpUserEvent
} from '@angular/common/http';
import {catchError, EMPTY, Observable, throwError} from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpSentEvent |
    HttpHeaderResponse | HttpProgressEvent |
    HttpResponse<any> | HttpUserEvent<any> | any> {

    return next.handle(this.addHeadersToRequest(request))
      .pipe(
        catchError((err) => {
          if (err instanceof HttpErrorResponse) {
            if (err.status === 401) {
              return EMPTY;
            }
            if (err.status === 404 && err.url?.indexOf('api') === -1) {
              return EMPTY;
            }
          } else {
            return throwError(err);
          }

          return EMPTY;
        })
      );
  }

  private addHeadersToRequest(request: HttpRequest<any>) {
    return request.clone({withCredentials: true});
  }
}
