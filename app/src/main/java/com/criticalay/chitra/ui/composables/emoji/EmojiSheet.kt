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

package com.criticalay.chitra.ui.composables.emoji

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EmojiSheet(
    modifier: Modifier = Modifier,
    onEmojiSelected: (emoji: String) -> Unit
) {
    val emojis = listOf(
        "❤️", "🙌", "🥳️", "👏️",
        "🤩️", "👀", "🙄", "😇",
        "😂️", "😅", "🤣", "🙃"
    )
    Column(modifier = modifier) {
        emojis.chunked(4).map { emojiRow ->
            EmojiRow(
                modifier = Modifier.fillMaxWidth(),
                emojis = emojiRow,
                onSelected = onEmojiSelected
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_EmojiSheet() {
    EmojiSheet(
        modifier = Modifier.padding(16.dp),
        onEmojiSelected = {}
    )
}

@Composable
fun EmojiRow(
    modifier: Modifier,
    emojis: List<String>,
    onSelected: (emoji: String) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        emojis.forEach { emoji ->
            EmojiOption(
                text = emoji,
                onClick = onSelected
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_EmojiRow() {
    EmojiRow(
        modifier = Modifier.padding(16.dp),
        emojis = listOf(
            "❤️", "🙌", "🥳️", "👏️"
        ),
        onSelected = {}
    )
}