package se.sabumbi.kmptemplate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.ApiUser

@Composable
fun UserCard(
    user: ApiUser,
    modifier: Modifier = Modifier,
    onLogout: () -> Unit
) {
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Hello: ${user.name} 👋")
            Button(onClick = onLogout) {
                Text("Log out")
            }
        }
    }
}