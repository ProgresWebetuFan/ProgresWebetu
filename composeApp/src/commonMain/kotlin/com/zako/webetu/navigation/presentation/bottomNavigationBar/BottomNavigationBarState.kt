package com.zako.webetu.navigation.presentation.bottomNavigationBar

import com.zako.webetu.navigation.model.BottomNavigationBarItem

data class BottomNavigationBarState(
    val items: List<BottomNavigationBarItem>,
    val selectedItem: BottomNavigationBarItem,
)
