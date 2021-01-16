package com.example.ddm_atividade6

import com.example.ddm_atividade6.dominio.User
import retrofit2.Call
import retrofit2.http.GET


interface ApiEndPoint {
    @GET("users")
    fun obterUsuarios(): Call<List<User>>
}
