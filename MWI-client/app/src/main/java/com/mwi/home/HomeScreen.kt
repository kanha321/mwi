package com.mwi.home

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kanhaji.ktemplate.activities.settings.SettingsActivity
import com.kanhaji.ktemplate.composable.Toolbar
import com.kanhaji.ktemplate.util.SharedPrefsManager
import com.mwi.R
import com.mwi.composable.VideoItem
import com.mwi.util.ipAddr
import com.mwi.util.updateUrl
import com.mwi.util.videoListState

@Composable
fun HomeScreen(context: Context, url: String) {
    // Use remember to store the list in Compose's memory

    // Use LaunchedEffect to trigger the API call and update the video list
    LaunchedEffect(Unit) {
        getVideos(context = context, url = url) { videos ->
            videoListState = videos // Update the video list when the data is received
        }
    }

    Column {
        Toolbar(
            context = context,
            showBack = false
        ) {
            IconButton(
                onClick = {
                    videoListState = emptyList()
                    updateUrl(SharedPrefsManager(context).getString("ip", "172.31.93.2"))
                    getVideos(context = context, url = url) { videos ->
                        videoListState = videos
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_refresh_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            IconButton(
                onClick = {
                    context.startActivity(Intent(context, SettingsActivity::class.java))
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_settings_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        // Check if the video list is empty (loading) or not
        if (videoListState.isEmpty()) {
            // Show a loading state until the data is loaded
            Text(text = "Loading videos...", modifier = Modifier.fillMaxSize())
        } else {
            // Display the video list when the data is available
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp, horizontal = 8.dp)
            ) {
                items(videoListState) { videoName ->
                    VideoItem(name = videoName, context = context)
                }
            }
        }
    }
}