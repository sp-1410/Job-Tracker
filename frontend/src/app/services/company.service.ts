import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Company } from '../models/models';

@Injectable({ providedIn: 'root' })
export class CompanyService {
  private readonly api = 'http://localhost:8080/api/companies';
  constructor(private http: HttpClient) {}
  getAll(): Observable<Company[]> { return this.http.get<Company[]>(this.api); }
  create(company: Company): Observable<Company> { return this.http.post<Company>(this.api, company); }
  update(id: number, company: Company): Observable<Company> { return this.http.put<Company>(`${this.api}/${id}`, company); }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.api}/${id}`); }
}
