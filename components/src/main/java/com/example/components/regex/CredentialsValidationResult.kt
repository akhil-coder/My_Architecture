package com.example.components.regex

sealed class CredentialsValidationResult {

  object InvalidEmail : CredentialsValidationResult()

  object InvalidPassword : CredentialsValidationResult()

  object Valid : CredentialsValidationResult()
}