package com.jeffrwatts.naturalistguide.data.species

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Species::class], version = 1)
abstract class NaturalistDatabase : RoomDatabase() {
    abstract fun speciesDao(): SpeciesDao
}
