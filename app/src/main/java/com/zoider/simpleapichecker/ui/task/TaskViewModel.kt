package com.zoider.simpleapichecker.ui.task

import androidx.lifecycle.ViewModel
import com.zoider.simpleapichecker.database.request.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val baseRepository: BaseRepository): ViewModel() {

}