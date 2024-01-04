package es.jfp.crud_retrofit.api

import com.google.gson.annotations.SerializedName


data class CarResponse (

    @SerializedName("id")
    var id: Int,

    @SerializedName("brand")
    var brand: String,

    @SerializedName("model")
    var model: String,

    @SerializedName("photo")
    var photo: String

)