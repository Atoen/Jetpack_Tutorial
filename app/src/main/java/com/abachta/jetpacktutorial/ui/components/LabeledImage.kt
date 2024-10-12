package com.abachta.jetpacktutorial.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

sealed class ImageLabelPosition {
    data object Top : ImageLabelPosition()
    data object Bottom : ImageLabelPosition()
}

@Composable
fun LabeledImage(
    @DrawableRes imageResId: Int,
    @StringRes labelTextId: Int,
    modifier: Modifier = Modifier,
    labelPosition: ImageLabelPosition = ImageLabelPosition.Bottom
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {

        if (labelPosition is ImageLabelPosition.Top) {
            ResText(
                resId = labelTextId,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Image(
            painter = painterResource(imageResId),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

        if (labelPosition is ImageLabelPosition.Bottom) {
            ResText(
                resId = labelTextId,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}