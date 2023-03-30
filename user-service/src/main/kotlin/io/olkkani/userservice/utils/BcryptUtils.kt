package io.olkkani.userservice.utils

import at.favre.lib.crypto.bcrypt.BCrypt

object BcryptUtils {

    fun  hash(password: String) =
        BCrypt.withDefaults().hashToString(12, password.toCharArray())

    fun verity(password: String, hashedPassword:String) =
        BCrypt.verifyer().verify(password.toCharArray(),hashedPassword).verified

}