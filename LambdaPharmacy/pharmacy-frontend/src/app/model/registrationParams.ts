export class RegistrationParams {

  private email: string;
  private username:string;
  private password: string;
  private firstName: string;
  private lastName: string;
  private address: string;
  private phoneNumber: string;

  constructor(email: string, username: string, password: string, firstName: string, lastName: string,
              address: string,  phoneNumber:string) {
    this.email = email;
    this.username= username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phoneNumber = phoneNumber;

  }


}
