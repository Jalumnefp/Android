package es.jfp.crud_retrofit.api

import retrofit2.Response
import retrofit2.http.*

interface CarsService {

    @GET("cars")
    suspend fun getAllCars(): Response<List<CarResponse>>

    @POST("cars")
    suspend fun addCar(@Body car: CarResponse): Response<CarResponse>

    @PUT("cars/{car_id}")
    suspend fun updateCar(@Path("car_id") car_id: Int, @Body car: CarResponse): Response<CarResponse>

    @DELETE("cars/{car_id}")
    suspend fun deleteCar(@Path("car_id") car_id: Int): Response<Unit>

}