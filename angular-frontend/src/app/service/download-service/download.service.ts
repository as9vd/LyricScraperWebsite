import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { FileData } from 'src/app/common/fileData/file-data';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class DownloadService {
  constructor(@Inject(HttpClient) private httpClient: HttpClient) {}

  download() {
    return this.httpClient.get('http://localhost:8080/getTemp', {
      observe: 'response',
      responseType: 'blob',
    });
  }

  clean() {
    return this.httpClient.get('http://localhost:8080/clearTemp');
  }

  clearRecents() {
    return this.httpClient.get('http://localhost:8080/clearRecents');
  }
}
