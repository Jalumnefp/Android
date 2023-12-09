package es.jfp.carsmanagement2.database

import androidx.room.*

@Dao
interface CarDao {
    @Query("SELECT * FROM car")
    suspend fun getAll(): List<Car>

    @Query("SELECT * FROM car WHERE id = :id")
    suspend fun findById(id: Int): Car

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(car: Car): Long

    @Update
    suspend fun update(car: Car)

    @Delete
    suspend fun delete(car: Car)

}