package com.example.justplanit.ui.home

import com.squareup.moshi.JsonClass

/*
@JsonClass(generateAdapter = true)
data class Advice(
    val slip: Slip
    )

@JsonClass(generateAdapter = true)
data class Slip(
    val advice: String,
    val id: Int
)
*/
@JsonClass(generateAdapter = true)
class slip(
    val slip_id: Int,
    val advice: String
    )