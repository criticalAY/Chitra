/*
 * Copyright (c) 2024 Ashish Yadav <mailtoashish693@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.criticalay.chitra.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.criticalay.chitra.R

@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    selectedColor: Color,
    onColorSelected: (color: Color) -> Unit,
    onClose: () -> Unit,
    orientation: Orientation = Orientation.Vertical
) {
    val colors = listOf(
        Color.Black, Color.Blue, Color.White, Color.Gray,
        Color.DarkGray, Color.Red, Color.Cyan, Color.Green,
        Color.Magenta, Color.Yellow
    )
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        onClose.let {
            IconButton(onClick = { onClose() }) {
                Icon(
                    modifier = Modifier
                        .padding(end = 12.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.cd_close_color_selection),
                    tint = Color.Black
                )
            }
        }
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .background(
                        color = if (color == selectedColor) color else color.copy(alpha = 0.7f),
                        shape = CircleShape
                    )
                    .border(
                        width = if (color == selectedColor) 4.dp else 2.dp,
                        color = if (color == selectedColor) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.inversePrimary
                        },
                        shape = CircleShape
                    )
                    .size(35.dp)
                    .toggleable(color == selectedColor) {
                        onColorSelected(color)
                    }
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun Preview_ColorPicker() {
    ColorPicker(
        modifier = Modifier.wrapContentSize(),
        selectedColor = Color.Black,
        onColorSelected = {},
        onClose = {}
    )
}