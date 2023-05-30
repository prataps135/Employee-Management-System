import { Component } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.scss']
})
export class UpdateEmployeeComponent {
  employee: Employee;
  id: number;

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.employee = new Employee();
    this.id = this.activatedRoute.snapshot.params['id'];

    this.employeeService.getEmployeeById(this.id).subscribe(
      data => this.employee = data,
      err => console.log("This is error ", err)
    );

  }

  goToEmployeeList(){
    this.router.navigate(['/employees']);
  }

  onSubmit() {
    this.employeeService.updateEmployee(this.id, this.employee).subscribe(
      data => {
        console.log(data);
        this.goToEmployeeList();
      },
      err => console.log("This is error ", err),
    );
  }
}
