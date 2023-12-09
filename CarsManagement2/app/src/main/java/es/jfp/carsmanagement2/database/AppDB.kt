package es.jfp.carsmanagement2.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context


@Database(
    entities = [Car::class],
    version = 1
)
abstract class AppDB: RoomDatabase() {
    abstract fun carDao(): CarDao
    companion object {
        private const val DATABASE_NAME = "APP_DB"

        private var instance: AppDB? = null
        fun getInstance(context: Context): AppDB {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDB::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return instance!!
        }
    }
}