package com.example.e_commerce.app.screens.signInScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.domain.repository.LocalRepo
import com.example.e_commerce.domain.utils.resources.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInScreenViewModel @Inject constructor(private val repo : LocalRepo) : ViewModel() {
    private val TAG = "SignInScreenViewModel"
    private val _resultState = mutableStateOf<Resources<Unit>>(Resources.Initial())
    var resultState = _resultState

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, "userLogIn: ${exception.message}")
        _resultState.value = Resources.Error(exception.message.toString())
    }

    fun userLogIn(username : String , password : String){
        _resultState.value = Resources.Loading()
        if(username.isEmpty() || password.isEmpty()){
            _resultState.value = Resources.Error("Please Fill All User Data Fields")
            return
        }
        Log.d(TAG, "userLogIn: $username $password")
        viewModelScope.launch(Dispatchers.IO + handler) {
            _resultState.value = logIn(username,password)
        }
    }

    private suspend fun logIn(username : String, password : String) : Resources<Unit> {
        val state = repo.getUserName(username)

        if(state is Resources.Success) {
            Log.d(TAG, "user is Exist ${state.response}")

            if(password == state.response.password){
                Log.d(TAG, "password is correct")
                return Resources.Success(Unit)
            }
            else{
                Log.d(TAG, "password is not correct")
                return Resources.Error("password is not correct")
            }
        }
        else{
            return Resources.Error("user is not Exist")
        }
    }
}