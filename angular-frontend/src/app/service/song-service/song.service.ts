import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Artist } from 'src/app/common/artist/artist';
import { Song } from 'src/app/common/song/song';

@Injectable({
  providedIn: 'root',
})
export class SongService {
  private baseUrl = 'http://localhost:8080';
  // private baseUrl = 'http://geniusscraper.us-east-2.elasticbeanstalk.com';
  public songList: Song[] = [];
  public currentPath: string;
  public file: File;

  constructor(@Inject(HttpClient) private httpClient: HttpClient) {}

  getSong(songID: number): Observable<Song> {
    const songUrl = this.baseUrl + '/api/songs?size=10000' + '/' + songID;

    return this.httpClient.get<Song>(songUrl);
  }

  async getSongJSON(link: string) {
    let relevantSnippet = link.split('genius.com/')[1];

    let data = await this.httpClient
      .get(this.baseUrl + '/persistLink/' + relevantSnippet, {
        responseType: 'text',
      })
      .toPromise()
      .then();

    let file = new File([data as BlobPart], 'song.json', {
      type: 'application/json',
    });

    this.file = file;
    const blob = new Blob([data as BlobPart], { type: 'application/json' });
    const url = window.URL.createObjectURL(blob);
    window.open(url);

    return file;
  }

  getSongList(): Observable<Song[]> {
    return this.httpClient
      .get<GetResponseSongs>(this.baseUrl + '/api/songs?size=10000')
      .pipe(map((response) => response._embedded.songs));
  }

  songToString(song: Song): string {
    return song.artist.name + ' - ' + song.title;
  }
}

interface GetResponseSongs {
  _embedded: {
    songs: Song[];
  };
}

interface GetResponseLists {
  _links: {
    artist: String[];
  };
}
