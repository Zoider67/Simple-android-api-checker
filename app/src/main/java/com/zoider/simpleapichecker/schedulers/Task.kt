package com.zoider.simpleapichecker.schedulers

interface Task {

    suspend fun execute()
}