package es.jfp.carsmanagement2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("car")
data class Car(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo("brand")
    val brand: String,

    @ColumnInfo("model")
    val model: String
)
