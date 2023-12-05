package es.jfp.carsmanagement2.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Car::class],
    version = 1
)
abstract class AppDB: RoomDatabase() {
}