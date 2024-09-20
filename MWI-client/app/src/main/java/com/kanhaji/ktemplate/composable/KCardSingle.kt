package com.kanhaji.ktemplate.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kanhaji.ktemplate.util.isDarkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KCardSingle(
    icon: Painter? = null,
    name: String,
    description: String = "",
    alignment: Alignment = Alignment.CenterStart,
    onCardClick: () -> Unit = {},

    // switch
    onCheckedChange: (Boolean) -> Unit = {},
    addKSwitch: Boolean = false,
    initialSwitchState: Boolean = false,
    switchEnabled: Boolean = true,
    onSwitchDisabledClick: () -> Unit = {},

    // button
    addButton: Boolean = false,
    buttonText: String = "Button",
    onButtonClick: () -> Unit = {},
    buttonEnabled: Boolean = true,
    onButtonDisabledClick: () -> Unit = {},
) {

    if (addButton && addKSwitch) {
        throw Exception("Only one item can be added to the card")
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 4.dp),
        onClick = {
            if (switchEnabled) onCheckedChange(!initialSwitchState) else onSwitchDisabledClick()
            if (buttonEnabled) onButtonClick() else onButtonDisabledClick()
            if (!addButton && !addKSwitch) onCardClick()
        },
    ) {
        Row(
            modifier = if(switchEnabled){
                Modifier
                    .background(MaterialTheme.colorScheme.onSecondary)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            } else {
                Modifier
                    .background(
                        if (isDarkTheme) Color(0xFF2E2E2E) else Color(0xFFECECEC)
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null){
                Icon(
                    painter = icon,
                    contentDescription = null, // Provide a meaningful description if needed
                    modifier = Modifier.size(24.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                if (description.isNotEmpty()){
                    Text(
                        text = description,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray
                    )
                }
            }

            // switch
            if (addKSwitch) {
                KSwitch(
                    state = initialSwitchState,
                    onCheckedChange = onCheckedChange,
                    enabled = switchEnabled
                )
            }

            // button
            if (addButton) {
                Button(
                    onClick = if (buttonEnabled) onButtonClick else onButtonDisabledClick,
                    enabled = buttonEnabled,
                ) {
                    Text(text = buttonText)
                }
            }
        }
    }
}