import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Mutante } from '../modelo/mutante';
import { catchError} from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MutanteService {

  constructor(private http:HttpClient) { 

  }

   _url:string = 'http://localhost:9000/api/v1/mutante/';
    

  getAll():Observable<Mutante[]>{
    return this.http.get<Mutante[]>(this._url);
  }

  getOne(id:number):Observable<Mutante>{
    return this.http.get<Mutante>(this._url + id);
  }

  post(mutante:Mutante):Observable<Mutante>{
    const auxparse: Object = {responseType: 'text'}
    return this.http.post<Mutante>(this._url,mutante,auxparse).pipe(catchError(this.handleError));
  }
  put(id:number,mutante:Mutante):Observable<Mutante>{
    return this.http.put<Mutante>(this._url+id,mutante).pipe(catchError(this.handleError));
  }
  delete(id:number):Observable<any>{    
    alert(`Registro Eliminado`);
    return this.http.delete(this._url + id);
  }
  stats():Observable<string>{   
    const auxparse: Object = {responseType: 'text'}
    return this.http.get<string>(this._url+"stats",auxparse);
  }
  
  handleError(error: HttpErrorResponse) {  
  alert(`Codigo de error: ${error.status}` + `\nMensaje: ${error.error}`);
  return throwError('Intente de nuevo.');
}
}
