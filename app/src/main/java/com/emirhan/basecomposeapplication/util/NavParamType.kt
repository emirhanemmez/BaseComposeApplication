package com.emirhan.basecomposeapplication.util

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import javax.inject.Inject

class NavArgumentType<T: Parcelable> @Inject constructor(
    private val gson: Gson,
    private val clazz: Class<T>
): NavType<T>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): T? = bundle.getParcelable(key)

    override fun parseValue(value: String): T {
        return gson.fromJson(value, clazz)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }
}
