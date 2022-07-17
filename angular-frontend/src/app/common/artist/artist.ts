export class Artist {
    id!: number;
    name!: string;

    toString(): string {
        return this.id + ": " + this.name;
    }
}