package com.kanhaji.ktemplate.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun AboutDialog(
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
    dialogTitle: String = "About the app",
    dialogText: String = "Made with ❤\uFE0F by Kanha \uD83D\uDE80",
    dialogIcon: Int? = null,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = dialogTitle) },
        text = {
            Column {
                Text(
                    text = dialogText,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(
                    text = "Thank You",
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        icon = dialogIcon?.let { { Icon(painterResource(id = it), contentDescription = null) } }
    )
}