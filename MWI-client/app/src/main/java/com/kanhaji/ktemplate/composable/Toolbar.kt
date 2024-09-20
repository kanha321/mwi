package com.kanhaji.ktemplate.composable

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mwi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    context: Context,
    title: String = context.getString(R.string.app_name),
    showBack: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (showBack) {
                    IconButton(
                        onClick = {
                            (context as Activity).finish()
                        },
                        modifier = androidx.compose.ui.Modifier
                            .offset(x = (-12).dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_arrow_back_24),
                            contentDescription = null,
                            modifier = androidx.compose.ui.Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = androidx.compose.ui.Modifier.size(8.dp))
                } else {
                    Spacer(modifier = androidx.compose.ui.Modifier.size(24.dp))
                }
                Text(
                    text = title,
                    modifier = androidx.compose.ui.Modifier
                        .offset(x = (-20).dp)
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        actions = content
    )
}