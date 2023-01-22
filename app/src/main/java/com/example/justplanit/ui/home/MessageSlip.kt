package com.example.justplanit.ui.home

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class slip(
    val slip_id: Int,
    val advice: String
    )
