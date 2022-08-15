import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { map, Observable, startWith } from 'rxjs';
import { RecentLink } from 'src/app/common/recentLink/recent-link';
import { Song } from 'src/app/common/song/song';
import { ArtistService } from 'src/app/service/artist-service/artist.service';
import { RecentService } from 'src/app/service/recent-service/recent-service.service';
import { SongService } from 'src/app/service/song-service/song.service';

@Component({
  selector: 'app-popular-songs',
  templateUrl: './popular-songs.component.html',
  styleUrls: ['./popular-songs.component.css'],
})
export class PopularSongsComponent implements OnInit {
  public songList: Song[] = [];
  public recentsList: RecentLink[] = [];
  public inputForm!: FormGroup;
  public filteredOptions: Observable<Song[]>;

  inputLink: string;

  constructor(
    @Inject(ArtistService) private artistService: ArtistService,
    @Inject(SongService) private songService: SongService,
    @Inject(FormBuilder) private formBuilder: FormBuilder,
    @Inject(RecentService) private recentService: RecentService
  ) {}

  ngOnInit(): void {
    this.songService.getSongList().subscribe((songs) => {
      this.songList = songs;
    });

    this.recentService.getRecents().subscribe((recents) => {
      this.recentsList = recents;
    });

    this.inputForm = this.formBuilder.group({
      searchBar: new FormControl(''),
    });

    this.filteredOptions = this.inputForm.get('searchBar')!.valueChanges.pipe(
      startWith(''),
      map((value) => {
        const name = typeof value === 'string' ? value : value?.name;
        return name ? this._filter(name as string) : this.songList.slice();
      })
    );
  }

  private _filter(name: string): Song[] {
    const filterValue = name.toLowerCase();

    return this.songList.filter((song) =>
      song.title.toLowerCase().includes(filterValue)
    );
  }

  displaySong(song: Song): string {
    return song.title;
  }

  onClick(link) {
    window.open(link);
  }

  changeValue(value: any) {
    console.log(value.link);
  }
}
