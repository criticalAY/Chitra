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

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import com.criticalay.chitra.model.EditorEvent
import com.criticalay.chitra.model.EditorObject
import com.criticalay.chitra.model.EditorTool

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DrawingArea(
    modifier: Modifier = Modifier,
    selectedTool: EditorTool?,
    drawingObjects: List<EditorObject>,
    currentPath: EditorObject?,
    handleEvent: (event: EditorEvent) -> Unit
) {
    Canvas(modifier = modifier
        .pointerInteropFilter { event ->
            if (selectedTool is EditorTool.Brush) {
                handleEvent(
                    EditorEvent.BrushEvent(
                        event
                    )
                )
            }
            true
        }) {
        (drawingObjects + currentPath).filterIsInstance<EditorObject.BrushPath>().forEach { drawingObject ->
            drawPath(
                path = drawingObject.path.value,
                color = drawingObject.brushConfiguration.color,
                alpha = 1F,
                style = Stroke(
                    drawingObject.brushConfiguration.thickness,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )
        }
    }
}