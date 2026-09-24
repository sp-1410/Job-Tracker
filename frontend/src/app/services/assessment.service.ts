import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OnlineAssessment } from '../models/models';
@Injectable({providedIn:'root'}) export class AssessmentService {
  private api='http://localhost:8080/api/assessments'; constructor(private http:HttpClient){}
  getByApplication(id:number):Observable<OnlineAssessment>{return this.http.get<OnlineAssessment>(`${this.api}/application/${id}`)}
  create(x:OnlineAssessment){return this.http.post<OnlineAssessment>(this.api,x)}
  update(id:number,x:OnlineAssessment){return this.http.put<OnlineAssessment>(`${this.api}/${id}`,x)}
  delete(id:number){return this.http.delete<void>(`${this.api}/${id}`)}
}
