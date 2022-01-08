package com.mws.aplikasidatafilm.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mws.aplikasidatafilm.R
import com.mws.aplikasidatafilm.activity.MainActivity
import com.mws.aplikasidatafilm.activity.menu.menu_dashboard
import com.mws.aplikasidatafilm.api.APIClient
import com.mws.aplikasidatafilm.api.SessionManager
import com.mws.aplikasidatafilm.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class login_activity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)



//        if(sessionManager.fetchAuthToken() != null){
//            startActivity(Intent(this@login_activity, menu_dashboard::class.java))
//            finish()
//        } else {
            setContentView(R.layout.activity_login)
            userLoginAuth()
//        }

    }

    private fun userLoginAuth() {

        val apiClient = APIClient.create(this)
        val etEmail: EditText = findViewById(R.id.et_email)
        val etPassword: EditText = findViewById(R.id.et_password)

        val btnLogin: Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {

            apiClient.login(
                etEmail.text.toString(),
                etPassword.text.toString()
            ).enqueue(object : Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val loginResponse = response.body()
                    if(loginResponse?.status_code == 200){
                        sessionManager.saveAuthToken(loginResponse.access_token)
                        Toast.makeText(this@login_activity, "Login Berhasil", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@login_activity, menu_dashboard::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@login_activity, "Email atau Password Salah", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                }


            })

        }

    }

//    fun userLogin() {
//
//        val email: EditText = findViewById(R.id.et_email)
//        val password: EditText = findViewById(R.id.et_password)
//
//        val btnLogin: Button = findViewById(R.id.btnLogin)
//
//        btnLogin.setOnClickListener {
//
//            email.text.toString()
//            password.text.toString()
//
//            if (email.text.toString().equals("admin") && password.text.toString()
//                    .equals("admin")
//            ) {
//                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_LONG).show()
//
//                val intent = Intent(this, menu_dashboard::class.java)
//                startActivity(intent)
//            } else {
//
//                Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
}



