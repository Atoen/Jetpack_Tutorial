package com.abachta.jetpacktutorial.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.jetpack_basics.quizzes.textQuiz
import com.abachta.jetpacktutorial.data.models.QuizModel
import com.abachta.jetpacktutorial.data.models.QuizQuestionModel
import com.abachta.jetpacktutorial.settings.QuizShufflingOption
import com.abachta.jetpacktutorial.ui.components.ExtendableFloatingActionButton
import com.abachta.jetpacktutorial.ui.components.QuizAnswerCard
import com.abachta.jetpacktutorial.ui.components.ResText
import kotlinx.coroutines.launch

@Composable
fun QuizScreen(
    quiz: QuizModel,
    shuffleMode: QuizShufflingOption,
    onQuizFinished: () -> Unit
) {
    val questionCount = quiz.questions.count()

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { questionCount + 1 }
    val currentPage = pagerState.currentPage

    val startAnimatingResult by remember {
        derivedStateOf { pagerState.settledPage == questionCount }
    }

    var questions by rememberSaveable(shuffleMode) {
        mutableStateOf(
            when (shuffleMode) {
                QuizShufflingOption.NoShuffle -> quiz.questions
                else -> quiz.questions.shuffled()
            }
        )
    }

    Scaffold(
        bottomBar = {
            if (currentPage < questionCount) {
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
                        val currentQuestion = questions[currentPage]
                        val revealed = currentQuestion.revealed
                        ExtendableFloatingActionButton(
                            text = { Text(stringResource(R.string.quiz_check)) },
                            icon = {
                                val icon = if (!revealed) {
                                    Icons.Filled.Check
                                } else Icons.AutoMirrored.Filled.ArrowForward

                                Icon(
                                    imageVector = icon,
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                if (!revealed) {
                                    currentQuestion.reveal()
                                } else {
                                    scope.launch {
                                        pagerState.animateScrollToPage(currentPage + 1)
                                    }
                                }
                            },
                            extended = !revealed
                        )
                    }
                )
            }
        }
    ) {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier
                .padding(bottom = it.calculateBottomPadding())
                .fillMaxSize()
        ) { page ->
            if (page < questionCount) {
                QuizQuestionScreen(
                    question = questions[page],
                    shuffleAnswers = shuffleMode.shuffleAnswers
                )
            } else {
                QuizSummaryScreen(
                    quiz = quiz,
                    startAnimatingResult = startAnimatingResult,
                    onContinue = onQuizFinished,
                    onRestart = {
                        scope.launch {
                            quiz.reset()

                            questions = when (shuffleMode) {
                                QuizShufflingOption.NoShuffle -> quiz.questions
                                else -> quiz.questions.shuffled()
                            }

                            pagerState.animateScrollToPage(0)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun QuizQuestionScreen(
    question: QuizQuestionModel,
    shuffleAnswers: Boolean
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ResText(
            resId = question.textResId,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        val answers = rememberSaveable(
            question.textResId, shuffleAnswers
        ) {
            if (shuffleAnswers) {
                question.answers.shuffled()
            } else question.answers
        }

        answers.forEach { answer ->
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
fun QuizSummaryScreen(
    quiz: QuizModel,
    startAnimatingResult: Boolean,
    onContinue: () -> Unit,
    onRestart: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.Center)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(id = quiz.titleResId),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = stringResource(R.string.quiz_finished),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.height(16.dp))

                val animatedProgress by animateFloatAsState(
                    targetValue = if (startAnimatingResult){
                        quiz.correctlyAnsweredQuestionFraction()
                    } else 0f,
                    animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
                    label = "quiz_result"
                )

                val color = if (animatedProgress == 1.0f) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.secondary
                }

                LinearProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .height(8.dp),
                    color = color,
                    trackColor = MaterialTheme.colorScheme.secondaryContainer,
                )

                Spacer(Modifier.height(8.dp))

                val completedPercent = (animatedProgress * 100).toInt()
                Text(
                    text = "$completedPercent%",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = color
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    stringResource(
                        id = R.string.quiz_answered_correctly_n_of,
                        quiz.correctlyAnsweredQuestionCount(), quiz.questions.count(),
                    )
                )

                val incorrectAnswers = quiz.incorrectAnswerCount()
                if (incorrectAnswers > 0) {

                    val text = buildAnnotatedString {
                        withStyle(SpanStyle(color = MaterialTheme.colorScheme.error)) {
                            append("$incorrectAnswers ")
                        }

                        append(pluralStringResource(R.plurals.incorrect_answers, incorrectAnswers))
                    }

                    Text(text)
                }
            }

            HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = onRestart) {
                    Text(stringResource(R.string.quiz_restart))
                }

                TextButton(onClick = onContinue) {
                    Text(stringResource(R.string.quiz_continue))
                }
            }
        }
    }
}

@Preview
@Composable
private fun QuizSummaryPreview() {
    QuizSummaryScreen(
        quiz = QuizModel.crate(textQuiz),
        startAnimatingResult = true,
        onContinue = {},
        onRestart = {}
    )
}