package com.example.wordcraftsmen.ui.theme.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordcraftsmen.R
import com.example.wordcraftsmen.data.Word

@Composable
fun WordItem(
    word: Word,
    modifier: Modifier = Modifier,
    checkbox: Boolean = false,
    checkedState: MutableState<Boolean> = mutableStateOf(false),
    newWordsGenerated: MutableState<Boolean> = mutableStateOf(false),
    isChecked: (Word) -> Unit = {},
    micClicked: (Word) -> Unit = {}
) {
    if (newWordsGenerated.value) {
        checkedState.value = false
    }
    Card(
        modifier = modifier
            .border(2.dp, Color.White)
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .fillMaxWidth(0.5f)
            ) {
                Column(
                    Modifier
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    if (checkbox) {
                        Checkbox(
                            checked = checkedState.value,
                            onCheckedChange = {
                                checkedState.value = true
                                isChecked.invoke(word)
                            },
                            modifier = Modifier.size(30.dp),
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Green,
                                checkmarkColor = MaterialTheme.colorScheme.primary,
                                uncheckedColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }

                    Text(
                        text = word.name,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                    )

                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(id = R.drawable.mic_icon),
                        contentDescription = stringResource(R.string.microphone_icon),
                        modifier = Modifier
                            .align(Alignment.End)
                            .clickable {
                                micClicked.invoke(word)
                            }
                    )
                }
            }

            Divider(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primary
            )

            Column(
                Modifier
                    .fillMaxWidth(0.5f)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = word.translation,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                )
            }
        }
    }
}