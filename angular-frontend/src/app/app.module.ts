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
import { AnalyserComponent } from './component/analyser/analyser.component';
import { QuestionsComponent } from './component/questions/questions.component';
import { SongScraperComponent } from './component/song-scraper/song-scraper.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { PopularSongsComponent } from './component/popular-songs/popular-songs.component';
import { MatSelectModule } from '@angular/material/select';
import { PieChartComponent } from './component/pie-chart/pie-chart.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AnalyserComponent,
    QuestionsComponent,
    SongScraperComponent,
    PopularSongsComponent,
    PieChartComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([
      { path: '', component: AnalyserComponent },
      { path: 'home', component: AnalyserComponent },
      { path: 'popularSongs', component: PopularSongsComponent },
      { path: 'faq', component: QuestionsComponent },
    ]),
    AppRoutingModule,
    MatAutocompleteModule,
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSelectModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
