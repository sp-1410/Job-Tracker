import { Injectable } from '@angular/core'; import { HttpClient } from '@angular/common/http'; import { Resume } from '../models/models';
@Injectable({providedIn:'root'}) export class ResumeService { private api='http://localhost:8080/api/resumes'; constructor(private http:HttpClient){}
getAll(){return this.http.get<Resume[]>(this.api)} create(x:Resume){return this.http.post<Resume>(this.api,x)} update(id:number,x:Resume){return this.http.put<Resume>(`${this.api}/${id}`,x)} delete(id:number){return this.http.delete<void>(`${this.api}/${id}`)} }
