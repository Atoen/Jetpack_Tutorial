package com.abachta.jetpacktutorial.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

sealed class ListItem {
    data class TextItem(@StringRes val resId: Int) : ListItem()
    data class NestedList(val items: List<ListItem>) : ListItem()

    companion object {
        fun @receiver:StringRes Int.toTextItem() = TextItem(this)
    }
}

enum class ItemizationMode {
    Bullet,
    Numeric,
    Alphabetic
}

private fun ItemizationMode.next(): ItemizationMode {
    return when (this) {
        ItemizationMode.Bullet -> ItemizationMode.Numeric
        ItemizationMode.Numeric -> ItemizationMode.Alphabetic
        ItemizationMode.Alphabetic -> ItemizationMode.Numeric // Not returning to bullet
    }
}

@Composable
fun ItemizedList(
    vararg items: ListItem,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    itemizationMode: ItemizationMode = ItemizationMode.Numeric,
    level: Int = 0
) {
    Column(
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        modifier = modifier
    ) {
        items.forEachIndexed { i, item ->

            val prefix = when (itemizationMode) {
                ItemizationMode.Bullet -> "•"
                ItemizationMode.Alphabetic -> "${('a' + i)})"
                ItemizationMode.Numeric -> "${i + 1}."
            }

            when (item) {
                is ListItem.TextItem -> {
                    Row {
                        Text(
                            text = prefix,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        ResText(item.resId)
                    }
                }
                is ListItem.NestedList -> {
                    val nestedModifier = Modifier.padding(start = 16.dp * (level + 1))
                    ItemizedList(
                        items = item.items.toTypedArray(),
                        modifier = nestedModifier,
                        verticalArrangement = verticalArrangement,
                        horizontalAlignment = horizontalAlignment,
                        itemizationMode = itemizationMode.next(),
                        level = level + 1
                    )
                }
            }
        }
    }
}
