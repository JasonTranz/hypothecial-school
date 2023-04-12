package com.jason.littlelives.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun RegularText(
    modifier: Modifier = Modifier,
    content: String,
    fontSize: TextUnit = 13.sp,
    color: Color = MaterialTheme.colorScheme.onBackground,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    maxLines: Int = 1,
) {
    Text(
        text = content,
        modifier = modifier,
        style = TextStyle(
            fontWeight = fontWeight,
            fontSize = fontSize,
            color = color,
            textAlign = textAlign,
            textDecoration = textDecoration,
            fontStyle = fontStyle
        ),
        maxLines = maxLines
    )
}

@Composable
fun SemiBoldText(
    modifier: Modifier = Modifier,
    content: String,
    fontSize: TextUnit = 13.sp,
    color: Color = MaterialTheme.colorScheme.onBackground,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.SemiBold,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    maxLines: Int = 1,
) {
    Text(
        text = content,
        modifier = modifier,
        style = TextStyle(
            fontWeight = fontWeight,
            fontSize = fontSize,
            color = color,
            textAlign = textAlign,
            textDecoration = textDecoration,
            fontStyle = fontStyle,
        ),
        maxLines = maxLines,
    )
}

@Composable
fun BoldText(
    modifier: Modifier = Modifier,
    content: String,
    fontSize: TextUnit = 12.sp,
    color: Color = MaterialTheme.colorScheme.onBackground,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration = TextDecoration.None,
    maxLines: Int = 1,
) {
    Text(
        text = content,
        modifier = modifier,
        style = TextStyle(
            fontWeight = fontWeight,
            fontSize = fontSize,
            color = color,
            textAlign = textAlign,
            textDecoration = textDecoration,
            fontStyle = fontStyle
        ),
        maxLines = maxLines
    )
}