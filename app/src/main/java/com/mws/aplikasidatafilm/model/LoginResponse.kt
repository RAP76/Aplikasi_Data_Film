package com.mws.aplikasidatafilm.model

data class LoginResponse(
    val access_token: String,
    val `data`: Data,
    val message: String,
    val status_code: Int
)