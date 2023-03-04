import { Component, OnInit } from '@angular/core';
import { DocType } from 'src/app/doc-type';
import { OcrService } from 'src/app/services/ocr.service';

@Component({
  selector: 'app-doc-type',
  templateUrl: './doc-type.component.html',
  styleUrls: ['./doc-type.component.css']
})
export class DocTypeComponent implements OnInit {

  constructor(
    private ocrService: OcrService
  ) { }

  public docType = new DocType('', '',[]);
  public tmpKeyword = ''
  public message = 'Succssfully Added'
  public showSpinner = false;

  ngOnInit(): void {
    this.docType.keywords = []
  }

  saveDocType(){
    this.showSpinner = true;
    this.ocrService.saveDocType(this.docType).subscribe(
      data => {
        this.docType = new DocType('', '',[]); 
        this.showSpinner = false;
      }
    )
  }

  addKeyword(){
    this.docType.keywords.push({"keyword":this.tmpKeyword});
    this.tmpKeyword = '';
  }

  removeKeyword(index: number){
    this.docType.keywords.splice(index, 1);
  }
}
