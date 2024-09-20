package com.mwi.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


var ipAddr by mutableStateOf("")
var url by mutableStateOf("http://$ipAddr:3000/videos/")

fun updateUrl(ip: String) {
    ipAddr = ip
    url = "http://$ip:3000/videos/"
}

fun isValidIp(ip: String): Boolean {
    val regex =
        Regex(pattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")
    return regex.matches(ip) || ip == "localhost"
}