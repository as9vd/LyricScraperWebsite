import { Component, OnInit } from '@angular/core';
import { saveAs } from 'file-saver';
import { FileData } from 'src/app/common/fileData/file-data';
import { DownloadService } from 'src/app/service/download-service/download.service';

@Component({
  selector: 'app-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css']
})
export class FilesComponent implements OnInit {

  fileList?: FileData[];

  constructor(private downloadService: DownloadService) { }

  ngOnInit(): void {
    this.getFileList();
  }

  getFileList(): void {
    this.downloadService.list().subscribe(result => {
      this.fileList = result;
    })
  }

  downloadFile(fileData: FileData): void {
    this.downloadService
      .download(fileData.fileName)
      .subscribe(blob => saveAs(blob, fileData.fileName))
  }

}
