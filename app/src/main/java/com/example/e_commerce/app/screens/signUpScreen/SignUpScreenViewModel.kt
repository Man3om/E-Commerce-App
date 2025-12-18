package com.example.e_commerce.app.screens.signUpScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.domain.entites.UsersEntity
import com.example.e_commerce.domain.repository.LocalRepo
import com.example.e_commerce.domain.repository.RemoteRepo
import com.example.e_commerce.domain.utils.resources.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(private val repo: LocalRepo , private val apiRepo : RemoteRepo) : ViewModel() {
    private val TAG = "SignUpScreenViewModel"
    private val _resultState = mutableStateOf<Resources<Unit>>(Resources.Initial())
    var resultState = _resultState

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, "userSignUp: ${exception.message}")
        _resultState.value = Resources.Error(exception.message.toString())
    }

    fun userSignUp(
        user: UsersEntity
    ) {
        _resultState.value = Resources.Loading()
        val isValid = isUserDataValid(user)
        if (!isValid) {
            _resultState.value = Resources.Error("Please Fill All User Data Fields")
            return
        }

        viewModelScope.launch(Dispatchers.IO + handler) {
            Log.d(TAG, "userSignUp: $user")
            val state = repo.getUserName(user.username)

            if (state is Resources.Success) {
                Log.d(TAG, "user is Exist ${state.response}")
                _resultState.value = Resources.Error("user is Already Exist")
            } else {
                Log.d(TAG, "user is not Exist")
                _resultState.value = repo.insertUser(user)
            }
        }
    }

    private fun isUserDataValid(user: UsersEntity) : Boolean {
        return !(user.name.isNullOrEmpty()
                || user.username.isEmpty()
                || user.email.isNullOrEmpty()
                || user.password == null
                || user.phone.isNullOrEmpty())
    }
}