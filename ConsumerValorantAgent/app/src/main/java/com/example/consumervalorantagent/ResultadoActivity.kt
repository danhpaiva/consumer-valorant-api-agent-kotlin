package com.example.consumervalorantagent

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val personagemImagem = findViewById<ImageView>(R.id.personagemImg)
        val nomeTextView = findViewById<TextView>(R.id.nameTv)
        val descTextView = findViewById<TextView>(R.id.descTv)
        val botaoVoltar = findViewById<Button>(R.id.buttonRetornar)

        val uuid = intent.getStringExtra("UUID_EXTRA")

        if (uuid != null) {
            val valorantApi = RetrofitHelper.getInstance().create(ValorantApi::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val response = valorantApi.getPersonagem("e370fa57-4757-3604-3648-499e1f642d3f")
                    if (response.isSuccessful) {
                        val persona = response.body()
                        Log.d("Retorno da API: ", persona.toString())

                        withContext(Dispatchers.Main) {
                            Glide.with(this@ResultadoActivity)
                                .load(persona?.data?.fullPortrait)
                                .into(personagemImagem)
                            nomeTextView.text = "NOME: ${persona?.data?.displayName}"
                            descTextView.text = "DESCRICAO: ${persona?.data?.description}"
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            nomeTextView.text = "Erro: ${response.code()}"
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        nomeTextView.text = "Ocorreu um erro: ${e.message}"
                    }
                }
            }
        } else {
            nomeTextView.text = "Personagem não encontrado."
        }

        botaoVoltar.setOnClickListener {
            onBackPressed()
        }
    }
}