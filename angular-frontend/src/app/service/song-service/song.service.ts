import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Artist } from 'src/app/common/artist/artist';
import { Song } from 'src/app/common/song/song';

@Injectable({
  providedIn: 'root'
})
export class SongService {
  private baseUrl = 'http://localhost:8080/api/songs?size=10000';

  public songList: Song[] = [];

  constructor(@Inject(HttpClient) private httpClient: HttpClient) {}

  getSong(songID: number): Observable<Song> {
    const songUrl = this.baseUrl + "/" + songID;

    return this.httpClient.get<Song>(songUrl);
  } 

  getSongList(): Observable<Song[]> {
    return this.httpClient.get<GetResponseSongs>(this.baseUrl).pipe(
      map(response => response._embedded.songs)
    );
  }

  songToString(song: Song): string {
    return song.artist.name + " - " + song.title;
  }

}

interface GetResponseSongs {
  _embedded: {
    songs: Song[];
  }
}

interface GetResponseLists {
  _links: {
    artist: String[];
  }
}