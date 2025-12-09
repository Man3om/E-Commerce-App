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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.e_commerce.domain.resources.Resources
import com.example.e_commerce.R
import com.example.e_commerce.app.HomeScreen
import com.example.e_commerce.app.SignUpScreen
import com.example.e_commerce.app.ui.theme.MainColor


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
        // Image
        Image(
            painter = painterResource(id = R.drawable.ic_app_name), contentDescription = "App Name",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 35.dp)
        )

        // Text
        Text(
            text = "Welcome Back To Route", fontSize = 20.sp, fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold, color = White
        )
        Text(
            text = "Please sign in with your mail",
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
        Text(
            text = "User Name", fontSize = 18.sp, fontFamily = FontFamily.Monospace,
            color = White, modifier = Modifier.padding(vertical = 10.dp)
        )

        val usernameState = rememberTextFieldState()
        TextField(
            state = usernameState,
            lineLimits = TextFieldLineLimits.SingleLine,
            placeholder = { Text("Enter Your Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = White,
                focusedTextColor = Black,
                focusedContainerColor = White
            ),
            textStyle = TextStyle(
                color = Black,
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif
            )
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        // Password
        Text(
            text = "Password", fontSize = 18.sp, fontFamily = FontFamily.Monospace,
            color = White, modifier = Modifier.padding(vertical = 10.dp)
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
                color = Black,
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif
            )
        )

        // Forgot Password
        Text(
            text = "Forgot Password?", fontSize = 16.sp, fontFamily = FontFamily.Monospace,
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
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onSignInClicked(
                    usernameState.text.toString(),
                    passwordState.text.toString()
                )
            },
            colors = buttonColors(containerColor = White, contentColor = MainColor)
        ) {
            Text(
                "Login",
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

        //Register
        Text(
            "Don't have an account? Create Account",
            modifier = Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally)
                .clickable { navController.navigate(SignUpScreen) },
            fontSize = 16.sp, fontFamily = FontFamily.Monospace, color = White
        )
    }
}