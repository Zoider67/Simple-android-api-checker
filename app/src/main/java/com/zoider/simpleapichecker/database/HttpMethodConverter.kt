package com.zoider.simpleapichecker.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class HttpMethodConverter {
    @TypeConverter
    fun toHttpMethod(value: Int): HttpMethod {
        return enumValues<HttpMethod>()[value]
    }

    @TypeConverter
    fun fromHttpMethod(httpMethod: HttpMethod): Int{
        return httpMethod.ordinal
    }
}