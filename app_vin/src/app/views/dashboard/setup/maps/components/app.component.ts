import {Component, NgModule} from '@angular/core';
import {BrowserModule} from "@angular/platform-browser";
import {GoogleMapsModule} from "@angular/google-maps";
import {FormsModule} from "@angular/forms";

@Component({
    selector: 'app-root',
    template: `
    <h1>Google Maps con Angular</h1>
    <app-map></app-map>
  `,
})
export class AppComponent {}

@NgModule({
    declarations: [AppComponent], // Aquí debe incluirse AppComponent
    imports: [
        BrowserModule,
        GoogleMapsModule,
        FormsModule
    ],
    bootstrap: [AppComponent] // Este es el componente raíz de la aplicación
})
export class AppModule {}
