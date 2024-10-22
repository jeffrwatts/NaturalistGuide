package com.jeffrwatts.naturalistguide.data.species

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import com.jeffrwatts.naturalistguide.network.SpeciesApi
import javax.inject.Inject

class SpeciesRepository @Inject constructor (
    private val speciesDao: SpeciesDao,
    private val api: SpeciesApi
) {

    private suspend fun refreshSpeciesData() {
        val speciesResponse = api.getSpecies()

        val speciesList = speciesResponse.results.map { speciesResult ->
            val taxon = speciesResult.taxon

            Species(
                id = taxon.id,
                speciesName = taxon.name,
                commonName = taxon.preferred_common_name,
                squareImageUrl = taxon.default_photo?.square_url,
                mediumImageUrl = taxon.default_photo?.medium_url,
                wikipediaUrl = taxon.wikipedia_url
            )
        }

        // Insert the list of species into the database
        speciesDao.insertAll(speciesList)
    }

    // Retrieve species from the local database, if empty fetch from API
    fun getSpecies(): Flow<List<Species>> = flow {
        // Check if there are any entries in the database
        val count = speciesDao.getSpeciesCount()
        if (count == 0) {
            // If database is empty, fetch from API and refresh the local database
            refreshSpeciesData()
        }

        // Emit the species from the database (it will auto-refresh via Flow)
        emitAll(speciesDao.getAllSpecies())
    }
}
