package com.kanhaji.ktemplate.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

var isDarkTheme by mutableStateOf(true)
var isSystemTheme by mutableStateOf(true)
var isDynamicColor by mutableStateOf(false)

var themeType by mutableIntStateOf(0)

var systemTheme = "System"
var lightTheme = "Light"
var darkTheme = "Dark"

var themeHeader by mutableStateOf(systemTheme)


// SharedPrefs

var sharedPrefsName = "com.mwi.preferences"
var sharedPrefsThemeType = "themeType"
var sharedPrefsIsDynamicColor = "isDynamicColor"

enum class ThemeType(val value: Int) {
    LIGHT(0),
    DARK(1),
    SYSTEM(2)
}