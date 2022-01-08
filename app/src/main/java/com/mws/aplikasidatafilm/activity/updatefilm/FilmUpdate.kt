package com.mws.aplikasidatafilm.activity.updatefilm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mws.aplikasidatafilm.R
import com.mws.aplikasidatafilm.activity.add.FilmAdd
import com.mws.aplikasidatafilm.api.APIClient
import com.mws.aplikasidatafilm.model.FilmModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmUpdate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_film)

        updateFilm()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu_delete, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.film_delete_menu -> {
                deleteFilmData()
                Toast.makeText(applicationContext,"Data berhasil di Hapus", Toast.LENGTH_LONG).show()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteFilmData(){

        val apiClient = APIClient.create(this)
        val id = intent.getStringExtra("ID")
        val deleteFilm = id?.toInt()?.let { apiClient.deleteFilm(it) }

        deleteFilm?.enqueue(object: Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful){
                    Toast.makeText(applicationContext,"Data berhasil di Hapus", Toast.LENGTH_LONG).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })


    }


    private fun updateFilm() {

        val etJudul: EditText = findViewById(R.id.etUpJudul)
        val etGenre: EditText = findViewById(R.id.etUpGenre)
        val etAsal: EditText = findViewById(R.id.etUpAsal)
        val etDurasi: EditText = findViewById(R.id.etUpDurasi)
        val btnUpdateFilm: Button = findViewById(R.id.btnUpdateFilm)


        etJudul.setText(intent.getStringExtra("JUDUL"))
        etGenre.setText(intent.getStringExtra("GENRE"))
        etAsal.setText(intent.getStringExtra("ASAL"))
        etDurasi.setText(intent.getStringExtra("DURASI"))

        btnUpdateFilm.setOnClickListener {

            val apiClient = APIClient.create(this)

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

            val id = intent.getStringExtra("ID")

            val updateFilm = apiClient.updateFilm(

                id?.toInt(),
                etJudul.text.toString(),
                etGenre.text.toString(),
                etAsal.text.toString(),
                etDurasi.text.toString(),
            )
            updateFilm.enqueue(object: Callback<FilmModel> {
                override fun onResponse(
                    call: Call<FilmModel>,
                    response: Response<FilmModel>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(applicationContext,"Data berhasil di Update", Toast.LENGTH_LONG).show()
                        finish()
                    }
                }

                override fun onFailure(call: Call<FilmModel>, t: Throwable) {
                    Toast.makeText(applicationContext,t.message.toString(), Toast.LENGTH_LONG).show()

                }

            })
        }


    }


}