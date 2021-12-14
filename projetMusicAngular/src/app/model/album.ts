import { Byte } from '@angular/compiler/src/util';
import { Artist } from './artist';
import { Music } from './music';
export class Album {
  public constructor(
    private _id?: number | undefined,
    private _name?: string | undefined,
    private _year?: string | undefined,
    private _cover?: Byte[] | undefined,
    private _musics?: Music[] | undefined,
    private _artists?: Artist[] | undefined
  ) {}

  /**
   * Getter id
   * @return {number}
   */
  public get id(): number | undefined {
    return this._id;
  }

  /**
   * Getter name
   * @return {string}
   */
  public get name(): string | undefined {
    return this._name;
  }

  /**
   * Getter year
   * @return {string}
   */
  public get year(): string | undefined {
    return this._year;
  }

  /**
   * Getter cover
   * @return {File}
   */
  public get cover(): Byte[] | undefined {
    return this._cover;
  }

  /**
   * Getter musics
   * @return {Music[]}
   */
  public get musics(): Music[] | undefined {
    return this._musics;
  }

  /**
   * Getter artists
   * @return {Artist[]}
   */
  public get artists(): Artist[] | undefined {
    return this._artists;
  }

  /**
   * Setter id
   * @param {number} value
   */
  public set id(value: number | undefined) {
    this._id = value;
  }

  /**
   * Setter name
   * @param {string} value
   */
  public set name(value: string | undefined) {
    this._name = value;
  }

  /**
   * Setter year
   * @param {string} value
   */
  public set year(value: string | undefined) {
    this._year = value;
  }

  /**
   * Setter cover
   * @param {string} value
   */
  public set cover(value: Byte[] | undefined) {
    this._cover = value;
  }

  /**
   * Setter musics
   * @param {Music[]} value
   */
  public set musics(value: Music[] | undefined) {
    this._musics = value;
  }

  /**
   * Setter artists
   * @param {Artist[]} value
   */
  public set artists(value: Artist[] | undefined) {
    this._artists = value;
  }
}
