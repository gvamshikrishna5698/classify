import { Component, OnInit } from '@angular/core';
import { OcrService } from 'src/app/services/ocr.service';

@Component({
  selector: 'app-preview-result',
  templateUrl: './preview-result.component.html',
  styleUrls: ['./preview-result.component.css']
})
export class PreviewResultComponent implements OnInit {
  

  constructor(
    private ocrService:OcrService
  ) { }

  ngOnInit(): void {
  }

  public imagePath: any;
  public imgURL: any;
  public localUrl: any;
  public message = '';

  isImage = false
  isPdf =  false
  isPreview = false

  isMainPreview = false
  showSpinner = false
  tokens!: string[] ;

  preview(files: FileList) {

    this.showSpinner = true;
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
      console.log('local url:'+this.localUrl)
    }

    this.ocrService.getTokens(files[0]).subscribe(
      data => {
        this.tokens = data,
        this.showSpinner = false;
      }
    );

  }


}
