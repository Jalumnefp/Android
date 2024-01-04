package es.jfp.crud_retrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitObject {

    companion object {
        private var instance: Retrofit? = null

        fun getInstance(): Retrofit {
            synchronized(this) {
                if (instance == null) {
                    instance = Retrofit.Builder()
                        .baseUrl("https://tpprxmdy.lucusprueba.es/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return instance!!
            }
        }
    }

}