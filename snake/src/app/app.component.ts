import { Component } from '@angular/core';
import { Person } from './person/person';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  constructor(){ }

  people : Person[] = new Array<Person>();

  addPerson(firstName: HTMLInputElement, lastName: HTMLInputElement, age: HTMLInputElement): boolean {
    let person = new Person(firstName.value, lastName.value, age.value);
    
    this.people.push(person);

    return false;
  }
  
}
