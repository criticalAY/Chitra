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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.criticalay.chitra.R

@Composable
fun EmojiOption(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (text: String) -> Unit
) {
    Text(
        modifier = modifier
            .padding(16.dp)
            .clickable(onClickLabel = stringResource(id = R.string.cd_select_emoji, text)) {
                onClick(text)
            },
        text = text,
        fontSize = 20.sp
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_EmojiOption() {
    EmojiOption(
        modifier = Modifier.wrapContentSize(),
        text = "🥳",
        onClick = {}
    )
}