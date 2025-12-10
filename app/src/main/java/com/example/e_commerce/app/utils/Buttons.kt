package com.example.e_commerce.app.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_commerce.app.ui.theme.MainColor

@Composable
fun AuthButton(modifier: Modifier = Modifier, text : String = "",onClicked: () -> Unit) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            onClicked()
        },
        colors = buttonColors(containerColor = White, contentColor = MainColor)
    ) {
        Text(
            text,
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = modifier.padding(vertical = 10.dp)
        )
    }
}