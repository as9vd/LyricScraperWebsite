import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FileData } from 'src/app/common/fileData/file-data';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DownloadService {
  constructor(@Inject(HttpClient) private httpClient: HttpClient) {}

  download(file: string | undefined): Observable<Blob> {
    return this.httpClient.get(`${environment.baseUrl}/files/${file}`, {
      responseType: 'blob'
    })
  }

  list(): Observable<FileData[]> {
    return this.httpClient.get<FileData[]>(`${environment.baseUrl}/files`);
  }
}