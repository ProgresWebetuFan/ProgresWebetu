package com.zako.webetu.navigation.presentation.bottomNavigationBar

import androidx.lifecycle.ViewModel
import com.zako.webetu.navigation.model.BottomNavigationBarItem
import com.zako.webetu.navigation.model.HomeScreen
import com.zako.webetu.utils.BaseString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import webetu.composeapp.generated.resources.Res
import webetu.composeapp.generated.resources.home
import webetu.composeapp.generated.resources.home_filled
import webetu.composeapp.generated.resources.home_outline

class MainGraphViewModel : ViewModel() {
    private val bottomNavigationBarItems = listOf(
        BottomNavigationBarItem(
            enabledIcon = Res.drawable.home_filled ,
            disabledIcon = Res.drawable.home_outline ,
            label = BaseString.ResString(Res.string.home) ,
            route = HomeScreen::class ,
        )
    )

    private val _bottomNavigationBarState = MutableStateFlow(
        BottomNavigationBarState(
        items = bottomNavigationBarItems,
        selectedItem = bottomNavigationBarItems.first(),
    )
    )

    val bottomNavigationBarState = _bottomNavigationBarState.asStateFlow()


    fun action (
        action: MainGraphActions
    ) {
        when (action) {
            is MainGraphActions.BottomNavigationBarActions.OnItemSelected -> {
                val selectedItem = action.item
                if (selectedItem != bottomNavigationBarState.value.selectedItem) {
                    _bottomNavigationBarState.update {
                        it.copy(
                            selectedItem = selectedItem
                        )
                    }
                }
            }
        }
    }
}