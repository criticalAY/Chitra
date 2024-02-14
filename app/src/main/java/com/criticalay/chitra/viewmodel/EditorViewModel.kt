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

package com.criticalay.chitra.viewmodel

import android.view.MotionEvent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.lifecycle.ViewModel
import com.criticalay.chitra.model.EditorEvent
import com.criticalay.chitra.model.EditorObject
import com.criticalay.chitra.model.EditorState
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class EditorViewModel : ViewModel() {

    val uiState = MutableStateFlow(EditorState())

    fun handleEvent(contentEvent: EditorEvent) {
        when (contentEvent) {
            is EditorEvent.AddText -> {
                val text = EditorObject.Text(
                    textId = UUID.randomUUID().toString(),
                    offset = Offset(
                        (contentEvent.x +
                                (contentEvent.width / 2)),
                        (contentEvent.y +
                                (contentEvent.height / 2))
                    ),
                    text = contentEvent.text,
                    color = contentEvent.color ?: Color.Unspecified
                )
                uiState.value = uiState.value.copy(
                    drawingObjects = uiState.value
                        .drawingObjects.toMutableList().apply {
                            add(text)
                        }.toList(),
                    selectedTool = null
                )
            }

            is EditorEvent.BrushEvent -> {
                when (contentEvent.event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        val path = Path().apply {
                            moveTo(contentEvent.event.x, contentEvent.event.y)
                        }
                        uiState.value = uiState.value.copy(
                            currentDrawingPath = EditorObject.BrushPath(
                                mutableStateOf(path),
                                uiState.value.brushConfiguration
                            )
                        )
                    }

                    MotionEvent.ACTION_MOVE -> {
                        val brushPath = (uiState.value.currentDrawingPath as EditorObject.BrushPath)
                        val updatedPath = brushPath.path.value.apply {
                            lineTo(contentEvent.event.x, contentEvent.event.y)
                        }
                        val config = brushPath.brushConfiguration
                        uiState.value = uiState.value.copy(
                            currentDrawingPath = EditorObject.BrushPath(
                                mutableStateOf(updatedPath),
                                config
                            )
                        )
                    }

                    MotionEvent.ACTION_UP -> {
                        uiState.value.currentDrawingPath?.let { path ->
                            uiState.value = uiState.value.copy(
                                drawingObjects = uiState.value.drawingObjects.toMutableList()
                                    .apply {
                                        add(path)
                                    }.toList(),
                                currentDrawingPath = null
                            )
                        }
                    }
                }
            }

            is EditorEvent.CloseEditor -> TODO()

            is EditorEvent.ToolSelected -> {
                uiState.value = uiState.value.copy(
                    selectedTool = contentEvent.tool
                )
            }

            is EditorEvent.TransformObject -> {
                val selectedObject =
                    uiState.value.drawingObjects.find {
                        it.id == contentEvent.id
                    } as EditorObject.Text

                val scale = selectedObject.scale *
                        (contentEvent.scale ?: 1f)

                val rotation = selectedObject.rotation +
                        (contentEvent.rotation ?: 1f)

                val transformedOffset =
                    contentEvent.offset.copy(
                        x = contentEvent.offset.x * scale,
                        y = contentEvent.offset.y * scale
                    ).rotateBy(rotation)

                uiState.value = uiState.value.copy(
                    drawingObjects =
                    uiState.value.drawingObjects
                        .toMutableList()
                        .apply {
                            val index = uiState.value
                                .drawingObjects
                                .indexOf(selectedObject)
                            val offset =
                                selectedObject.offset.plus(transformedOffset)
                            set(
                                index,
                                selectedObject.copy(
                                    rotation = rotation,
                                    scale = scale,
                                    offset = offset
                                )
                            )
                        }.toList()
                )
            }

            is EditorEvent.Undo -> {
                uiState.value = uiState.value.copy(
                    drawingObjects = uiState.value
                        .drawingObjects.toMutableList().apply {
                            removeLast()
                        }.toList()
                )

            }

            is EditorEvent.UnselectTool -> {
                uiState.value = uiState.value.copy(
                    selectedTool = null
                )
            }

            is EditorEvent.UpdateToolThickness -> {
                uiState.value = uiState.value.copy(
                    brushConfiguration = uiState.value.brushConfiguration.copy(
                        thickness = contentEvent.thickness
                    )
                )
            }
            is EditorEvent.UpdateToolColor -> {
                uiState.value = uiState.value.copy(
                    brushConfiguration = uiState.value.brushConfiguration.copy(
                        color = contentEvent.color
                    )
                )
            }
        }
    }

    private fun Offset.rotateBy(angle: Float): Offset {
        val angleInRadians = angle * PI / 180
        return Offset(
            (x * cos(angleInRadians) - y *
                    sin(angleInRadians)).toFloat(),
            (x * sin(angleInRadians) + y *
                    cos(angleInRadians)).toFloat()
        )
    }

}