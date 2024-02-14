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

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.InsertEmoticon
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.ui.graphics.vector.ImageVector
import com.criticalay.chitra.R

sealed class EditorTool(
    @StringRes val description: Int,
    val icon: ImageVector
) {
    data object Text : EditorTool(R.string.label_text, Icons.Default.TextFormat)

    data object Brush : EditorTool(R.string.label_brush, Icons.Default.Brush)

    data object Emoji : EditorTool(R.string.label_emoji, Icons.Default.InsertEmoticon)
}