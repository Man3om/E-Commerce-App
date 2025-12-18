package com.example.e_commerce.data.models.remote

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class AuthenticationResponseModel(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("user")
	val user: UserModel,

	@field:SerializedName("token")
	val token: String? = null
) : Parcelable

@Parcelize
data class UserModel(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable
