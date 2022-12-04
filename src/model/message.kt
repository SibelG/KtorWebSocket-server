package com.autumnsun.model

import java.lang.Error
import javax.lang.model.type.ErrorType

data class message(
    val isSuccess: Boolean,
    val errorMessage: String,
    val code: Int)