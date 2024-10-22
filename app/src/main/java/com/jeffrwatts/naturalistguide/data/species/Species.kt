package com.jeffrwatts.naturalistguide.data.species

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "species")
data class Species(
    @PrimaryKey val id: Int,
    val speciesName: String,
    val commonName: String?,
    val squareImageUrl: String?,
    val mediumImageUrl: String?,
    val wikipediaUrl: String?
)
