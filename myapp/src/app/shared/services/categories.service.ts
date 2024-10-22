import { Pagination } from './../models/pagination.model';
import { Category } from './../models/category/category.model';
import { environment } from './../../../environment/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { catchError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CategoriesService extends BaseService {
  private _sharedHeaders = new HttpHeaders();
  constructor(private http: HttpClient) {
    super();
    this._sharedHeaders = this._sharedHeaders.set(
      'Content-type',
      'application/json'
    );
  }

  getDetails(id: string) {
    return this.http
      .get<Category>(`${environment.API_URL}/api/category/${id}`, {
        headers: this._sharedHeaders,
      })
      .pipe(catchError(this.handleError));
  }

  getAll() {
    return this.http
      .get<Category[]>(`${environment.API_URL}/api/category/list`, {
        headers: this._sharedHeaders,
      })
      .pipe(catchError(this.handleError));
  }

  getAllPaging(filter: any, pageIndex: number, pageSize: number) {
    return this.http
      .get<Pagination<Category>>(
        `${environment.API_URL}/api/category/paging?page=${pageIndex}&size=${pageSize}&name=${filter}`,
        { headers: this._sharedHeaders }
      )
      .pipe(catchError(this.handleError));
  }
}
