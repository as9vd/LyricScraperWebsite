import { Component, OnInit } from '@angular/core';
import { saveAs } from 'file-saver';
import { FileData } from 'src/app/common/fileData/file-data';
import { DownloadService } from 'src/app/service/download-service/download.service';

@Component({
  selector: 'app-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css'],
})
export class FilesComponent implements OnInit {
  fileList?: FileData[];

  constructor(private downloadService: DownloadService) {}

  ngOnInit(): void {}

  public downloadFile(): void {
    this.downloadService.download().subscribe((response) => {
      let fileName = response.headers.get('Content-Disposition');
      let blob: Blob = response.body as Blob;
      let a = document.createElement('a');
      a.download = 'song.json';
      a.href = window.URL.createObjectURL(blob);
      a.click();
    });
  }
}
