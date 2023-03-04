import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListRequestsComponent } from './common/list-requests/list-requests.component';
import { ViewRequestComponent } from './common/view-request/view-request.component';

const routes: Routes = [
  { path: 'list-requests', component: ListRequestsComponent },
  { path: 'work-request/:id', component: ViewRequestComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
