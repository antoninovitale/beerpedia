package com.antoninovitale.beerpedia.ui.screens

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.antoninovitale.beerpedia.ui.BeerViewModel
import com.antoninovitale.beerpedia.ui.R

/**
 * This is the default composable used when no items can be fetched from available data sources.
 * The view contains a refresh button to try loading again.
 *
 * @param viewModel [BeerViewModel] injected using [hiltViewModel]
 */
@Composable
internal fun EmptyView(
    viewModel: BeerViewModel = hiltViewModel()
) {
    EmptyViewContent(viewModel::start)
}

@VisibleForTesting
@Composable
internal fun EmptyViewContent(onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .testTag("EmptyViewTag"),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            text = stringResource(id = R.string.beer_details_empty_message_title)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            text = stringResource(id = R.string.beer_details_empty_message)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Button(
            onClick = onButtonClick
        ) {
            Text(
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                text = stringResource(id = R.string.beer_details_empty_message_button)
            )
        }
    }
}

/**
 * This is the default composable used to render a loading state.
 */
@Composable
internal fun LoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .testTag("LoadingViewTag"),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp),
        )
    }
}

//region preview
@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true
)
@Composable
private fun ViewsPreview(
    @PreviewParameter(ViewsPreviewPreviewParameterProvider::class)
    preview: PreviewParameters
) {
    when {
        preview.showEmptyView -> EmptyViewContent {}
        preview.showLoadingView -> LoadingView()
    }
}

private class ViewsPreviewPreviewParameterProvider : PreviewParameterProvider<PreviewParameters> {

    override val values = sequenceOf(
        PreviewParameters(
            showEmptyView = true,
            showLoadingView = false
        ),
        PreviewParameters(
            showEmptyView = false,
            showLoadingView = true
        )
    )
}

private data class PreviewParameters(
    val showEmptyView: Boolean,
    val showLoadingView: Boolean
)
//endregion
