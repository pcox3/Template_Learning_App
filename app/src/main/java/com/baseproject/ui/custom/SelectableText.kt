package com.baseproject.ui.custom

import com.baseproject.theme.extraLargeRadius
import com.baseproject.theme.primaryColor
import com.baseproject.theme.smallSpacing
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SelectableText(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    options: List<String> = arrayListOf("Coffee", "Chocolate", "Tea"),
    placeholder: String = "",
    radius: Dp = extraLargeRadius,
    padding: Dp = smallSpacing,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    leadingIcon: ImageVector? = null,
    iconTint: Color = MaterialTheme.colorScheme.onSurface
) {
    var expanded by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }

    val borderColor = if (expanded)
        primaryColor
    else
        MaterialTheme.colorScheme.outline.copy(alpha = 1f)

    val shape = RoundedCornerShape(radius)

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .border(width = 1.dp, color = borderColor, shape = shape)
                .clickable { expanded = !expanded }
                .padding(padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.padding(end = smallSpacing)
                    )
                }

                Text(
                    text = value.ifEmpty { placeholder },
                    style = textStyle.copy(color = MaterialTheme.colorScheme.onSurface)
                )
            }
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                tint = iconTint
            )
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onValueChange(option)
                        value = option
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditableDropdownPreview() {
    SelectableText(
        onValueChange = {  },
        options = listOf("Espresso", "Cappuccino", "Latte", "Mocha"),
        placeholder = "Select Coffee"
    )
}