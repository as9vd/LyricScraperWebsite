import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { debounceTime, map, Observable, startWith } from 'rxjs';
import { Artist } from 'src/app/common/artist/artist';
import { Song } from 'src/app/common/song/song';
import { ArtistService } from 'src/app/service/artist-service/artist.service';
import { SongService } from 'src/app/service/song-service/song.service';
import { DownloadService } from 'src/app/service/download-service/download.service';
import { Chart } from 'node_modules/chart.js';

@Component({
  selector: 'song-scraper',
  templateUrl: './song-scraper.component.html',
  styleUrls: ['./song-scraper.component.css'],
})
export class SongScraperComponent implements OnInit {
  public songList: Song[] = [];
  public inputForm!: FormGroup;
  public filteredOptions: Observable<Song[]>;

  public inputLink: string;
  public validLink: boolean;

  public jsonButtonPressed: boolean;

  public file: File;
  public chart = [];
  public data;

  public display: boolean = false;

  constructor(
    @Inject(ArtistService) private artistService: ArtistService,
    @Inject(SongService) private songService: SongService,
    @Inject(FormBuilder) private formBuilder: FormBuilder,
    @Inject(DownloadService) private downloadService: DownloadService
  ) {}

  ngOnInit(): void {
    this.songService.getSongList().subscribe((songs) => {
      this.songList = songs;
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

  async onClick() {
    this.jsonButtonPressed = true;
    try {
      let path = this.songService.getSongJSON(this.inputLink);

      await path.then((value) => {
        return value;
      });

      this.validLink = true;

      this.file = this.songService.file;

      this.display = true;
    } catch (exception) {
      this.validLink = false;
    } finally {
      this.downloadService.clearRecents().subscribe();
      await console.log('Valid link: ' + this.validLink);
      await console.log('Display canvas? ' + this.display);
    }
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

  onSubmit() {}

  onDownloadClick() {
    this.downloadService.download();
  }

  testOnClick() {
    this.validLink = !this.validLink;
  }
}
