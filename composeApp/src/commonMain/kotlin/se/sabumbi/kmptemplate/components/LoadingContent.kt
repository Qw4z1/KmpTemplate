package se.sabumbi.kmptemplate.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun LoadingContent(
    modifier: Modifier = Modifier,
    color: Color = ProgressIndicatorDefaults.circularColor
) {
    CircularProgressIndicator(
        color = color,
        modifier = Modifier.size(
            64.dp
        ).composed { modifier }
    )
}

@Composable
fun FullScreenLoadingContent(paddingValues: PaddingValues = PaddingValues(0.dp)) {
    Box(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
    ) {
        LoadingContent(
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}