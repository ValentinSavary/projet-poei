import { Genre } from './genre';
export class Music {
  public constructor(
    private _id?: number | undefined,
    private _title?: string | undefined,
    private _duration?: number | undefined,
    private _musicFile?: File | undefined,
    private _genre?: Genre | undefined
  ) {}

  /**
   * Getter id
   * @return {number}
   */
  public get id(): number | undefined {
    return this._id;
  }

  /**
   * Getter title
   * @return {string}
   */
  public get title(): string | undefined {
    return this._title;
  }

  /**
   * Getter duration
   * @return {number}
   */
  public get duration(): number | undefined {
    return this._duration;
  }

  /**
   * Getter musicFile
   * @return {File}
   */
  public get musicFile(): File | undefined {
    return this._musicFile;
  }

  /**
   * Getter genre
   * @return {Genre}
   */
  public get genre(): Genre | undefined {
    return this._genre;
  }

  /**
   * Setter id
   * @param {number} value
   */
  public set id(value: number | undefined) {
    this._id = value;
  }

  /**
   * Setter title
   * @param {string} value
   */
  public set title(value: string | undefined) {
    this._title = value;
  }

  /**
   * Setter duration
   * @param {number} value
   */
  public set duration(value: number | undefined) {
    this._duration = value;
  }

  /**
   * Setter musicFile
   * @param {File} value
   */
  public set musicFile(value: File | undefined) {
    this._musicFile = value;
  }

  /**
   * Setter genre
   * @param {Genre} value
   */
  public set genre(value: Genre | undefined) {
    this._genre = value;
  }
}
