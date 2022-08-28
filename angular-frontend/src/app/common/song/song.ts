import { Artist } from '../artist/artist';

// This was moreso useful when I was trying to do the autocomplete thing with the input bar, but I moved past that as of right now.
// Maybe in the future.
export class Song {
  id!: number;
  link!: string;
  artist!: Artist;
  _links: any;
  title!: string;
}
