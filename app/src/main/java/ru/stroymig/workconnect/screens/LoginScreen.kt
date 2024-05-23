package ru.stroymig.workconnect.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.stroymig.workconnect.R
import ru.stroymig.workconnect.navigation.Screen
import ru.stroymig.workconnect.navigation.WorkConnectAppRouter
import ru.stroymig.workconnect.data.login.LoginViewModel
import ru.stroymig.workconnect.data.login.LoginUIEvent
import ru.stroymig.workconnect.ui.theme.GradientBackgroundColor
import ru.stroymig.workconnect.ui.theme.Primary02
import ru.stroymig.workconnect.ui.theme.Primary03

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GradientBackgroundColor),
        contentAlignment = Alignment.Center
    ){
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ){
                Image(
                    painterResource(id = R.drawable.logo),
                    contentDescription = "My Login Icon",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(top = 65.dp)
                )

                Card(
                    modifier = Modifier
                        .padding(vertical = 75.dp, horizontal = 25.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, Primary02),
                    backgroundColor = Primary03
                ){

                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(all = 10.dp)

                            .background(Primary03)
                    ){
                        HeadingTextComponent(value = stringResource(id = R.string.auth))
                        Spacer(modifier = Modifier.height(10.dp))
                        NormalTextComponent(value = stringResource(id = R.string.auth_message))

                        Spacer(modifier = Modifier.height(20.dp))

                        MyTextField(labelValue = stringResource(id = R.string.email),
                            onTextSelected = {
                                loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                            },
                            errorStatus = loginViewModel.loginUIState.value.emailError
                        )

                        PasswordTextField(labelValue = stringResource(id = R.string.password),
                            onTextSelected = {
                                loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                            },
                            errorStatus = loginViewModel.loginUIState.value.passwordError
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        ButtonComponent(value = stringResource(id = R.string.login),
                            onButtonClicked = {
                                loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                            },
                            isEnabled = loginViewModel.allValidationsPassed.value
                        )

                        if(loginViewModel.loginInProgress.value){
                            CircularProgressIndicator()
                        }
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    WorkConnectAppRouter.navigateTo(Screen.SignUpScreen)
                })

            }
        }
    }


@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}