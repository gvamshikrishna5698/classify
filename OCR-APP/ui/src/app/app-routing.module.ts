import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ByFileComponent } from './common/by-file/by-file.component';
import { ByTextComponent } from './common/by-text/by-text.component';
import { DocTypeComponent } from './common/doc-type/doc-type.component';
import { PreviewResultComponent } from './common/preview-result/preview-result.component';

const routes: Routes = [
  { path: 'preview-token', component: PreviewResultComponent },
  { path: 'by-text', component: ByTextComponent },
  { path: 'by-file', component: ByFileComponent },
  { path: 'work-type', component: DocTypeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})


export class AppRoutingModule { 
  
}
