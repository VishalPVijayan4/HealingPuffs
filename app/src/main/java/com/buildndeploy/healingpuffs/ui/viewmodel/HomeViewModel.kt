package com.buildndeploy.healingpuffs.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    // State will be connected to repository later
    val lastUrge: String = "45m ago"
    val lastSmoke: String = "2h ago"
}
