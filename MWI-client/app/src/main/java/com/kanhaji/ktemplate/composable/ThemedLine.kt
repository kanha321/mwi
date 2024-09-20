package com.kanhaji.ktemplate.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun LineHeader(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            color = color,
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
        )
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = textSize,
        )
        Divider(
            color = color,
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
        )
    }
}