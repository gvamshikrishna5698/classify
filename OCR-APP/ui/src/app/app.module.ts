import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { PreviewResultComponent } from './common/preview-result/preview-result.component';

import { PdfViewerModule } from 'ng2-pdf-viewer';
import { ByTextComponent } from './common/by-text/by-text.component';
import { ByFileComponent } from './common/by-file/by-file.component';
import { ResultComponent } from './common/result/result.component';
import { SpinnerComponent } from './common/spinner/spinner.component';
import { DocTypeComponent } from './common/doc-type/doc-type.component';
import { HeaderComponent } from './common/header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    PreviewResultComponent,
    ByTextComponent,
    ByFileComponent,
    ResultComponent,
    SpinnerComponent,
    DocTypeComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    PdfViewerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
