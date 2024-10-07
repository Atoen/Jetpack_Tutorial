package com.abachta.jetpacktutorial.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.CodeOff
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.getChallengeById
import com.abachta.jetpacktutorial.ui.Screen
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChallengeScreen(
    challengeData: Screen.Challenge
) {
    var codeRevealed by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    val res = if (codeRevealed) {
                        R.string.challenge_hide_code
                    } else R.string.challenge_show_code

                    Text(stringResource(res))
                },
                icon = {
                    val icon = if (codeRevealed) {
                        Icons.Filled.CodeOff
                    } else Icons.Filled.Code

                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                },
                onClick = {
                    codeRevealed = !codeRevealed
                }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            val challenge = getChallengeById(challengeData.id)
            Preview {
                challenge.content()
            }

            AnimatedVisibility(visible = codeRevealed) {
                CodeListing(
                    code = challenge.code,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
