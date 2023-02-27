package com.dedycoding.cache

import com.dedycoding.features.register.RegisterReceiveRemote
import com.dedycoding.features.token.TokenCache


object InMemoryCache {
    val userList: MutableList<RegisterReceiveRemote> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()
}