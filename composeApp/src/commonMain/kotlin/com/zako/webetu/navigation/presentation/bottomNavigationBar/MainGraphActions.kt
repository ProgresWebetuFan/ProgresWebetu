package com.zako.webetu.navigation.presentation.bottomNavigationBar

import com.zako.webetu.navigation.model.BottomNavigationBarItem

sealed interface MainGraphActions {
    sealed class  BottomNavigationBarActions : MainGraphActions {
        data class OnItemSelected(val item: BottomNavigationBarItem) : MainGraphActions
    }
}