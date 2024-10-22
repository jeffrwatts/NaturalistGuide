package com.jeffrwatts.naturalistguide.ui.sealife

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.jeffrwatts.naturalistguide.NaturalistTopAppBar
import com.jeffrwatts.naturalistguide.R
import com.jeffrwatts.naturalistguide.data.species.Species

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeaLifeScreen(
    modifier: Modifier = Modifier,
    viewModel: SeaLifeViewModel = hiltViewModel()
) {
    val topAppBarState = rememberTopAppBarState()
    val speciesList by viewModel.speciesList.collectAsState()

    Scaffold(
        topBar = {
            NaturalistTopAppBar(
                title = stringResource(R.string.sea_life),
                topAppBarState = topAppBarState
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            // Button row
            LazyColumn {
                items(speciesList) { species ->
                    SeaLifeListItem(species)
                }
            }
        }
    }
}

@Composable
fun SeaLifeListItem(species: Species) {
    val context = LocalContext.current  // To access the Android context for launching the browser

    // Wrap each item in a Card to delineate it and handle clicks
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {  // Handle card clicks
                species.wikipediaUrl?.let { url ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Display the species common name with a more pronounced style
            Text(
                text = species.commonName ?: "Unknown Species",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Load the mediumImageUrl using Coil, with placeholder and error handling
            species.mediumImageUrl?.let { url ->
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(url)
                            .placeholder(android.R.drawable.ic_menu_report_image)
                            .error(android.R.drawable.ic_delete)
                            .build()
                    ),
                    contentDescription = species.commonName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                        .padding(top = 8.dp)
                )
            }
        }
    }

    // Add a Divider below each item for clear separation
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 4.dp),
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
    )
}