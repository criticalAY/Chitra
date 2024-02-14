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

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.criticalay.chitra.model.BrushConfiguration
import com.criticalay.chitra.model.EditorEvent
import com.criticalay.chitra.model.EditorObject
import com.criticalay.chitra.model.EditorTool

@Composable
fun ToolConfig(
    modifier: Modifier = Modifier,
    selectedTool: EditorTool?,
    configuration: BrushConfiguration,
    currentObject: EditorObject?,
    handleEvent: (event: EditorEvent) -> Unit,
    addText: (test: String, color: Color) -> Unit
) {
    if (selectedTool is EditorTool.Brush) {
        BrushSettings(
            modifier = modifier,
            brushConfiguration = configuration,
            onColorSelected = {
                handleEvent(EditorEvent.UpdateToolColor(it))
            },
            onThicknessChanged = {
                handleEvent(EditorEvent.UpdateToolThickness(it))
            },
            onClose = {

            }
        )
    } else if (selectedTool is EditorTool.Text) {
        TextSettings(
            modifier = modifier,
            addText = addText
        )
    }
}

@Preview(showBackground = false)
@Composable
fun Preview_ToolConfigurationBrush() {
    ToolConfig(
        modifier = Modifier.wrapContentSize(),
        selectedTool = EditorTool.Brush,
        configuration = BrushConfiguration(),
        currentObject = null,
        handleEvent = {},
        addText = { text, color -> }
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_ToolConfigurationText() {
    ToolConfig(
        modifier = Modifier.wrapContentSize(),
        selectedTool = EditorTool.Text,
        configuration = BrushConfiguration(),
        currentObject = null,
        handleEvent = {},
        addText = { text, color -> }
    )
}