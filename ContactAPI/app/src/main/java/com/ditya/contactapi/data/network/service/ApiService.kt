package com.ditya.contactapi.data.network.service

import com.ditya.contactapi.data.network.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/users")
    suspend fun getUser(): List<User>

    @POST("/save")
    suspend fun saveUser(
        @Body user: User
    ): String

    @DELETE("/delete/{id}")
    suspend fun deleteUser(@Path("id") id: Long): String

    companion object{
        var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("http://192.168.1.83:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}