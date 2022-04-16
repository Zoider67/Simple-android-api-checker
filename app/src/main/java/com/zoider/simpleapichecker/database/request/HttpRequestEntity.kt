package com.zoider.simpleapichecker.database.request

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zoider.simpleapichecker.commons.HttpMethod

@Entity
data class HttpRequestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val method: HttpMethod,
    val url: String,
)

