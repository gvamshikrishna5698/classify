import { Component, OnInit } from '@angular/core';
import { WorkRequest } from 'src/app/model/work-request';
import { RequestService } from 'src/app/service/request.service';

@Component({
  selector: 'app-list-requests',
  templateUrl: './list-requests.component.html',
  styleUrls: ['./list-requests.component.css']
})
export class ListRequestsComponent implements OnInit {

  constructor(
    private requestService: RequestService
  ) { }

  ngOnInit(): void {
    this.getWorkRequest()
  }

  workRequest: WorkRequest[] = [];
  username = 'sai'

  getWorkRequest(){
    this.requestService.getRequests(this.username).subscribe(
      data => {
        this.workRequest = data;
      }
    )
  }

}
