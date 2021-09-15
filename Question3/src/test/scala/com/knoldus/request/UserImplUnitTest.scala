package com.knoldus.request
import com.knoldus.models.User
import com.knoldus.validator.UserValidator
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec

class UserImplUnitTest extends AnyFlatSpec {

  val mockedUserValidator = mock[UserValidator]
  val SachinUser: User = User("sachin","kumar",22,"Knoldus","sachin.kumar@knoldus.com")

  "User" should "be created" in {
    val userImpl = new UserImpl(mockedUserValidator)

    when(mockedUserValidator.userIsValid(SachinUser)) thenReturn(true)
    val result = userImpl.createUser(SachinUser)
    assert(!result.isEmpty)
  }

  "User" should "not be created" in {
    val userImpl = new UserImpl(mockedUserValidator)

    when(mockedUserValidator.userIsValid(SachinUser)) thenReturn(false)
    val result = userImpl.createUser(SachinUser)
    assert(result.isEmpty)
  }

}
