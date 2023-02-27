package com.dedycoding.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginReceiveRemote (
    val login: String,
    val password: String
)