export class Artist {
  public constructor(
    private _id?: number | undefined,
    private _name?: string | undefined,
    private _country?: string | undefined
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
   * Getter country
   * @return {string}
   */
  public get country(): string | undefined {
    return this._country;
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
   * Setter country
   * @param {string} value
   */
  public set country(value: string | undefined) {
    this._country = value;
  }
}
