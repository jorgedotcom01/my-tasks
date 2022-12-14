package com.jorgedotcom.mytasks.view.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.jorgedotcom.mytasks.data.repository.ITasksRepository

class TasksViewModel(tasksRepository: ITasksRepository) : ViewModel() {

    private var _tasksUiState = MutableLiveData(TasksUiState())
    val tasksUiState: LiveData<TasksUiState>
        get() = _tasksUiState

    init {
        _tasksUiState = tasksRepository.getAllTasksLiveData().switchMap {
            val value = tasksUiState.value?.copy(tasks = it) ?: TasksUiState(tasks = it)
            MutableLiveData(value)
        } as MutableLiveData<TasksUiState>
    }
}
