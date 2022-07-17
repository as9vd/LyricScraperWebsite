import { Artist } from "../artist/artist";

export class Song {
    id!: number;
    link!: string;
    artist!: Artist;
    _links: any;
    title!: string;
}