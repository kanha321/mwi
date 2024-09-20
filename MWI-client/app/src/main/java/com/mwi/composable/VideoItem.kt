package com.mwi.composable

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mwi.player.PlayerActivity
import com.mwi.util.url

@Composable
fun VideoItem(name: String, context: Context) {
    val imageUrl = "$url$name/poster.jpg"
    Log.d("VideoItem", "Loading image from URL: $imageUrl")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 4.dp),
        onClick = {
            val intent = Intent(context, PlayerActivity::class.java).apply {
                putExtra("video_url", "$url$name/playlist.m3u8") // Replace with your video URL
            }
            context.startActivity(intent)
        },
        content = {
            // Use aspectRatio to set 16:9 ratio
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
            Text(
                text = name,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    )
}


@Preview(showBackground = true)
@Composable
fun VideoItemPreview() {
    VideoItem("Video Name", LocalContext.current)
}