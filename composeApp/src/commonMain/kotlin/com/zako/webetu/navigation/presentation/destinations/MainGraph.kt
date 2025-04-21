package com.zako.webetu.navigation.presentation.destinations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.zako.webetu.auth.user.UserController
import com.zako.webetu.navigation.model.BottomNavigationBarItem
import com.zako.webetu.navigation.presentation.bottomNavigationBar.BottomNavigationBarState
import com.zako.webetu.navigation.presentation.bottomNavigationBar.MainGraphActions
import com.zako.webetu.navigation.presentation.bottomNavigationBar.MainGraphViewModel
import com.zako.webetu.utils.asStringCompose
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun MainGraphRoot(modifier: Modifier = Modifier) {

    val viewModel  = koinViewModel<MainGraphViewModel> ()
    val state by viewModel.bottomNavigationBarState.collectAsState()


    MainGraph(
        modifier = modifier ,
        state = state ,
        onItemSelected = { item ->
            viewModel.action(
                MainGraphActions.BottomNavigationBarActions.OnItemSelected(item)
            )
        }
    )

}


@Composable
fun MainGraph(
    modifier: Modifier = Modifier,
    state : BottomNavigationBarState,
    onItemSelected : (BottomNavigationBarItem) -> Unit = {},
) {

    // todo : implement the real screen and logic
    var uuid by remember {
        mutableStateOf<String?>(null )
    }

    LaunchedEffect(true){
        uuid = UserController.getUserAuth()?.uuid
    }
    NavigationSuiteScaffold(
        modifier = modifier,
        navigationSuiteItems = {
            state.items.forEachIndexed { index, navItem ->
                item(
                    icon = { Icon(
                        painter = painterResource(navItem.disabledIcon) ,
                        contentDescription = navItem.label.asStringCompose()
                    ) },
                    label = { Text(text = navItem.label.asStringCompose() ) },
                    selected = navItem == state.selectedItem,
                    onClick = {
                        onItemSelected(navItem)
                    },
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier,
                text = "Hello $uuid",
                textAlign = TextAlign.Center
            )
        }
    }
}