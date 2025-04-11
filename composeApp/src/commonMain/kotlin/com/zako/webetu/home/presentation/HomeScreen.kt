package com.zako.webetu.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HomeScreenRoot(modifier: Modifier = Modifier) {
    HomeScreen(modifier)
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val navItems = listOf("Songs", "Artists", "Playlists")
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            navItems.forEachIndexed { index, navItem ->
                item(
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = navItem) },
                    label = { Text(navItem) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index }
                )
            }
        },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier,
                text = "Current NavigationSuiteTyp ,\nit is ",
                textAlign = TextAlign.Center
            )
        }
    }
}
