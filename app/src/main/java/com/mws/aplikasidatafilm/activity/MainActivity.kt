package com.mws.aplikasidatafilm.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mws.aplikasidatafilm.R
import com.mws.aplikasidatafilm.activity.add.FilmAdd
import com.mws.aplikasidatafilm.adapter.FilmAdapter
import com.mws.aplikasidatafilm.api.APIClient
import com.mws.aplikasidatafilm.model.FilmModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun refreshData(){

        val dataRefreshLayout: SwipeRefreshLayout = findViewById(R.id.refresh_data)
        dataRefreshLayout.setOnRefreshListener {
            getFilmData()
            Toast.makeText(this,"Page Refreshed!!", Toast.LENGTH_LONG).show()
            dataRefreshLayout.isRefreshing = false
        }
    }

    override fun onResume() {
        getFilmData()
        refreshData()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.film_add_menu -> {
                val intent = Intent(this,FilmAdd::class.java)
                startActivity(intent)

                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getFilmData(){

        val listData = ArrayList<FilmModel>()

        val rvFilmData: RecyclerView = findViewById(R.id.rv_film_data)
        rvFilmData.setHasFixedSize(true)
        rvFilmData.layoutManager = LinearLayoutManager(this)

        val tvStatusCode: TextView = findViewById(R.id.tv_status_code)

        val apiClient = APIClient.create(this)
        val callData = apiClient.getFilms()

        callData.enqueue(object: Callback<ArrayList<FilmModel>>{
            override fun onResponse(
                call: Call<ArrayList<FilmModel>>,
                response: Response<ArrayList<FilmModel>>
            ) {
                val data = response.body()

                tvStatusCode.text = response.code().toString()
                data?.let { listData.addAll(it) }

                val adapterData = FilmAdapter(listData)

                rvFilmData.adapter = adapterData

                Log.d("TAG", response.code().toString())
            }

            override fun onFailure(call: Call<ArrayList<FilmModel>>, t: Throwable) {

                Log.e("TAG", t.message.toString())
            }

        })
    }
}

