package tcs.app.dev.homework1.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomAppBar(
    title:String,
    modifier: Modifier = Modifier,
    leftActionIcon: @Composable (Modifier) -> Unit = { _ ->},
    rightActionIcon: @Composable (Modifier) -> Unit = { _ ->},
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 15.dp, start = 4.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        leftActionIcon(modifier)
        Text(
            title,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        rightActionIcon(modifier)
    }
}