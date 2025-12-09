package com.example.e_commerce.app.screens.signUpScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.e_commerce.R
import com.example.e_commerce.app.HomeScreen
import com.example.e_commerce.app.ui.theme.MainColor
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
            navController.navigate(HomeScreen){
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
        Text(
            text = "Full Name",
            fontSize = 18.sp,
            fontFamily = FontFamily.Monospace,
            color = White,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        val fullNameState = rememberTextFieldState()
        TextField(
            state = fullNameState,
            lineLimits = TextFieldLineLimits.SingleLine,
            placeholder = { Text("Enter Your Full Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = White,
                focusedTextColor = Black,
                focusedContainerColor = White
            ),
            textStyle = TextStyle(
                color = Black, fontSize = 16.sp, fontFamily = FontFamily.SansSerif
            )
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // Mobile Phone
        Text(
            text = "Mobile Number",
            fontSize = 18.sp,
            fontFamily = FontFamily.Monospace,
            color = White,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        val mobileNumberState = rememberTextFieldState()
        TextField(
            state = mobileNumberState,
            lineLimits = TextFieldLineLimits.SingleLine,
            placeholder = { Text("Enter Your Mobile No.") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = White,
                focusedTextColor = Black,
                focusedContainerColor = White
            ),
            textStyle = TextStyle(
                color = Black, fontSize = 16.sp, fontFamily = FontFamily.SansSerif
            )
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // UserName
        Text(
            text = "User Name",
            fontSize = 18.sp,
            fontFamily = FontFamily.Monospace,
            color = White,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        val userNameState = rememberTextFieldState()
        TextField(
            state = userNameState,
            lineLimits = TextFieldLineLimits.SingleLine,
            placeholder = { Text("Enter Your User Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = White,
                focusedTextColor = Black,
                focusedContainerColor = White
            ),
            textStyle = TextStyle(
                color = Black, fontSize = 16.sp, fontFamily = FontFamily.SansSerif
            )
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // Email
        Text(
            text = "Email Address",
            fontSize = 18.sp,
            fontFamily = FontFamily.Monospace,
            color = White,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        val emailState = rememberTextFieldState()
        TextField(
            state = emailState,
            lineLimits = TextFieldLineLimits.SingleLine,
            placeholder = { Text("Enter Your Email Address") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = White,
                focusedTextColor = Black,
                focusedContainerColor = White
            ),
            textStyle = TextStyle(
                color = Black, fontSize = 16.sp, fontFamily = FontFamily.SansSerif
            )
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // Password
        Text(
            text = "Password",
            fontSize = 18.sp,
            fontFamily = FontFamily.Monospace,
            color = White,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        val passwordState = rememberTextFieldState()
        TextField(
            state = passwordState,
            lineLimits = TextFieldLineLimits.SingleLine,
            placeholder = { Text("Enter Your Password") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = White,
                focusedTextColor = Black,
                focusedContainerColor = White
            ),
            textStyle = TextStyle(
                color = Black, fontSize = 16.sp, fontFamily = FontFamily.SansSerif
            )
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // Sign In Button
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp), onClick = {
                onSignUpClicked(
                    fullNameState.text.toString(),
                    mobileNumberState.text.toString(),
                    userNameState.text.toString(),
                    emailState.text.toString(),
                    passwordState.text.toString()
                )
            }, colors = buttonColors(containerColor = White, contentColor = MainColor)
        ) {
            Text(
                "Sign Up",
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

    }
}