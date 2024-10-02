package com.abachta.jetpacktutorial.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.getting_started.gettingStartedLessons
import com.abachta.jetpacktutorial.data.Lesson

@Composable
fun LessonCard(
    lesson: Lesson,
    onClick: () -> Unit
) {
    val lessonIsCompleted = lesson.progress.completed

    ElevatedCard(
        modifier = Modifier
            .widthIn(max = 500.dp)
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = if (lessonIsCompleted) {
            MaterialTheme.colorScheme.primaryContainer
        } else MaterialTheme.colorScheme.surfaceVariant ),
        onClick = { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(lesson.titleResId),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.weight(1f))

                if (lessonIsCompleted) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = stringResource(R.string.completed),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                var showPopup by remember { mutableStateOf(false) }

                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(20.dp)
                        .clickable {
                            showPopup = true
                        }
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            val pages = lesson.pages.count()
            Text(
                text = pluralStringResource(R.plurals.lesson_pages, pages, pages),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LessonCardPreview() {

    val lesson = gettingStartedLessons[0]
    lesson.progress.complete()

    LessonCard(
        lesson = lesson,
        onClick = {}
    )
}
