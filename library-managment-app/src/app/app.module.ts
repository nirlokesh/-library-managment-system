import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatCardModule} from '@angular/material/card';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { BookdetailsComponent } from './book-details/bookdetails/bookdetails.component';
import { BorrowListComponent } from './borrowlist/borrow-list/borrow-list.component';
import { LoginComponent } from './login/login/login.component';
import { FormsModule } from '@angular/forms';
import { AlertModule,AlertConfig  } from 'ngx-bootstrap/alert';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AccordionModule } from 'ngx-bootstrap/accordion';

 

@NgModule({
  declarations: [
    AppComponent,
    BookdetailsComponent,
    BorrowListComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,MatCardModule,HttpClientModule,FormsModule,AlertModule,BrowserAnimationsModule,AccordionModule
  ],
  providers: [AlertConfig],
  bootstrap: [AppComponent]
})
export class AppModule { }
