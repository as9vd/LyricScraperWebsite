import { Component, Inject, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { SongService } from 'src/app/service/song-service/song.service';

@Component({
  selector: 'pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css'],
})
export class PieChartComponent implements OnInit {
  chart = [];
  file: File;
  data;

  constructor(@Inject(SongService) private songService: SongService) {}

  ngOnInit(): void {
    this.file = this.songService.file;
    this.data = this.songService.data!;
    this.data = JSON.parse(this.data);
    this.data = this.data[Object.keys(this.data)[0]];

    let words: string[] = [];
    let count: string[] = [];

    for (let item in this.data) {
      let count = this.data[item].count;
      let word = this.data[item].word;

      words.push(word);
      count.push(count);
    }

    this.chart = new Chart('canvas', {
      type: 'pie',
      data: {
        labels: [
          // words
          'Red',
          'Yellow',
          'Blue',
        ],
        datasets: [
          {
            data: [10, 20, 30],
          },
        ],
        hoverOffset: 4,
      },
    });
  }
}
