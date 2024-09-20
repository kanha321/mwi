package com.kanhaji.ktemplate.activities.settings

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kanhaji.ktemplate.composable.AboutDialog
import com.kanhaji.ktemplate.composable.KCardSingle
import com.kanhaji.ktemplate.composable.LineHeader
import com.kanhaji.ktemplate.composable.ThemeDialog
import com.kanhaji.ktemplate.composable.Toolbar
import com.kanhaji.ktemplate.util.MyToast
import com.kanhaji.ktemplate.util.isDynamicColor
import com.kanhaji.ktemplate.util.sharedPrefsIsDynamicColor
import com.kanhaji.ktemplate.util.themeHeader
import com.mwi.R
import com.mwi.composable.IpAddressDialog

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel, context: Context
) {
    Column {
        Toolbar(
            context = context,
            title = "Settings",
        ) {
            IconButton(
                onClick = {
                    viewModel.onInfoButtonClick()
                }, modifier = Modifier
                    .padding(12.dp)
                    .size(24.dp)
                    .offset((-12).dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_info_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        LineHeader("Theme")
        Spacer(modifier = Modifier.size(8.dp))
        KCardSingle(name = "App Theme",
            description = "Select theme for the app",
            icon = painterResource(R.drawable.outline_brightness_24),
            addButton = true,
            buttonText = themeHeader,
            onButtonClick = {
                viewModel.onThemeButtonClick()
            })
        KCardSingle(name = "Material You",
            description = "Wallpaper based colors",
            icon = painterResource(R.drawable.outline_color_lens_24),
            addKSwitch = true,
            initialSwitchState = isDynamicColor,
            onCheckedChange = {
                isDynamicColor = it
                viewModel.sharedPrefsManager.saveBoolean(
                    sharedPrefsIsDynamicColor, isDynamicColor
                )
            },
            switchEnabled = true,
            onSwitchDisabledClick = {
                MyToast.show(
                    context,
                    "Not Available for devices below Android 12",
                )
            }
        )
        KCardSingle(
            name = "IP Address",
            description = "Change IP Address",
            icon = painterResource(R.drawable.outline_key_24),
            onCardClick = {
                viewModel.onIPButtonClick()
            }
        )
    }

    if (viewModel.isDialogShown) {
        ThemeDialog(onConfirm = {
            viewModel.onThemeConfirmClick()
        }, onDismiss = {
            viewModel.onThemeCancelClick()
        }, context = context
        )
    }

    if (viewModel.isInfoDialogShown) {
        AboutDialog(
            onConfirm = {
                viewModel.onInfoConfirmClick()
            },
            onDismiss = {
                viewModel.onInfoCancelClick()
            },
            dialogIcon = R.drawable.outline_info_24,
        )
    }

    if (viewModel.isIPDialogShown) {
        IpAddressDialog(
            onConfirm = { text ->
                viewModel.onIPConfirmClick(context, text)
            },
            onDismiss = {
                viewModel.onIPCancelClick()
            },
        )
    }
}
