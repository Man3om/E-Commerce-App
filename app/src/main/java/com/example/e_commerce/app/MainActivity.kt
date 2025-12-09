package com.example.e_commerce.app

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_commerce.R
import com.example.e_commerce.app.screens.homeScreen.HomeScreen
import com.example.e_commerce.app.screens.signInScreen.SignInScreen
import com.example.e_commerce.app.screens.signUpScreen.SignUpScreen
import com.example.e_commerce.app.ui.theme.ECommerceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        actionBar?.hide()
        /*************************************************************************************/
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.MainColor)
        /*************************************************************************************/
        installSplashScreen()
        setContent {
            val navController = rememberNavController()
            ECommerceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = SignInScreen , modifier = Modifier.fillMaxSize()
                        .padding(innerPadding)) {
                        composable<SignInScreen>{
                            SignInScreen(navController = navController)
                        }

                        composable<SignUpScreen>{
                           SignUpScreen(navController =navController)
                        }

                        composable<HomeScreen>{
                            HomeScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
