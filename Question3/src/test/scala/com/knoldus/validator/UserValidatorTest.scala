package com.knoldus.validator

import com.knoldus.db.CompanyReadDto
import com.knoldus.models.{Company, User}
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec

class UserValidatorTest extends AnyFlatSpec {

  val anjaliUser: User = User("anjali","pandey",22,"Google","anjali.pandey@google.in")
  val knoldusCompany: Company = Company("Knoldus", "knoldus@gmail.com", "Noida")

  val mockedCompanyReadDto = mock[CompanyReadDto]
  val mockedEmailvalidator = mock[EmailValidator]

  val userValidator = new UserValidator(mockedCompanyReadDto,mockedEmailvalidator)

  "User" should "be valid" in {

    when(mockedCompanyReadDto.getCompanyByName(anjaliUser.companyName)) thenReturn(Option(knoldusCompany))
    when(mockedEmailvalidator.emailIdIsValid(anjaliUser.emailId)) thenReturn(true)

    val result = userValidator.userIsValid(anjaliUser)
    assert(result)
  }

  "User" should "be invalid because his email is not valid" in {

    when(mockedCompanyReadDto.getCompanyByName(anjaliUser.companyName)) thenReturn(Option(knoldusCompany))
    when(mockedEmailvalidator.emailIdIsValid(anjaliUser.emailId)) thenReturn(false)

    val result = userValidator.userIsValid(anjaliUser)
    assert(!result)
  }

  "User" should "be invalid because his company does not exists in DB" in {

    when(mockedCompanyReadDto.getCompanyByName(anjaliUser.companyName)) thenReturn(None)
    when(mockedEmailvalidator.emailIdIsValid(anjaliUser.emailId)) thenReturn(true)

    val result = userValidator.userIsValid(anjaliUser)
    assert(!result)
  }

  "User" should "be invalid because his email id is not valid and his company does not exists in DB" in {

    when(mockedCompanyReadDto.getCompanyByName(anjaliUser.companyName)) thenReturn(None)
    when(mockedEmailvalidator.emailIdIsValid(anjaliUser.emailId)) thenReturn(false)

    val result = userValidator.userIsValid(anjaliUser)
    assert(!result)
  }

}
