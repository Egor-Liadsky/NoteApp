package com.lyadsky.noteapp.android.feature.mainNavigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lyadsky.noteapp.common.navigation.ScreenRoute

data class BottomNavigationItem(
    val route: ScreenRoute,
    val iconContentDescription: String
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        ScreenRoute.Main,
        ""
    ),
    BottomNavigationItem(
        ScreenRoute.Profile,
        ""
    ),
)

@Composable
fun BottomBar(
    currentRoute: ScreenRoute,
    onButtonClick: (ScreenRoute) -> Unit
) {
    BottomNavigation(
        modifier = Modifier
            .navigationBarsPadding()
    ) {
        Row {
            bottomNavigationItems.forEach { item ->
                BottomNavigationItem(
                    icon = {
                    },
                    selected = currentRoute == item.route,
                    onClick = {
                        onButtonClick(item.route)
                    },
                    label = {
                        Text(text = item.route.name)
                    }
                )
            }
        }
    }
}
