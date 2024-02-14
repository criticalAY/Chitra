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

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.criticalay.chitra.model.EditorTool

@Composable
fun Tool(
    modifier: Modifier = Modifier,
    tool: EditorTool,
    onSelected: (tool: EditorTool) -> Unit
) {
    IconButton(onClick = { onSelected(tool) }) {
        Icon(
            modifier = modifier,
            imageVector = tool.icon,
            contentDescription = stringResource(id = tool.description),
            tint = Color.White
        )
    }
}

@Preview(showBackground = false)
@Composable
fun Preview_Tool() {
    Tool(
        tool = EditorTool.Emoji,
        onSelected = { }
    )
}