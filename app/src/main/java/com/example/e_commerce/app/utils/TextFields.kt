package com.example.e_commerce.app.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextFieldsData(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    placeholder: String = "",
    label: String = ""
) {
    Text(
        text = label, fontSize = 18.sp, fontFamily = FontFamily.Monospace,
        color = White, modifier = modifier.padding(vertical = 10.dp)
    )
    TextField(
        state = state,
        lineLimits = TextFieldLineLimits.SingleLine,
        placeholder = { Text(placeholder) },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = White,
            focusedTextColor = Black,
            focusedContainerColor = White
        ),
        textStyle = TextStyle(
            color = Black,
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif
        )
    )
}