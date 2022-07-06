import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Song } from 'src/app/common/song/song';

@Injectable({
  providedIn: 'root'
})
export class SongService {
  private baseUrl = 'http://localhost:8080/api/songs';

  constructor(@Inject(HttpClient) private httpClient: HttpClient) {}

  getSong(songID: number): Observable<Song> {
    const artistUrl = this.baseUrl + "/" + songID;

    return this.httpClient.get<Song>(artistUrl);
  } 

}