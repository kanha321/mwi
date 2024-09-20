package com.kanhaji.ktemplate.activities.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.kanhaji.ktemplate.ui.theme.KTemplateTheme

class SettingsActivity : ComponentActivity() {

    // view model factory with context
    private val viewModel: SettingsViewModel by viewModels {
        SettingsViewModelFactory(context = this)
    }
//    private lateinit var sharedPrefsManager: SharedPrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        sharedPrefsManager = SharedPrefsManager(this)
//
//        themeType = sharedPrefsManager.getInt(sharedPrefsThemeType, 2)
//        when (themeType) {
//            ThemeType.LIGHT.value -> {
//                isDarkTheme = false
//                isSystemTheme = false
//                themeHeader = lightTheme
//            }
//
//            ThemeType.DARK.value -> {
//                isDarkTheme = true
//                isSystemTheme = false
//                themeHeader = darkTheme
//            }
//
//            else -> {
//                isSystemTheme = true
//                themeHeader = systemTheme
//            }
//        }
//
//        isDynamicColor = sharedPrefsManager.getBoolean(
//            sharedPrefsIsDynamicColor,
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
//        )

        setContent {
            KTemplateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SettingsScreen(
                        viewModel = viewModel,
                        context = this
                    )
                }
            }
        }
    }
}
