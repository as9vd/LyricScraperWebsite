import { Artist } from "../artist/artist";

export class Song {
    id!: number;
    link!: string;
    artist!: Artist;
}