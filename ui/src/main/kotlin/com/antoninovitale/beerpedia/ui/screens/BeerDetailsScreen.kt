package com.antoninovitale.beerpedia.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
 * Based on the state received, an [EmptyView], a [LoadingView] or a [DetailsView] will be presented to the user.
 * The [DetailsView] will show name, image, description and ingredients of the selected beer.
 * Ingredients, if any, will be displayed in a [LazyRow] scrollable horizontally.
 * Note that the view falls back to an [EmptyView] in case the beer is not found.
 *
 * @param beerId id of the beer to show details for
 * @param viewModel [BeerViewModel] injected using [hiltViewModel]
 */
@Composable
internal fun BeerDetailsScreen(
    beerId: Int,
    viewModel: BeerViewModel = hiltViewModel()
) {
    when (val state = viewModel.state.collectAsState().value) {
        UiState.Error,
        UiState.Empty -> EmptyView()

        UiState.Loading -> LoadingView()
        is UiState.Loaded -> {
            val beer = state.beers.firstOrNull { it.id == beerId }

            if (beer != null) {
                DetailsView(beer)
            } else {
                EmptyView()
            }
        }
    }
}

@Composable
private fun DetailsView(beer: Beer) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .verticalScroll(rememberScrollState())
            .testTag("DetailsTag")
    ) {
        Header(beer)
        Spacer(modifier = Modifier.size(24.dp))
        Description(beer)
        Spacer(modifier = Modifier.size(24.dp))
        Ingredients(beer)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun Header(beer: Beer) {
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            text = beer.name
        )
        Spacer(modifier = Modifier.size(16.dp))
        GlideImage(
            alignment = Alignment.Center,
            model = beer.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(240.dp)
        )
    }
}

@Composable
private fun Description(beer: Beer) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
        text = stringResource(id = R.string.beer_details_description_title)
    )
    Spacer(modifier = Modifier.size(8.dp))
    Text(
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(fontSize = 16.sp),
        text = beer.description
    )
}

@Composable
private fun Ingredients(beer: Beer) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
        text = stringResource(id = R.string.beer_details_ingredients_title)
    )
    Spacer(modifier = Modifier.size(8.dp))
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        items(beer.ingredients) { ingredient ->
            when (ingredient) {
                is Ingredient.Hop ->
                    Ingredient(
                        name = ingredient.name,
                        amount = ingredient.amount,
                        label = stringResource(id = R.string.beer_details_hop_label)
                    )

                is Ingredient.Malt ->
                    Ingredient(
                        name = ingredient.name,
                        amount = ingredient.amount,
                        label = stringResource(id = R.string.beer_details_malt_label)
                    )

                is Ingredient.Yeast ->
                    Ingredient(
                        name = ingredient.name,
                        amount = null,
                        label = stringResource(id = R.string.beer_details_yeast_label)
                    )
            }
        }
    }
}

@Composable
private fun Ingredient(name: String, amount: String?, label: String) {
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(fontSize = 16.sp),
            text = name
        )
        amount?.let {
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(fontSize = 14.sp),
                text = it
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            style = TextStyle(fontSize = 12.sp, fontStyle = FontStyle.Italic),
            text = label
        )
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
    @PreviewParameter(BeerDetailsScreenPreviewParameterProvider::class)
    beer: Beer
) {
    DetailsView(beer)
}

private class BeerDetailsScreenPreviewParameterProvider :
    PreviewParameterProvider<Beer> {

    override val values = sequenceOf(
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
            description = "",
            imageUrl = "https://images.punkapi.com/v2/32.png",
            ingredients = emptyList()
        )
    )
}
//endregion
