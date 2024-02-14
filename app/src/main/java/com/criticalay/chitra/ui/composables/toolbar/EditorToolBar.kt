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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.criticalay.chitra.R
import com.criticalay.chitra.model.EditorTool

@Composable
fun EditorToolBar(
    modifier: Modifier = Modifier,
    onToolSelected: (tool: EditorTool) -> Unit,
    closeEditor: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { closeEditor() }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.cd_close_story_editor),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Tool(
            tool = EditorTool.Text,
            onSelected = onToolSelected
        )
        Tool(
            tool = EditorTool.Brush,
            onSelected = onToolSelected
        )
        Tool(
            tool = EditorTool.Emoji,
            onSelected = onToolSelected
        )
    }
}

@Preview(showBackground = false)
@Composable
fun Preview_EditorToolBar() {
    EditorToolBar(
        modifier = Modifier.wrapContentSize(),
        onToolSelected = { },
        closeEditor = { }
    )
}