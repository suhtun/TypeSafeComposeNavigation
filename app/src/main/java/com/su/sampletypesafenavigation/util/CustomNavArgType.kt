package com.su.sampletypesafenavigation.util

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.su.sampletypesafenavigation.Grade
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavArgType {

    val GradeType = object : NavType<Grade>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): Grade? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Grade {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun put(bundle: Bundle, key: String, value: Grade) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}