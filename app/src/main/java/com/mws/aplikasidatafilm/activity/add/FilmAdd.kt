package com.mws.aplikasidatafilm.activity.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mws.aplikasidatafilm.R
import com.mws.aplikasidatafilm.api.APIClient
import com.mws.aplikasidatafilm.model.FilmModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmAdd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_add)

        createFilmData()
    }

    private fun createFilmData() {

        val etJudul: EditText = findViewById(R.id.etJudul)
        val etGenre: EditText = findViewById(R.id.etGenre)
        val etAsal: EditText = findViewById(R.id.etAsal)
        val etDurasi: EditText = findViewById(R.id.etDurasi)

        val btnSaveFilm: Button = findViewById(R.id.btnSaveFilm)
        val apiClient = APIClient.create(this)

        btnSaveFilm.setOnClickListener{
            if (etJudul.text.toString().isEmpty()){
                etJudul.setError("Judul Film Tidak Boleh Kosong!!")
            }
            if (etGenre.text.toString().isEmpty()){
                etGenre.setError("Genre Film Tidak Boleh Kosong!!")
            }
            if (etAsal.text.toString().isEmpty()){
                etAsal.setError("Asal Film Tidak Boleh Kosong!!")
            }
            if (etDurasi.text.toString().isEmpty()){
                etDurasi.setError("Durasi Film Tidak Boleh Kosong!!")
            }

            val sendData = apiClient.createFilm(
                etJudul.text.toString(),
                etGenre.text.toString(),
                etAsal.text.toString(),
                etDurasi.text.toString(),
            )

            sendData.enqueue(object: Callback<FilmModel>{
                override fun onResponse(call: Call<FilmModel>, response: Response<FilmModel>) {

                    if (response.isSuccessful){
                        Toast.makeText(this@FilmAdd, "Data Ditambahkan", Toast.LENGTH_LONG).show()
                        finish()
                    }
                }

                override fun onFailure(call: Call<FilmModel>, t: Throwable) {
                }

            })

        }

    }
}