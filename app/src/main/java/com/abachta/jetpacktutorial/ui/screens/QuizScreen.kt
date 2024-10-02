package com.abachta.jetpacktutorial.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizAnswer
import com.abachta.jetpacktutorial.data.QuizQuestion
import com.abachta.jetpacktutorial.ui.Screen
import com.abachta.jetpacktutorial.ui.components.ExtendableFloatingActionButton
import com.abachta.jetpacktutorial.ui.components.QuizAnswerCard
import kotlinx.coroutines.launch

val composeTextQuiz = Quiz(
    titleResId = R.string.lesson_text_title,
    questions = listOf(
        QuizQuestion(
            textResId = R.string.quiz_question_1,
            answers = listOf(
                QuizAnswer(textResId = R.string.quiz_answer_1_1, isCorrect = true),
                QuizAnswer(textResId = R.string.quiz_answer_1_2),
                QuizAnswer(textResId = R.string.quiz_answer_1_3)
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_question_2,
            answers = listOf(
                QuizAnswer(textResId = R.string.quiz_answer_2_1),
                QuizAnswer(textResId = R.string.quiz_answer_2_2, isCorrect = true),
                QuizAnswer(textResId = R.string.quiz_answer_2_3)
            )
        ),
        QuizQuestion(
            textResId = R.string.quiz_question_3,
            answers = listOf(
                QuizAnswer(textResId = R.string.quiz_answer_3_1),
                QuizAnswer(textResId = R.string.quiz_answer_3_2),
                QuizAnswer(textResId = R.string.quiz_answer_3_3, isCorrect = true)
            )
        )
    )
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun QuizScreen(
    quizData: Screen.Quiz,
    onQuizFinished: () -> Unit
) {
    val quiz = composeTextQuiz
    val questionCount = quiz.questions.count()

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { questionCount + 1 })
    val currentPage = pagerState.currentPage

    LaunchedEffect(Unit) {
        quiz.reset()
    }

    Scaffold(
        bottomBar = {
            if (currentPage < questionCount) {
                val currentQuestion = quiz.questions[currentPage]
                BottomAppBar(
                    actions = {
                        IconButton(
                            enabled = currentPage != 0,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(currentPage - 1)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }

                        Text(stringResource(R.string.quiz_question_n_of, currentPage + 1, questionCount))
                    },
                    floatingActionButton = {
                        ExtendableFloatingActionButton(
                            text = { Text(stringResource(R.string.quiz_check)) },
                            icon = {
                                if (!currentQuestion.revealed) {
                                    Icon(
                                        imageVector = Icons.Filled.Check,
                                        contentDescription = null
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = null
                                    )
                                }
                            },
                            onClick = {
                                if (!currentQuestion.revealed) {
                                    currentQuestion.reveal()
                                } else {
                                    scope.launch {
                                        pagerState.animateScrollToPage(currentPage + 1)
                                    }
                                }
                            },
                            extended = !currentQuestion.revealed
                        )
                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) { page ->
                if (page < quiz.questions.count()) {
                    val question = quiz.questions[page]
                    QuizQuestionScreen(question)
                } else {
                    QuizSummaryScreen(quiz)
                }
            }
        }
    }
}

@Composable
private fun QuizQuestionScreen(
    question: QuizQuestion
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = question.textResId),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        question.answers.forEach { answer ->
            QuizAnswerCard(
                isRevealed = question.revealed,
                isSelected = answer.selected,
                isCorrect = answer.isCorrect,
                textResId = answer.textResId,
                onClick = {
                    if (!question.revealed) {
                        answer.selected = !answer.selected
                    }
                }
            )
        }
    }
}

@Composable
fun QuizSummaryScreen(quiz: Quiz) {
    val correctAnswers = quiz.questions.size - quiz.incorrectAnswerCount()
    val totalQuestions = quiz.questions.size
    val fractionCorrect = quiz.correctlyAnsweredQuestionFraction()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ElevatedCard(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = stringResource(id = quiz.titleResId),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "You answered $correctAnswers out of $totalQuestions questions correctly!",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                val animatedProgress by animateFloatAsState(
                    targetValue = fractionCorrect,
                    animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
                    label = "quiz_progress"
                )

                LinearProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .height(8.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.secondaryContainer,
                )

                Text(
                    text = "%.2f%%".format(animatedProgress * 100),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Button(
                    onClick = { /* Handle restart quiz or navigate */ },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Restart Quiz")
                }
            }
        }
    }
}