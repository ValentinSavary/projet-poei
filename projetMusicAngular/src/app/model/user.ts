import { AccountType } from './accountType';

export class User {
  public constructor(
    private _id?: number | undefined,
    private _username?: string | undefined,
    private _login?: string | undefined,
    private _password?: string | undefined,
    private _accountType?: AccountType | undefined
  ) {}

  /**
   * Getter id
   * @return {number}
   */
  public get id(): number | undefined {
    return this._id;
  }

  /**
   * Getter username
   * @return {string}
   */
  public get username(): string | undefined {
    return this._username;
  }

  /**
   * Getter login
   * @return {string}
   */
  public get login(): string | undefined {
    return this._login;
  }

  /**
   * Getter password
   * @return {string}
   */
  public get password(): string | undefined {
    return this._password;
  }

  /**
   * Getter accountType
   * @return {AccountType}
   */
  public get accountType(): AccountType | undefined {
    return this._accountType;
  }

  /**
   * Setter id
   * @param {number} value
   */
  public set id(value: number | undefined) {
    this._id = value;
  }

  /**
   * Setter username
   * @param {string} value
   */
  public set username(value: string | undefined) {
    this._username = value;
  }

  /**
   * Setter login
   * @param {string} value
   */
  public set login(value: string | undefined) {
    this._login = value;
  }

  /**
   * Setter password
   * @param {string} value
   */
  public set password(value: string | undefined) {
    this._password = value;
  }

  /**
   * Setter accountType
   * @param {AccountType} value
   */
  public set accountType(value: AccountType | undefined) {
    this._accountType = value;
  }
}
