package com.zako.webetu.navigation.model

import com.zako.webetu.utils.BaseString
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Resource
import kotlin.reflect.KClass


data class BottomNavigationBarItem(
    val enabledIcon  : DrawableResource,
    val disabledIcon : DrawableResource,
    val label : BaseString  ,
    val route : KClass<*>,
)
