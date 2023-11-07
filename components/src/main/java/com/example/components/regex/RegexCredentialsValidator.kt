package com.example.components.regex

import java.util.regex.Pattern

class RegexCredentialsValidator {

    private companion object {

        private const val USER_NAME_REGEX = """^[a-zA-Z0-9_-]{3,16}${'$'}"""

        private const val EMAIL_REGEX =
            """[a-zA-Z0-9+._%\-]{1,64}@[a-zA-Z0-9][a-zA-Z0-9\-]{1,64}(\.[a-zA-Z0-9][a-zA-Z0-9\-]{1,25})"""

        private const val PASSWORD_REGEX =
            """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=\-]).{8,}$"""
    }

    private val namePattern = Pattern.compile(USER_NAME_REGEX)
    private val emailPattern = Pattern.compile(EMAIL_REGEX)
    private val passwordPattern = Pattern.compile(PASSWORD_REGEX)


    fun validateValue(
        email: String? = null, password: String? = null, name: String? = null
    ): Boolean {
        when {
            !email.isNullOrEmpty() -> {
                if (emailPattern.matcher(email).matches()) {
                    return true
                }
            }

            !password.isNullOrEmpty() -> {
                if (passwordPattern.matcher(password).matches()) {
                    return true
                }
            }

            !name.isNullOrEmpty() -> {
                if (namePattern.matcher(name).matches()) {
                    return true
                }
            }
        }
        return false
    }

}