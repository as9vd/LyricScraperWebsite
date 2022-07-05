import { Inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Artist } from 'src/app/common/artist/artist';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ArtistService {
  private baseUrl = 'http://localhost:8080/api/artists';

  private fullArtistUrl = 'http://localhost:8080/api/artists?size=1000';

  constructor(@Inject(HttpClient) private httpClient: HttpClient) {}

  getArtist(artistID: number): Observable<Artist> {
    const artistUrl = this.baseUrl + "/" + artistID;

    return this.httpClient.get<Artist>(artistUrl);
  } 

  getArtistList(): Observable<Artist[]> {
    return this.httpClient.get<GetResponse>(this.fullArtistUrl).pipe(
      map(response => response._embedded.artists)
    );
  }

}

interface GetResponse {
  _embedded: {
    artists: Artist[];
  }
}