package com.mwi.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.random.Random


var ipAddr by mutableStateOf("")
var url by mutableStateOf("http://$ipAddr:3000/videos/")
var password by mutableStateOf("")

var isNsfwShowing by mutableStateOf(false)
var autoRefresh by mutableStateOf(false)
var videoListState by mutableStateOf<List<String>>(emptyList())

var randomStrings = arrayOf(
    "What are you doing?",
    "This ain't gonna work",
    "Go away",
    "Self destruct initiated",
    "Android is not for you",
    "You should use Nokia 3310",
)

private var shuffledStrings = mutableListOf<String>()

fun getNextRandomString(): String {
    if (shuffledStrings.isEmpty()) {
        shuffledStrings = randomStrings.toMutableList()
        shuffledStrings.shuffle(Random(System.currentTimeMillis()))
    }
    return shuffledStrings.removeAt(0)
}

fun updateUrl(ip: String) {
    ipAddr = ip
    url = "http://$ip:3000/videos/"
}

fun isValidIp(ip: String): Boolean {
    val regex = Regex(pattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")
    return regex.matches(ip) || ip == "localhost"
}