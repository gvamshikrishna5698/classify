import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { WorkRequest } from 'src/app/model/work-request';
import { RequestService } from 'src/app/service/request.service';

@Component({
  selector: 'app-view-request',
  templateUrl: './view-request.component.html',
  styleUrls: ['./view-request.component.css']
})
export class ViewRequestComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private requestService: RequestService,

  ) { }

  public id! : number;

  workRequest!: WorkRequest

  public imagePath:any;
  public isImage = false
  public isPdf =  false
  public isPreview = false
  public imgURL: any;
  public localUrl:any;
  public files!: FileList
  public index = 0
  public docId:number[] = []

  public showSpinner = false
  

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.getRequest(this.id)
  }

  getRequest(id: number){
    this.requestService.getRequest(id).subscribe(
      data => {
        this.workRequest = data;
        this.workRequest.actionComment = ''

        this.requestService.getDocId(data.sourceId).subscribe(
          data => {
            this.docId = data
            this.renderFile();
          }
        )
        }
    )
  }

  renderFile(){
    this. showSpinner = true;
    this.requestService.getDocument(this.docId[this.index]).subscribe(
      data => {
        this.preview(data);
        this. showSpinner = false
      }, err => {
        this.showSpinner = false
      }
    )
    this.index++;
    if(this.index >= this.docId.length)
          this.index = 0
  }

  saveRequest(){
    this.requestService.saveWorkRequest(this.workRequest).subscribe(
      data => {
        this.router.navigate(['/list-requests'])
      }
    )
  }
  preview(file: any) {

    console.log(file)

    this.isImage = false
    this.isPdf =  false


    var mimeType = file.type;
   
    if(mimeType == "application/pdf") {
      this.isPdf = true;      
    }else{
      this.isImage = true;
    }
 
    console.log(file)
    var reader = new FileReader();
    this.imagePath = file;
    reader.readAsDataURL(file); 
    reader.onload = (_event) => { 
    this.imgURL = reader.result; 
    this.localUrl = reader.result;
    }
  }


 

}
