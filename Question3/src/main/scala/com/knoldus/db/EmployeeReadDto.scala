package com.knoldus.db

import com.knoldus.models.Employee
import scala.collection.immutable.HashMap

class EmployeeReadDto {

  val sachinEmployee: Employee = Employee("sachin","kumar",22,15000,"Intern","knoldus","sachin.kumar@knoldus.com")
  val anjaliEmployee: Employee = Employee("anjali","pandey",21,30000,"Software Engineer","Philips","anjali.pandey@philips.com")

  val employees: HashMap[String, Employee] = HashMap("sachin" -> sachinEmployee, "anjali" -> anjaliEmployee)
  def getEmployeeByName(name: String): Option[Employee] = employees.get(name)

}
