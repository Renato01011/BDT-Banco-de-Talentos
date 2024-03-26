import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { ToastService } from '../../services/toast/toast.service';
import {
  BAD_REQUEST,
  INTERNAL_SERVER_ERROR,
  NOT_FOUND,
  UNAUTHORIZED,
} from '../../global/constants/http-errors.constants';
import { LoaderService } from '../../services/loader/loader.service';

@Injectable()
export class ErrHandlerInterceptor implements HttpInterceptor {
  constructor(
    private toastService: ToastService,
    private loaderService: LoaderService
  ) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    return next
      .handle(request)
      .pipe(
        catchError((error: HttpErrorResponse) => this.handleRequestError(error))
      );
  }

  private handleRequestError(error: HttpErrorResponse): Observable<any> {
    this.getErrMessage(error);
    this.loaderService.hideLoader();
    return throwError(() => error);
  }

  private getErrMessage(error: HttpErrorResponse) {
    if (error.status == 0) {
      this.toastService.addProperties(
        'error',
        'Error de servidor',
        'Fallo de conexión con el servidor'
      );
    } else if (error.status === BAD_REQUEST) {
      this.toastService.addProperties(
        'warn',
        'Mensaje de servidor',
        'Fallo de conexión con el servidor'
      );
    } else if (error.status === UNAUTHORIZED) {
      this.toastService.addProperties(
        'warn',
        'Mensaje de servidor',
        'Debe autenticarse'
      );
    } else if (error.status === NOT_FOUND) {
      this.toastService.addProperties(
        'warn',
        'Mensaje de servidor',
        'El recurso solicitado no existe'
      );
    } else if (error.status === INTERNAL_SERVER_ERROR) {
      this.toastService.addProperties(
        'error',
        'Error de servidor',
        'Disculpe la molestia, vuelva a intentarlo mas tarde'
      );
    } else {
      this.toastService.addProperties(
        'error',
        'Mensaje de servidor',
        'Fallo de conexión con el servidor'
      );
    }
  }
}
