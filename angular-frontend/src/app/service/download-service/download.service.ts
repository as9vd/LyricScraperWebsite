import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { FileData } from 'src/app/common/fileData/file-data';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class DownloadService {
  constructor(@Inject(HttpClient) private httpClient: HttpClient) {}

  private baseUrl = 'http://localhost:8080';
  // private baseUrl = 'http://geniusscraper.us-east-2.elasticbeanstalk.com';

  download() {
    return this.httpClient.get(this.baseUrl + '/getTemp', {
      observe: 'response',
      responseType: 'blob',
    });
  }

  clean() {
    return this.httpClient.get(this.baseUrl + '/clearTemp');
  }

  clearRecents() {
    return this.httpClient.get(this.baseUrl + '/clearRecents');
  }
}
