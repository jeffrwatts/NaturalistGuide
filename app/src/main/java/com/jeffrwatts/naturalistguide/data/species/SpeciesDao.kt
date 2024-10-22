package com.jeffrwatts.naturalistguide.data.species

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SpeciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(species: List<Species>)

    @Query("SELECT * FROM species WHERE id = :speciesId")
    fun getSpeciesById(speciesId: Int): Flow<Species?>

    @Query("SELECT * FROM species")
    fun getAllSpecies(): Flow<List<Species>>

    @Query("DELETE FROM species")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM species")
    suspend fun getSpeciesCount(): Int
}
