package com.example.ddm_atividade6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.ddm_atividade6.dominio.User
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buscaDados()
    }

    private fun buscaDados() {
        val resultado = TextView(this@MainActivity)
        main_layout.addView(resultado)
        val serviceRetrofit = RetrofitService()
        serviceRetrofit.api?.obterUsuarios()?.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>?>?, response: Response<List<User>?>?) {
                val lista = response?.body();
                if (lista != null) {
                    resultado.text = "Lista de usúarios (nome - email)\n\n"
                    for (user in lista) {
                        val usuario = TextView(this@MainActivity)

                        usuario.text = "${user.name.toString()} - ${user.email.toString()}"
                        Log.d("Resposta", "${user.name.toString()} - ${user.email.toString()}")

                        main_layout.addView(usuario)
                    }
                } else {
                    resultado.text = "Não há usuários"
                }
            }

            override fun onFailure(call: Call<List<User>?>?, t: Throwable?) {
                resultado.text = "Não foi possível realizar a consulta"
                Log.e("Erro", "************** erro **********\n"+t?.message.toString())
            }
        })
    }
}
