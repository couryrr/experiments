export class Person {
    firstName: string;
    lastName: string;
    age: number | string;

    constructor(firstName: string, lastName: string, age: number |string) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}