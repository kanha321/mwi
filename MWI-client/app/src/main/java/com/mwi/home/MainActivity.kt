package com.mwi.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.kanhaji.ktemplate.ui.theme.KTemplateTheme
import com.kanhaji.ktemplate.util.SharedPrefsManager
import com.kanhaji.ktemplate.util.ThemeType
import com.kanhaji.ktemplate.util.darkTheme
import com.kanhaji.ktemplate.util.isDarkTheme
import com.kanhaji.ktemplate.util.isDynamicColor
import com.kanhaji.ktemplate.util.isSystemTheme
import com.kanhaji.ktemplate.util.lightTheme
import com.kanhaji.ktemplate.util.sharedPrefsIsDynamicColor
import com.kanhaji.ktemplate.util.sharedPrefsThemeType
import com.kanhaji.ktemplate.util.systemTheme
import com.kanhaji.ktemplate.util.themeHeader
import com.kanhaji.ktemplate.util.themeType
import com.mwi.util.ipAddr
import com.mwi.util.updateUrl
import com.mwi.util.url

//const val ip = "192.168.227.86"
//const val url = "http://$ip:3000/videos/"


class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var sharedPrefsManager: SharedPrefsManager

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefsManager = SharedPrefsManager(this)

        updateUrl(sharedPrefsManager.getString("ip", "172.31.93.2"))
        Log.d(TAG, "onCreate: ipAddr: $ipAddr")

        themeType = sharedPrefsManager.getInt(sharedPrefsThemeType, ThemeType.SYSTEM.value)
        when (themeType) {
            ThemeType.LIGHT.value -> {
                isDarkTheme = false
                isSystemTheme = false
                themeHeader = lightTheme
            }

            ThemeType.DARK.value -> {
                isDarkTheme = true
                isSystemTheme = false
                themeHeader = darkTheme
            }

            else -> {
                isSystemTheme = true
                themeHeader = systemTheme
            }
        }

        isDynamicColor = sharedPrefsManager.getBoolean(
            sharedPrefsIsDynamicColor, true
        )


        enableEdgeToEdge()
        setContent {
            KTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    HomeScreen(context = this@MainActivity, url = url) // Pass the context and URL to the Composable
                }
            }
        }
    }
}