import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { RecentLink } from 'src/app/common/recentLink/recent-link';

@Injectable({
  providedIn: 'root',
})
export class RecentService {
  private baseUrl = 'http://localhost:8080/api/recentLinks';
  public recentList: RecentLink[] = [];

  constructor(@Inject(HttpClient) private httpClient: HttpClient) {}

  getRecents(): Observable<RecentLink[]> {
    return this.httpClient
      .get<GetRecentLinks>(this.baseUrl)
      .pipe(map((response) => response._embedded.recentLinks));
  }
}

interface GetRecentLinks {
  _embedded: {
    recentLinks: RecentLink[];
  };
}
