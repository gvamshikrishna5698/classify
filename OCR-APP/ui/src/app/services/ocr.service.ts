import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { ClassificationResponse } from '../classification-response';
import { DocType } from '../doc-type';

@Injectable({
  providedIn: 'root'
})
export class OcrService {

  constructor(
    private httpClient: HttpClient
  ) { }

  private baseUrl = `http://localhost:8080/create`
  private tokensUrl = `${this.baseUrl}/getTokensFromFile`
  private byTexturl = `${this.baseUrl}/createRequestFromText`
  private byFileurl = `http://localhost:8080/create/creteForFile`
  private saveDocTypeUrl = `http://localhost:8080/admin/creteDocType`

  private getDocTypeUrl = `http://localhost:8080/admin/getDocType`

  getTokens(file:File) : Observable<any>{
    const formData = new FormData();
    formData.append("file", file);
    return this.httpClient.post<string[]>(this.tokensUrl, formData);
  }

  getClassificationByText(text:string){
    return this.httpClient.post<ClassificationResponse>(this.byTexturl, {"requestOwner": "MOBILE_APP",
    "requestCategory": "Category_1",
    "requestType": "INSTANT_API",
    "textContent" : text,
    });
  }

  getClassificationByFile( files: File[], customerName: string, referenceNumber: string){
    const data = {
      "requestOwner":"KOFAX",
      "requestCategory":"Category_1",
      "targetUrl":"http://localhost:9030/usecase/create/createWorkRequest",
      "targetPayload": `{"customerName": "${customerName}", "referenceNumber":"${referenceNumber}"}`}
    const formData = new FormData();
    Array.from(files).forEach(file => formData.append("files" , file));
    formData.append("request",  JSON.stringify(data));
    return this.httpClient.post<ClassificationResponse>(this.byFileurl, formData);
  }

  getDocTypes(){
    return this.httpClient.get<DocType>(this.getDocTypeUrl);
  }

  saveDocType(docType: DocType){
    return this.httpClient.post<DocType>(this.saveDocTypeUrl, docType);
  }

}
