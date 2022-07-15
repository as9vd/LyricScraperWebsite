import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ArtistGrabberComponent } from './component/artist-grabber/artist-grabber.component';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './component/header/header.component';
import { MainPageBodyComponent } from './component/main-page-body/main-page-body.component';
import { AnalyserComponent } from './component/analyser/analyser.component';
import { QuestionsComponent } from './component/questions/questions.component';
import { SongScraperComponent } from './component/song-scraper/song-scraper.component';

@NgModule({
  declarations: [
    AppComponent,
    ArtistGrabberComponent,
    HeaderComponent,
    MainPageBodyComponent,
    AnalyserComponent,
    QuestionsComponent,
    SongScraperComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([
      {path: '', component: HeaderComponent},
      {path: 'analyser', component: AnalyserComponent},
      {path: 'questions', component: QuestionsComponent},
    ]),
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
