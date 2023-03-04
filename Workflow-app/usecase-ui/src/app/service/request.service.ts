import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { WorkRequest } from '../model/work-request';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public getRequestUrl = 'http://localhost:9030/usecase/create/getWorkRequest?username='
  public getRequestByIdUrl = 'http://localhost:9030/usecase/create/getWorkRequest/'
  public getDocIdUrl = 'http://localhost:8080/create/getDocuments/'
  public getDocumentUrl = 'http://localhost:8080/create/getDocument/'
  public saveRequestUrl = 'http://localhost:9030/usecase/create/saveWorkRequest'

  getRequests(username: string){
    return this.httpClient.get<WorkRequest[]>(this.getRequestUrl+username);
  }

  getRequest(id:number){
    return this.httpClient.get<WorkRequest>(this.getRequestByIdUrl+id);
  }

  getDocument(id:number){
    let headers = new HttpHeaders({'Accept': 'application/octet-stream'});
  
    return this.httpClient.get(
      this.getDocumentUrl+id, {
        headers: headers,
        responseType: 'blob' 
      }
    )
  }

  saveWorkRequest( request:WorkRequest){
    return this.httpClient.post<WorkRequest>(this.saveRequestUrl, request);
  }
  
  getDocId(id:number){
    return this.httpClient.get<number[]>(this.getDocIdUrl+id);
  }

  
}
