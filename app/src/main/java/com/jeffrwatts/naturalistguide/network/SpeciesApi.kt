package com.jeffrwatts.naturalistguide.network

import retrofit2.http.GET
import retrofit2.http.Query

interface SpeciesApi {
    @GET("v1/observations/species_counts")
    suspend fun getSpecies(
        @Query("place_id") placeId: Int = 11,  // Hawaii place ID
        @Query("iconic_taxa") iconicTaxa: String = "Actinopterygii"  // Ray-finned fishes
    ): SpeciesResponse
}

data class SpeciesResponse(
    val results: List<SpeciesResult>
)

data class SpeciesResult(
    val count: Int,
    val taxon: Taxon // Taxon is nested under 'taxon'
)

data class Taxon(
    val id: Int,
    val name: String,
    val preferred_common_name: String?,
    val rank: String,
    val count: Int,
    val default_photo: DefaultPhoto?,
    val wikipedia_url: String?
)

data class DefaultPhoto(
    val square_url: String?,
    val medium_url: String?
)