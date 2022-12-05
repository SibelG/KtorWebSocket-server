package com.autumnsun.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class message(
    val isSuccess: Boolean,
    val errorMessage: String,
    val code: Int)