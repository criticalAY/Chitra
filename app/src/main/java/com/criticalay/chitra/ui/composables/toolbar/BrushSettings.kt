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

package com.criticalay.chitra.ui.composables.toolbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.criticalay.chitra.model.BrushConfiguration
import com.criticalay.chitra.ui.composables.ColorPicker

@Composable
fun BrushSettings(
    modifier: Modifier = Modifier,
    brushConfiguration: BrushConfiguration,
    onColorSelected: (color: Color) -> Unit,
    onThicknessChanged: (thickness: Float) -> Unit,
    onClose: () -> Unit
) {
    Column(modifier = modifier) {
        Slider(
            modifier = Modifier
                .align(Alignment.Start)
                .rotate(270f)
                .offset(x = 0.dp, y = (-165).dp)
                .weight(1f),
            valueRange = 1f..50f,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.White,
                inactiveTrackColor = Color.White.copy(alpha = 0.4f)
            ),
            value = brushConfiguration.thickness,
            onValueChange = {
                onThicknessChanged(it)
            }
        )
        Surface(
            color = Color.Black.copy(alpha = 0.1f),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            ColorPicker(
                onColorSelected = onColorSelected,
                selectedColor = brushConfiguration.color,
                onClose = onClose
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun Preview_BrushSettings() {
    BrushSettings(
        modifier = Modifier.wrapContentSize(),
        brushConfiguration = BrushConfiguration(),
        onColorSelected = {},
        onThicknessChanged = {},
        onClose = {},
    )
}