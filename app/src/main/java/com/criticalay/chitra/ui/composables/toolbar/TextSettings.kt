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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatColorText
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.criticalay.chitra.R
import com.criticalay.chitra.ui.composables.ColorPicker

@Composable
fun TextSettings(
    modifier: Modifier = Modifier,
    addText: (text: String, color: Color) -> Unit
) {
    Box(
        modifier = modifier.background(Color.Black.copy(alpha = 0.8f))
    ) {
        var content by remember {
            mutableStateOf("")
        }
        var color by remember {
            mutableStateOf(Color.White)
        }
        val focusRequester = FocusRequester()
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        var showingColors by remember { mutableStateOf(false) }
        Row(modifier = Modifier.fillMaxWidth()) {
            if (!showingColors) {
                IconButton(onClick = { showingColors = !showingColors }) {
                    Icon(
                        modifier = Modifier
                            .padding(20.dp),
                        imageVector = Icons.Default.FormatColorText,
                        contentDescription = stringResource(id = R.string.cd_select_color),
                        tint = Color.White
                    )
                }
            }
            if (showingColors) {
                ColorPicker(
                    selectedColor = color,
                    onColorSelected = {
                        color = it
                        showingColors = !showingColors
                    },
                    onClose = {
                        showingColors = !showingColors
                    }
                )
            } else {
                Spacer(Modifier.weight(1f))
                TextButton(
                    modifier = Modifier.padding(end = 12.dp, top = 14.dp),
                    onClick = {
                        if (content.isNotEmpty()) {
                            addText(content, color)
                        }
                    }) {
                    Text(
                        text = stringResource(id = R.string.label_done),
                        color = Color.White
                    )
                }
            }
        }

        TextField(
            modifier = Modifier
                .widthIn(min = 28.dp)
                .padding(16.dp)
                .focusRequester(focusRequester)
                .align(Alignment.Center),
            value = content,
            onValueChange = {
                content = it
            },
            textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_TextSettings() {
    TextSettings(
        modifier = Modifier.fillMaxSize(),
        addText = { a, b ->

        }
    )
}