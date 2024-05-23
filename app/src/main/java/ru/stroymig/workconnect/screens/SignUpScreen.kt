package ru.stroymig.workconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.stroymig.workconnect.R
import ru.stroymig.workconnect.navigation.Screen
import ru.stroymig.workconnect.navigation.WorkConnectAppRouter
import ru.stroymig.workconnect.data.signup.SignupUIEvent
import ru.stroymig.workconnect.data.signup.SignupViewModel
import ru.stroymig.workconnect.ui.theme.GradientBackgroundColor

@Composable
fun SignUpScreen (signupViewModel: SignupViewModel = viewModel()){

    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .background(GradientBackgroundColor)
                .padding(28.dp)
        ){
            Column (
                modifier = Modifier.fillMaxSize()
                    .background(GradientBackgroundColor)
            ){

                HeadingTextComponent(value = stringResource(id = R.string.create_account))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextField(labelValue = stringResource(id = R.string.first_name),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
                )

                MyTextField(labelValue = stringResource(id = R.string.last_name),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.lastNameError
                )

                MyTextField(labelValue = stringResource(id = R.string.email),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.emailError
                )

                PasswordTextField(labelValue = stringResource(id = R.string.password),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(80.dp))
                ButtonComponent(value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = signupViewModel.allValidationsPassed.value
                )
                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    WorkConnectAppRouter.navigateTo(Screen.LoginScreen)
                })

            }

        }

        if (signupViewModel.signUpInProgress.value){
            CircularProgressIndicator()
        }
        
    }
}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen(){
    SignUpScreen()
}