package com.dedycoding.utils

import com.dedycoding.database.tokens.Tokens

object TokenCheck {

    fun isTokenValid(token: String): Boolean = Tokens.fetchTokens().firstOrNull { it.token == token } != null

    fun isTokenAdmin(token: String): Boolean = token == "ba98dd28-9483-48c8-ae0a-83d08410d348"
}