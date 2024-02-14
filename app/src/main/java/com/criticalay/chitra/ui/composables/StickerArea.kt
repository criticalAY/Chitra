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

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.sp
import com.criticalay.chitra.model.EditorObject

@Composable
fun StickerArea(
    modifier: Modifier = Modifier,
    drawingObjects: List<EditorObject.Text>,
    onTransform: (id: String, offset: Offset, rotation: Float, scale: Float) -> Unit
) {
    Box(modifier = modifier) {
        drawingObjects.forEach { text ->
            Text(
                modifier = Modifier
                    .graphicsLayer {
                        rotationZ = text.rotation
                        scaleX = text.scale
                        scaleY = text.scale
                        translationX = text.offset.x
                        translationY = text.offset.y
                    }
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, gestureZoom, gestureRotate ->
                            onTransform(
                                text.id!!,
                                Offset(pan.x, pan.y),
                                gestureRotate,
                                gestureZoom
                            )
                        }
                    },
                text = text.text,
                color = text.color,
                fontSize = 100.sp
            )
        }
    }
}