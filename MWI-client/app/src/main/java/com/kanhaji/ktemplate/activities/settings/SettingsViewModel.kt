package com.kanhaji.ktemplate.activities.settings

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kanhaji.ktemplate.util.MyToast
import com.kanhaji.ktemplate.util.SharedPrefsManager
import com.mwi.util.ipAddr
import com.mwi.util.isValidIp

class SettingsViewModel(
    context: Context
): ViewModel() {

    var isDialogShown by mutableStateOf(false)
        private set

    var isInfoDialogShown by mutableStateOf(false)
        private set

    var isIPDialogShown by mutableStateOf(false)
        private set

    val sharedPrefsManager = SharedPrefsManager(context = context)

    fun onThemeConfirmClick() {
        isDialogShown = false
    }
    fun onThemeCancelClick() {
        isDialogShown = false
    }
    fun onThemeButtonClick() {
        isDialogShown = true
    }

    fun onInfoConfirmClick() {
        isInfoDialogShown = false
    }
    fun onInfoCancelClick() {
        isInfoDialogShown = false
    }
    fun onInfoButtonClick() {
        isInfoDialogShown = true
    }

    fun onIPConfirmClick(context: Context, ip : String) {

        var myIP = ip
        if (myIP.isEmpty()) myIP = "localhost"

        if (isValidIp(myIP)) {
            sharedPrefsManager.saveString("ip", myIP)
            ipAddr = myIP
            MyToast.show(context, "IP address changed to $myIP")
            isIPDialogShown = false
        }
        else MyToast.show(context, "Invalid IP")
    }
    fun onIPCancelClick() {
        isIPDialogShown = false
    }
    fun onIPButtonClick() {
        isIPDialogShown = true
    }

}