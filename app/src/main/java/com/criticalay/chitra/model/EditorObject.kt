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

import androidx.compose.runtime.MutableState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

sealed class EditorObject(val id: String? = null) {

    data class BrushPath(
        val path: MutableState<Path>,
        val brushConfiguration: BrushConfiguration
    ) : EditorObject()

    data class Text(
        val textId: String,
        val text: String,
        val offset: Offset,
        val scale: Float = 1f,
        val rotation: Float = 1f,
        val color: Color = Color.White
    ) : EditorObject(textId)
}