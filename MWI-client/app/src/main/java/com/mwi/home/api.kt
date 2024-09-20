package com.mwi.home

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

fun getVideos(context: Context, url: String, onResult: (List<String>) -> Unit) {
    Log.d("TAG", "getVideos: $url")
    val requestQueue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(Request.Method.GET, url, { response ->
        // Parse the response as a JSON array
        val videoList = parseVideoList(response)
        onResult(videoList) // Pass the list to the callback
    }, { error ->
        Log.e("VolleyError", "Failed to fetch videos: ${error.message}")
    })

    requestQueue.add(stringRequest)
}

fun parseVideoList(response: String): List<String> {
    val videoList = mutableListOf<String>()

    try {
        // Convert the response to a JSONArray
        val jsonArray = JSONArray(response)

        // Iterate through the array and add each string to the video list
        for (i in 0 until jsonArray.length()) {
            videoList.add(jsonArray.getString(i))
        }
    } catch (e: Exception) {
        Log.e("ParseError", "Failed to parse video list: ${e.message}")
    }

    return videoList
}