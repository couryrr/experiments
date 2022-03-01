import { Component, Input, OnInit } from '@angular/core';
import { Person } from '../person/person';
import { PersonComponent } from '../person/person.component';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.scss']
})
export class PersonListComponent implements OnInit {

  @Input() people: Person[] = new Array<Person>();

  constructor() { }

  ngOnInit(): void {
  }

}
