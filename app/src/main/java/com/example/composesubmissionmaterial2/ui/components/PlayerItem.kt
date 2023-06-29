package com.example.composesubmissionmaterial2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composesubmissionmaterial2.R
import com.example.composesubmissionmaterial2.ui.theme.ComposeSubmissionMaterial2Theme

@Composable
fun PlayerItem(
    image: Int,
    position: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
        )
        Text(
            text = position,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PlayerItemPreview() {
    ComposeSubmissionMaterial2Theme {
        PlayerItem(R.drawable.casemiro, "Midfielder")
    }
}