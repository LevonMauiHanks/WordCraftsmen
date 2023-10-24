package com.example.wordcraftsmen.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordcraftsmen.R

@Composable
fun AppLogo(
    textSize: Int,
    paddingStart: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(start = paddingStart.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.earth_icon),
            contentDescription = stringResource(R.string.app_logo),
            modifier = Modifier.padding(end = 4.dp)
        )

        Text(
            stringResource(R.string.wordcraftsmen),
            textAlign = TextAlign.Center,
            fontSize = textSize.sp,
            fontFamily = FontFamily.Cursive,
            color = MaterialTheme.colorScheme.primary
        )
    }
}