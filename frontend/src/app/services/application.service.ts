import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Application, ApplicationStatus, ApplicationStatusHistory } from '../models/models';

@Injectable({ providedIn: 'root' })
export class ApplicationService {
  private readonly api = 'http://localhost:8080/api/applications';
  constructor(private http: HttpClient) {}
  getAll(): Observable<Application[]> { return this.http.get<Application[]>(this.api); }
  create(application: Application): Observable<Application> { return this.http.post<Application>(this.api, application); }
  update(id: number, application: Application): Observable<Application> { return this.http.put<Application>(`${this.api}/${id}`, application); }
  updateStatus(id: number, status: ApplicationStatus): Observable<Application> {
    const params = new HttpParams().set('value', status);
    return this.http.patch<Application>(`${this.api}/${id}/status`, {}, { params });
  }
  history(id: number): Observable<ApplicationStatusHistory[]> { return this.http.get<ApplicationStatusHistory[]>(`${this.api}/${id}/history`); }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.api}/${id}`); }
}
