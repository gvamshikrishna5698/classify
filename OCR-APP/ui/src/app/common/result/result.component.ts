import { Component, Input, OnInit } from '@angular/core';
import { ClassificationResponse } from 'src/app/classification-response';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

  constructor() { }

  @Input() response!:ClassificationResponse ;

  ngOnInit(): void {
  }

}
