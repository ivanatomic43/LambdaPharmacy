
export class UserProfileDTO {
  constructor(
    public id: number,
    public firstName: string,
    public lastName: string,
    public username: string,
    public password: string,
    public email: string,
    public address: string,
    public phoneNumber: string,
    public role : string


  ) {}



}
