package com.example.justplanit.ui.achievements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AchievementsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is achievement Fragment"
    }
    val text: LiveData<String> = _text
}