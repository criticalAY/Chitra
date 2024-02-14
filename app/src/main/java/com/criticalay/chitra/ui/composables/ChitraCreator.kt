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

import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.criticalay.chitra.R
import com.criticalay.chitra.model.EditorEvent
import com.criticalay.chitra.model.EditorObject
import com.criticalay.chitra.model.EditorState
import com.criticalay.chitra.model.EditorTool
import com.criticalay.chitra.ui.composables.emoji.EmojiSheet
import com.criticalay.chitra.ui.composables.toolbar.ToolConfig
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChitraCreator(
    modifier: Modifier = Modifier,
    state: EditorState,
    handleEvent: (event: EditorEvent) -> Unit
) {
    val defaultTextPaint = remember {
        Paint().apply {
            textSize = 80f
        }
    }
    var centerCanvas by remember {
        mutableStateOf(Offset(0f, 0f))
    }
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            EmojiSheet(
                modifier = Modifier.fillMaxWidth()
            ) {
                Rect().also { bounds ->
                    defaultTextPaint.getTextBounds(it, 0, it.length, bounds)
                    handleEvent(
                        EditorEvent.AddText(
                            centerCanvas.x,
                            centerCanvas.y,
                            bounds.width(),
                            bounds.height(),
                            it
                        )
                    )
                }
            }
        }
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.dog),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            LaunchedEffect(this.constraints, block = {
                centerCanvas = Offset(
                    (constraints.maxWidth / 2).toFloat(),
                    (constraints.maxHeight / 2).toFloat()
                )
            })
            DrawingArea(
                modifier = Modifier.fillMaxSize(),
                state.selectedTool, state.drawingObjects, state.currentDrawingPath, handleEvent
            )
            StickerArea(
                modifier = Modifier.fillMaxSize(),
                drawingObjects = state.drawingObjects.filterIsInstance<EditorObject.Text>(),
                onTransform = { id, offset, rotation, scale ->
                    handleEvent(
                        EditorEvent.TransformObject(
                            id = id,
                            offset = offset,
                            rotation = rotation,
                            scale = scale
                        )
                    )
                }
            )
            ActionsBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                selectedTool = state.selectedTool,
                drawingObjects = state.drawingObjects,
                handleEvent = {
                    if (it is EditorEvent.ToolSelected && it.tool == EditorTool.Emoji) {
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    } else handleEvent(it)
                }
            )
            ToolConfig(
                modifier = Modifier.fillMaxSize(),
                selectedTool = state.selectedTool,
                configuration = state.brushConfiguration,
                currentObject = state.currentDrawingPath,
                handleEvent = handleEvent,
                addText = { text, color ->
                    android.graphics.Rect().also { bounds ->
                        defaultTextPaint.getTextBounds(text, 0, text.length, bounds)
                        handleEvent(
                            EditorEvent.AddText(
                                centerCanvas.x,
                                centerCanvas.y,
                                bounds.width(),
                                bounds.height(),
                                text,
                                color
                            )
                        )
                    }
                }
            )
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun Preview_StoryCreator() {
    ChitraCreator(
        state = EditorState(),
        handleEvent = {}
    )
}