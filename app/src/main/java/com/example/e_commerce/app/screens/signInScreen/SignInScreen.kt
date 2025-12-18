package com.example.e_commerce.app.screens.signInScreen

import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.e_commerce.domain.utils.resources.Resources
import com.example.e_commerce.R
import com.example.e_commerce.app.HomeScreen
import com.example.e_commerce.app.SignUpScreen
import com.example.e_commerce.app.ui.theme.MainColor
import com.example.e_commerce.app.utils.AuthButton
import com.example.e_commerce.app.utils.TextFieldsData


@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val context = LocalContext.current
    val viewModel: SignInScreenViewModel = hiltViewModel()
    SignInScreenUi(modifier, navController) { username, password ->
        viewModel.userLogIn(username, password)
    }

    when (val state = viewModel.resultState.value) {
        is Resources.Error -> {
            if (state.message.isNotEmpty()) {
                Toast.makeText(context, state.message, LENGTH_LONG).show()
            }
        }

        is Resources.Initial -> {


        }

        is Resources.Loading -> {

        }

        is Resources.Success -> {
            navController.navigate(HomeScreen){
                popUpTo(HomeScreen) { inclusive = true }
            }
        }
    }
}

@Composable
private fun SignInScreenUi(
    modifier: Modifier,
    navController: NavController,
    onSignInClicked: (String, String) -> Unit
) {
    Column(
        modifier = modifier
            .background(MainColor)
            .fillMaxSize()
            .padding(10.dp)
    ) {

        val usernameState = rememberTextFieldState()
        val passwordState = rememberTextFieldState()

        // Image
        Image(
            painter = painterResource(id = R.drawable.ic_app_name), contentDescription = stringResource(
                R.string.app_logo
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 35.dp)
        )

        // Text
        Text(
            text = stringResource(R.string.welcome_back_to_route), fontSize = 20.sp, fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold, color = White
        )
        Text(
            text = stringResource(R.string.please_sign_in_with_your_mail),
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            color = White
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )

        // UserName
        TextFieldsData(state = usernameState , label = stringResource(R.string.user_name)
            , placeholder = stringResource(R.string.enter_your_name) , modifier = Modifier)

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // Password
        TextFieldsData(state = passwordState , label = stringResource(R.string.password)
            , placeholder = stringResource(R.string.enter_your_password) , modifier = Modifier)

        // Forgot Password
        Text(
            text = stringResource(R.string.forgot_password), fontSize = 16.sp, fontFamily = FontFamily.Monospace,
            color = White, modifier = Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.End)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // Sign In Button
        AuthButton(text = stringResource(R.string.login)) {
            onSignInClicked(
                usernameState.text.toString().trim(),
                passwordState.text.toString().trim()
            )
        }

        //Register
        Text(
            stringResource(R.string.don_t_have_an_account_create_account),
            modifier = Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
                .clickable { navController.navigate(SignUpScreen) },
            fontSize = 16.sp, fontFamily = FontFamily.Monospace, color = White
        )
    }
}