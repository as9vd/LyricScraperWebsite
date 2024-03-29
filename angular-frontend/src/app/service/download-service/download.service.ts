import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { FileData } from 'src/app/common/fileData/file-data';
import { environment } from 'src/environments/environment';
import { SongService } from '../song-service/song.service';

@Injectable({
  providedIn: 'root',
})
// This class manages the database. Every 5 songs, we clear out the first-inserted song.
export class DownloadService {
  constructor(
    @Inject(HttpClient) private httpClient: HttpClient,
    @Inject(SongService) private songService: SongService
  ) {}

  // private baseUrl = 'http://localhost:8080';
  private baseUrl = 'http://geniusscraper.us-east-2.elasticbeanstalk.com';

  download() {
    return this.songService.file;
  }

  clean() {
    return this.httpClient.get(this.baseUrl + '/clearTemp');
  }

  clearRecents() {
    return this.httpClient.get(this.baseUrl + '/clearRecents');
  }
}
