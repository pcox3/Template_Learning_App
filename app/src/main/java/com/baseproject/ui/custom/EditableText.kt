package com.baseproject.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.baseproject.R
import com.baseproject.theme.colorWhite
import com.baseproject.theme.extraLargeRadius
import com.baseproject.theme.smallSpacing

@Composable
fun EditableText(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    singleLine: Boolean = true,
    isEnabled: Boolean = true,
    showIcon: Boolean = false,
    onSearch: (String) -> Unit = {},
    onDone: () -> Unit = {},
    onSend: (String) -> Unit = {},
    isOutlined: Boolean = true,
    icon: Int = R.drawable.ic_launcher_foreground,
    isWrapContentWidth: Boolean = false,
    radius: Dp = extraLargeRadius,
    padding: Dp = smallSpacing,
    height: Dp = 40.dp,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {
    val focusManager = LocalFocusManager.current
    var focused by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }
    val borderColor = if (focused)
        MaterialTheme.colorScheme.primary
    else
        MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)

    val shape = RoundedCornerShape(radius)

    BasicTextField(
        value = value,
        onValueChange = {
            value = it
            onValueChange(value)
        },
        singleLine = singleLine,
        enabled = isEnabled,
        textStyle = textStyle.copy(color = MaterialTheme.colorScheme.onSurface),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions {
            this.apply {
                when(imeAction){
                    ImeAction.Done -> {
                        onDone()
                    }
                    ImeAction.Next -> {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                    ImeAction.Search -> {
                        onSearch(value)
                    }
                    ImeAction.Send -> {
                        onSend(value)
                    }
                }
            }
        },
        modifier = modifier
            .height(height = height)
            .clip(shape)
            .onFocusChanged { focused = it.isFocused }
            .then(if (isWrapContentWidth) Modifier.wrapContentWidth()
            else Modifier.fillMaxWidth())
            .then(if (isOutlined) Modifier
                .border(width = 1.dp, color = borderColor, shape = shape)
            else Modifier.background(colorWhite))
            .padding(horizontal = padding),
        decorationBox = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (showIcon){
                    Icon(
                        modifier = Modifier.padding(start = smallSpacing),
                        painter = painterResource(icon),
                        contentDescription = "Icon")
                }
                if (value.isEmpty() && placeholder.isNotEmpty()) {
                    Text(text = placeholder, style = textStyle.copy(
                        color = Color.Gray
                    ))
                }else{
                    it()
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun EditableTextPreview() {
    EditableText(
        onValueChange = { }
    )
}