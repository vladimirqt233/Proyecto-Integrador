import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { GoogleMapsModule } from '@angular/google-maps';
import { FormsModule } from '@angular/forms';
// { AppComponent } from './app.component';

@NgModule({
    //declarations: [AppComponent],
    imports: [BrowserModule, GoogleMapsModule, FormsModule],
    //bootstrap: [AppComponent],
})
export class AppModule {}
