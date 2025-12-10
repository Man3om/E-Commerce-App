package com.example.e_commerce.app.screens.signUpScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.e_commerce.R
import com.example.e_commerce.app.HomeScreen
import com.example.e_commerce.app.ui.theme.MainColor
import com.example.e_commerce.app.utils.AuthButton
import com.example.e_commerce.app.utils.TextFieldsData
import com.example.e_commerce.domain.entites.UsersEntity
import com.example.e_commerce.domain.resources.Resources


@Composable
fun SignUpScreen(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel: SignUpScreenViewModel = hiltViewModel()
    val context = LocalContext.current
    SignUpScreenUi(modifier) { fullName, mobileNumber, userName, email, password ->
        viewModel.userSignUp(
            UsersEntity(
                fullName,
                userName,
                email,
                password.hashCode(),
                mobileNumber
            )
        )
    }

    when (val state = viewModel.resultState.value) {
        is Resources.Error -> {
            if (state.message.isNotEmpty()) {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            }
        }

        is Resources.Initial -> {}
        is Resources.Loading -> {
        }

        is Resources.Success -> {
            navController.navigate(HomeScreen) {
                popUpTo(HomeScreen) { inclusive = true }
            }
        }
    }
}

@Composable
fun SignUpScreenUi(
    modifier: Modifier = Modifier, onSignUpClicked: (String, String, String, String, String) -> Unit
) {
    Column(
        modifier = modifier
            .background(MainColor)
            .fillMaxSize()
            .padding(10.dp)
    ) {
        val fullNameState = rememberTextFieldState()
        val mobileNumberState = rememberTextFieldState()
        val userNameState = rememberTextFieldState()
        val emailState = rememberTextFieldState()
        val passwordState = rememberTextFieldState()

        // Image
        Image(
            painter = painterResource(id = R.drawable.ic_app_name),
            contentDescription = "App Name",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // Full Name
        TextFieldsData(
            state = fullNameState,
            label = stringResource(R.string.full_name),
            placeholder = stringResource(R.string.enter_your_name),
            modifier = Modifier
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // Mobile Phone
        TextFieldsData(
            state = mobileNumberState,
            label = stringResource(R.string.mobile_number),
            placeholder = stringResource(R.string.enter_your_mobile_no),
            modifier = Modifier
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // UserName
        TextFieldsData(
            state = userNameState,
            label = stringResource(R.string.user_name),
            placeholder = stringResource(R.string.enter_your_user_name),
            modifier = Modifier
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // Email
        TextFieldsData(
            state = userNameState,
            label = stringResource(R.string.email_address),
            placeholder = stringResource(R.string.enter_your_email_address),
            modifier = Modifier
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // Password
        TextFieldsData(
            state = passwordState,
            label = stringResource(R.string.password),
            placeholder = stringResource(R.string.enter_your_password),
            modifier = Modifier
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // Sign In Button
        AuthButton(text =stringResource(R.string.sign_up) ) {
            onSignUpClicked(
                fullNameState.text.toString(),
                mobileNumberState.text.toString(),
                userNameState.text.toString(),
                emailState.text.toString(),
                passwordState.text.toString()
            )
        }
    }
}