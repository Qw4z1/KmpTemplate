package se.sabumbi.kmptemplate.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SmallSpace() {
    Spacer(Modifier.size(8.dp))
}

@Composable
fun MediumSpace() {
    Spacer(Modifier.size(16.dp))
}

@Composable
fun LargeSpace() {
    Spacer(Modifier.size(32.dp))
}

@Composable
fun HugeSpace() {
    Spacer(Modifier.size(64.dp))
}