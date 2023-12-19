package com.antoninovitale.beerpedia.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.antoninovitale.beerpedia.domain.Beer
import com.antoninovitale.beerpedia.domain.Ingredient
import com.antoninovitale.beerpedia.ui.BeerViewModel
import com.antoninovitale.beerpedia.ui.R
import com.antoninovitale.beerpedia.ui.UiState
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

/**
 * This composable function handles the [UiState] provided by the [BeerViewModel].
 * Based on the state received, an [EmptyView], a [LoadingView] or a [ListView] will be presented to the user.
 *
 * @param viewModel [BeerViewModel] injected using [hiltViewModel]
 * @param onNavigateToDetails callback to use to retrieve the id of the item to see details of.
 */
@Composable
internal fun BeerListScreen(
    viewModel: BeerViewModel = hiltViewModel(),
    onNavigateToDetails: (Int) -> Unit
) {
    when (val state = viewModel.state.collectAsState().value) {
        UiState.Empty,
        UiState.Error -> EmptyView()

        UiState.Loading -> LoadingView()

        is UiState.Loaded -> ListView(
            beers = state.beers,
            onNavigateToDetails = onNavigateToDetails
        )
    }
}

@Composable
private fun ListView(
    beers: List<Beer>,
    onNavigateToDetails: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .testTag("ListViewTag")
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("ListViewTitleTag"),
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            text = stringResource(id = R.string.beer_list_title)
        )
        Spacer(modifier = Modifier.size(16.dp))
        LazyColumn(
            modifier = Modifier
                .animateContentSize()
                .testTag("ListViewItemsTag"),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = beers,
                key = { beer -> beer.id }
            ) { beer ->
                BeerRow(
                    beer = beer,
                    onNavigateToDetails = onNavigateToDetails
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun BeerRow(beer: Beer, onNavigateToDetails: (Int) -> Unit) {
    Card(
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = { onNavigateToDetails.invoke(beer.id) })
                .padding(all = 8.dp)
                .testTag("BeerRowTag ${beer.id}")
        ) {
            GlideImage(
                model = beer.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(40.dp)
            )
            Column {
                Text(
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                    text = beer.name
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    style = TextStyle(fontSize = 14.sp, fontStyle = FontStyle.Italic),
                    text = beer.tagline
                )
            }
        }
    }
}

// Note that preview on GlideImage doesn't work yet.
//region preview
@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true
)
@Composable
private fun DetailsPreview(
    @PreviewParameter(ListViewPreviewParameterProvider::class)
    beers: List<Beer>
) {
    ListView(
        beers = beers,
        onNavigateToDetails = {}
    )
}

private class ListViewPreviewParameterProvider : PreviewParameterProvider<List<Beer>> {

    override val values = sequenceOf(
        listOf(
            Beer(
                id = 1,
                name = "Super Beer 1",
                tagline = "A beer to remember",
                description = "Enjoy this beer with friends and family.",
                imageUrl = "https://images.punkapi.com/v2/192.png",
                ingredients = listOf(
                    Ingredient.Malt(name = "Malt 1", amount = "50 grams"),
                    Ingredient.Hop(name = "Hop 1", amount = "10 kilograms"),
                    Ingredient.Yeast(name = "Yeast 1")
                )
            ),
            Beer(
                id = 2,
                name = "Super Beer 2",
                tagline = "",
                description = "Enjoy this beer with colleagues after work.",
                imageUrl = "https://images.punkapi.com/v2/32.png",
                ingredients = listOf(
                    Ingredient.Malt(name = "Malt 2", amount = "10 grams"),
                    Ingredient.Hop(name = "Hop 2", amount = "50 kilograms"),
                    Ingredient.Yeast(name = "Yeast 2")
                )
            )
        )
    )
}
//endregion
