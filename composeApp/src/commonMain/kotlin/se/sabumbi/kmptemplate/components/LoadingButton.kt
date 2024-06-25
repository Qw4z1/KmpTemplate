package se.sabumbi.kmptemplate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class LoadingButtonStyle {
    PLAIN, BOXED
}

@Composable
fun LoadingButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    style: LoadingButtonStyle = LoadingButtonStyle.PLAIN
) {
    if (style == LoadingButtonStyle.PLAIN) {
        Plain(
            text = text,
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            loading = loading
        )
    } else if (style == LoadingButtonStyle.BOXED) {
        Boxed(
            text = text,
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            loading = loading
        )
    }
}

@Composable
private fun Plain(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false
) {
    val click = if (!loading) onClick else {
        {}
    }
    Button(
        modifier = Modifier.fillMaxWidth().composed { modifier },
        onClick = click,
        enabled = enabled
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(16.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        } else {
            Text(
                text,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

val BoxedButtonHeight = 72.dp

@Composable
private fun Boxed(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0x66FFFFFF),
                        Color(0xCCFFFFFF),
                        Color.White
                    )
                )
            ).composed { modifier }
    ) {
        SmallSpace()
        Plain(
            text,
            enabled = enabled,
            onClick = onClick,
            loading = loading,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        )
    }
}