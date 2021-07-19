package com.enteprise.kotlinboldchallengev2.utils

fun stringCut(data: String?): String? {
    return if (data != null) {
        if (data.length < 5) {
            data
        } else {
            data.substring(0, 5)
        }
    } else data
}
