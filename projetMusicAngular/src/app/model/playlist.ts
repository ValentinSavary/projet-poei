import { Music } from './music';
export class Playlist {
  public constructor(
    private _id?: number | undefined,
    private _name?: string | undefined,
    private _typePrivate?: boolean | undefined,
    private _musics?: Music[] | undefined
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
   * Getter typePrivate
   * @return {boolean}
   */
  public get typePrivate(): boolean | undefined {
    return this._typePrivate;
  }

  /**
   * Getter musics
   * @return {Music[]}
   */
  public get musics(): Music[] | undefined {
    return this._musics;
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
   * Setter typePrivate
   * @param {boolean} value
   */
  public set typePrivate(value: boolean | undefined) {
    this._typePrivate = value;
  }

  /**
   * Setter musics
   * @param {Music[]} value
   */
  public set musics(value: Music[] | undefined) {
    this._musics = value;
  }
}
