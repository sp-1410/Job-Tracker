import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Job } from '../models/models';

@Injectable({ providedIn: 'root' })
export class JobService {
  private readonly api = 'http://localhost:8080/api/jobs';
  constructor(private http: HttpClient) {}
  getAll(query = ''): Observable<Job[]> {
    const suffix = query.trim() ? `?q=${encodeURIComponent(query.trim())}` : '';
    return this.http.get<Job[]>(this.api + suffix);
  }
  create(job: Job): Observable<Job> { return this.http.post<Job>(this.api, job); }
  update(id: number, job: Job): Observable<Job> { return this.http.put<Job>(`${this.api}/${id}`, job); }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.api}/${id}`); }
}
