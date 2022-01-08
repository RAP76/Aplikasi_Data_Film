package com.mws.aplikasidatafilm.`interface`

import com.mws.aplikasidatafilm.model.FilmModel
import com.mws.aplikasidatafilm.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @FormUrlEncoded
    @POST("auth")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("films")
    fun getFilms(): Call<ArrayList<FilmModel>>

    @GET("films/{id}")
    fun getFilm(
        @Path("id") id: Int
    ): Call<ArrayList<FilmModel>>

    @FormUrlEncoded
    @POST("films")
    fun createFilm(
        @Field("judul") judul: String,
        @Field("genre") genre: String,
        @Field("asal") asal: String,
        @Field("durasi") durasi: String,
    ): Call<FilmModel>

    @FormUrlEncoded
    @PUT("films/{id}")
    fun updateFilm(
        @Path("id") id : Int?,
        @Field("judul") judul: String,
        @Field("genre") genre: String,
        @Field("asal") asal: String,
        @Field("durasi") durasi: String,
    ): Call<FilmModel>

    @DELETE("films/{id}")
    fun deleteFilm(
        @Path("id") id : Int
    ): Call<Unit>
}