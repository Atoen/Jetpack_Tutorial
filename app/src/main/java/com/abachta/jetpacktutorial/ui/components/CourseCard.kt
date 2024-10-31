package com.abachta.jetpacktutorial.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Course

@Composable
fun CourseCard(
    course: Course,
    onClick: () -> Unit
) {
    ElevatedCard (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        onClick = onClick
    ) {

        val courseIsCompleted = course.isCompleted

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Text(
                    text = stringResource(course.titleResId),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                if (courseIsCompleted) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = stringResource(R.string.completed),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(course.descriptionResId),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(16.dp))

            val progressColor = if (courseIsCompleted) {
                MaterialTheme.colorScheme.primary
            } else MaterialTheme.colorScheme.secondary

            val trackColor = if (courseIsCompleted) {
                MaterialTheme.colorScheme.primaryContainer
            } else MaterialTheme.colorScheme.secondaryContainer

            val animatedProgress by animateFloatAsState(
                targetValue = course.progress.value,
                animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
                label = "course_progress"
            )

            val indicatorModifier = Modifier
                .fillMaxWidth()
                .height(8.dp)

            LinearProgressIndicator(
                progress = { animatedProgress },
                modifier = indicatorModifier,
                color = progressColor,
                trackColor = trackColor,
                strokeCap = StrokeCap.Round,
            )

            Spacer(modifier = Modifier.height(8.dp))

            val completedPercent = (animatedProgress * 100).toInt()
            Text(
                text = stringResource(R.string.completed_percent, completedPercent),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = progressColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseCardPreview() {
    CourseCard(
        course = Course.GettingStarted,
        onClick = {}
    )
}
