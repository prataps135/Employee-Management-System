import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.scss']
})
export class EmployeeDetailsComponent implements OnInit{
  id:number;
  employee:Employee;

  constructor(
    private employeeService:EmployeeService,
    private activatedRoute:ActivatedRoute
  ){}

  ngOnInit(): void {
      this.id = this.activatedRoute.snapshot.params['id'];

      this.employeeService.getEmployeeById(this.id).subscribe(
        data => {
          console.log(data);
          this.employee = data;
        },
        err => console.log("This is error ",err)
      );
  }
}
