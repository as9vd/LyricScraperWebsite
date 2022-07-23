import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './component/header/header.component';
import { MainPageBodyComponent } from './component/main-page-body/main-page-body.component';
import { AnalyserComponent } from './component/analyser/analyser.component';
import { QuestionsComponent } from './component/questions/questions.component';
import { SongScraperComponent } from './component/song-scraper/song-scraper.component';
import { MatFormFieldModule } from '@angular/material/form-field';

@NgModule({
  declarations: [
    AppComponent,
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
    MatAutocompleteModule,
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
  
})
export class AppModule { }
