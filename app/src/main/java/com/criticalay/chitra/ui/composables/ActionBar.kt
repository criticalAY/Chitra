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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.criticalay.chitra.R
import com.criticalay.chitra.model.BrushConfiguration
import com.criticalay.chitra.model.EditorEvent
import com.criticalay.chitra.model.EditorObject
import com.criticalay.chitra.model.EditorTool
import com.criticalay.chitra.ui.composables.toolbar.EditorToolBar

@Composable
fun ActionsBar(
    modifier: Modifier = Modifier,
    selectedTool: EditorTool?,
    drawingObjects: List<EditorObject>?,
    handleEvent: (event: EditorEvent) -> Unit
) {
    Surface(
        modifier = modifier,
        color = Color.Black.copy(alpha = 0.4f)
    ) {
        if (selectedTool == null) {
            EditorToolBar(
                modifier = Modifier.fillMaxWidth(),
                onToolSelected = {
                    handleEvent(EditorEvent.ToolSelected(it))
                },
                closeEditor = {
                    handleEvent(EditorEvent.CloseEditor)
                }
            )
        } else if (selectedTool is EditorTool.Brush) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (!drawingObjects.isNullOrEmpty()) {
                    IconButton(onClick = {
                        handleEvent(EditorEvent.Undo)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Undo,
                            contentDescription = stringResource(id = R.string.cd_undo),
                            tint = Color.White
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {
                    handleEvent(EditorEvent.UnselectTool)
                }) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = stringResource(id = R.string.cd_done),
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun Preview_ActionsBar() {
    ActionsBar(
        modifier = Modifier.wrapContentSize(),
        selectedTool = EditorTool.Brush,
        drawingObjects = listOf(EditorObject.BrushPath(
            remember {
                mutableStateOf(Path())
            },
            BrushConfiguration()
        )),
        handleEvent = { }
    )
}