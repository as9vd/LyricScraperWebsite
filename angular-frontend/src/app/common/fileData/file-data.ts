// An outdated class. Was necessary prior when I was transferring files via the temp directory, but unfortunately, that's not good enough for AWS.
// So now we use MySQL's JSON feature.
export class FileData {
  fileName?: string;
  contentType?: string;
  size?: number;
}
