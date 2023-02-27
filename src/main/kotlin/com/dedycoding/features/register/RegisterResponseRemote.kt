package com.dedycoding.features.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseRemote(
    val token: String
)