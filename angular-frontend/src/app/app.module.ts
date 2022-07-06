import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ArtistGrabberComponent } from './component/artist-grabber/artist-grabber.component';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './component/header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    ArtistGrabberComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
