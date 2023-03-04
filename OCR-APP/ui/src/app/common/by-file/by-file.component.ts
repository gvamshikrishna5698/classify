import { Component, OnInit } from '@angular/core';
import { ClassificationResponse } from 'src/app/classification-response';
import { OcrService } from 'src/app/services/ocr.service';

@Component({
  selector: 'app-by-file',
  templateUrl: './by-file.component.html',
  styleUrls: ['./by-file.component.css']
})
export class ByFileComponent implements OnInit {

  constructor(
    private ocrService:OcrService
  ) { }

  ngOnInit(): void {
  }

  filesList :File[] = [];

  private request = ``;
  public response!: ClassificationResponse;
  public showSpinner = false

  public imagePath:any;
  public isImage = false
  public isPdf =  false
  public isPreview = false
  public imgURL: any;
  public localUrl:any;
  public files!: FileList
  public customerName!: string
  public referenceNumber!: string

  addFile(files: FileList){
    Array.from(files).forEach(file => this.filesList.push(file));
    this.files = files
  }

  sendRequest(){
    this.showSpinner = true;
    this.ocrService.getClassificationByFile(this.filesList, this.customerName, this.referenceNumber,).subscribe(
      data => {
        this.response = data;
        this.showSpinner = false;
        this.preview(this.files);
        this.customerName = ''
        this.referenceNumber = ''
      },
      err =>{
        this.showSpinner = false
        this.customerName = ''
        this.referenceNumber = ''
      }
    )
  }

  removeSelectedFile(index:number){
    this.filesList.splice(index, 1);
  }


  preview(files: FileList) {
    console.log(files)
    this.isImage = false
    this.isPdf =  false

    if (files.length === 0)
      return;
 
    var mimeType = files[0].type;
   
    if(mimeType == "application/pdf") {
      this.isPdf = true;      
    }else{
      this.isImage = true;
    }
 
    var reader = new FileReader();
    this.imagePath = files;
    reader.readAsDataURL(files[0]); 
    reader.onload = (_event) => { 
      this.imgURL = reader.result; 
      this.localUrl = reader.result;
      console.log(this.localUrl)
    }
  }


}
