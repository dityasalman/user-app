package com.ditya.contactapi.ui.theme.mainscreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ditya.contactapi.data.network.model.User
import com.ditya.contactapi.data.network.service.ApiService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var responseMessage by mutableStateOf("")
    var usersResponse: List<User> by mutableStateOf(listOf())
    var errorMessage by mutableStateOf("")
    var isAddingUser by mutableStateOf(false)
    private val apiService = ApiService.getInstance()

    fun getUserList() {
        viewModelScope.launch {
            try {
                val userList = apiService.getUser()
                usersResponse = userList
                Log.d("USER", "value: ${usersResponse[0]}")
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()

            }
        }
    }

    fun postUser(user: User) {
        viewModelScope.launch {
            try {
                val postUser = apiService.saveUser(user)
                responseMessage = postUser
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun deleteUser(id: Long) {
        viewModelScope.launch {
            viewModelScope.launch {
                try {
                    val deleteUser = apiService.deleteUser(id)
                    responseMessage = deleteUser
                }
                catch (e: Exception) {
                    errorMessage = e.message.toString()
                }
            }
        }
    }

    fun onAddClick() {
        isAddingUser = true
    }

    fun onDismissClick() {
        isAddingUser = false
    }
}