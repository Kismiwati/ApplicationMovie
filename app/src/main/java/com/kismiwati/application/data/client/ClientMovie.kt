package com.kismiwati.application.data.client

import com.kismiwati.application.data.client.IClientMovie
import com.kismiwati.application.data.client.IClientMovie.Companion.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientMovie {
    //bagian ini digunakan untuk mengakses member dari kelas tanpa melalui objek.
    companion object {
        private lateinit var retrofit: Retrofit
        private fun getRetrofitInstance(): Retrofit {
            if (!::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
        val movieClientService: IClientMovie by lazy {
            getRetrofitInstance().create(IClientMovie::class.java)
        }
    }
}
