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

package com.criticalay.chitra.model

import android.view.MotionEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

sealed class EditorEvent {
    class ToolSelected(val tool: EditorTool) : EditorEvent()

    data object UnselectTool : EditorEvent()

    data object CloseEditor : EditorEvent()

    class BrushEvent(val event: MotionEvent) : EditorEvent()

    class UpdateToolColor(
        val color: Color
    ): EditorEvent()

    class UpdateToolThickness(
        val thickness: Float
    ): EditorEvent()

    data object Undo : EditorEvent()

    class AddText(
        val x: Float,
        val y: Float,
        val width: Int,
        val height: Int,
        val text: String,
        val color: Color? = null
    ) : EditorEvent()

    class TransformObject(
        val id: String,
        val offset: Offset,
        val rotation: Float = 1f,
        val scale: Float = 1f
    ) : EditorEvent()
}