package com.zoider.simpleapichecker.database.query

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zoider.simpleapichecker.database.HttpMethod

@Entity
data class HttpQuery(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val method: HttpMethod,
    val url: String,
)

