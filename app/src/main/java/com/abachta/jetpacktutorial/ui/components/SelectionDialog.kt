package com.abachta.jetpacktutorial.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.settings.AppTheme

interface SelectableTextProvider {
    @Composable
    fun getText(): String
}

@Composable
fun <T : SelectableTextProvider> SelectionDialog(
    onDismissRequest: () -> Unit,
    title: String,
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {

            Column {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 4.dp)
                ) {

                    items.forEach { item ->

                        val isSelected = item == selectedItem

                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onItemSelected(item)
                                }
                        ) {
                            Icon(
                                imageVector = if (isSelected) {
                                    Icons.Filled.RadioButtonChecked
                                } else {
                                    Icons.Filled.RadioButtonUnchecked
                                },
                                tint = if (isSelected) {
                                    RadioButtonDefaults.colors().selectedColor
                                } else {
                                    RadioButtonDefaults.colors().unselectedColor
                                },
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            )

                            Text(
                                text = item.getText(),
                            )
                        }
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text(text = stringResource(R.string.dialog_cancel))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SelectionDialogPreview() {
    SelectionDialog(
        onDismissRequest = {},
        title = "Title",
        items = AppTheme.entries,
        selectedItem = AppTheme.Auto,
        onItemSelected = {}
    )
}
