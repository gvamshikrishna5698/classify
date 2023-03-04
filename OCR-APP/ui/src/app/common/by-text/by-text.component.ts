import { Component, OnInit } from '@angular/core';
import { ClassificationResponse } from 'src/app/classification-response';
import { OcrService } from 'src/app/services/ocr.service';

@Component({
  selector: 'app-by-text',
  templateUrl: './by-text.component.html',
  styleUrls: ['./by-text.component.css']
})
export class ByTextComponent implements OnInit {

  constructor(
    private ocrService: OcrService
  ) { }

  ngOnInit(): void {
  }

  public text!:string;
  public response!: ClassificationResponse;
  public showSpinner = false

  processRequest(){
    this.showSpinner = true;
    this.ocrService.getClassificationByText(this.text).subscribe(
      data => {this.response = data
                this.showSpinner = false;
              }   
        )
      }

}
