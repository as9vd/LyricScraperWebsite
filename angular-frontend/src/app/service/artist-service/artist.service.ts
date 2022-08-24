import { Inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Artist } from 'src/app/common/artist/artist';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ArtistService {
  // private baseUrl = 'http://localhost:8080/api/artists';
  private baseUrl = 'http://geniusscraper.us-east-2.elasticbeanstalk.com';

  private fullArtistUrl = 'http://localhost:8080/api/artists?size=1000';

  constructor(@Inject(HttpClient) private httpClient: HttpClient) {}

  getArtist(artistID: number): Observable<Artist> {
    const artistUrl = this.baseUrl + '/api/artists' + '/' + artistID;

    return this.httpClient.get<Artist>(artistUrl);
  }

  getArtistList(): Observable<Artist[]> {
    return (
      this.httpClient
        // .get<GetResponse>(this.fullArtistUrl)
        .get<GetResponse>(this.baseUrl + '/api/artists?size=1000')
        .pipe(map((response) => response._embedded.artists))
    );
  }
}

interface GetResponse {
  _embedded: {
    artists: Artist[];
  };
}
