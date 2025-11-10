package tcs.app.dev.homework1.views.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tcs.app.dev.R
import tcs.app.dev.homework1.data.Euro
import tcs.app.dev.homework1.data.cents
import tcs.app.dev.homework1.data.euro
import tcs.app.dev.homework1.data.plus
import tcs.app.dev.homework1.views.AddToCartIcon
import tcs.app.dev.ui.theme.AppTheme

@Composable
fun ItemDisplay(name:Int, image:Int, price: Euro, modifier: Modifier, onClick: () -> Unit = {}) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = stringResource(id = name),
                modifier = Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp).heightIn(min = 32.dp, max = 64.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = name),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = price.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            AddToCartIcon()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RadioRowSelectedPreview() {
    AppTheme {
        ItemDisplay(R.string.name_golden_fig, R.drawable.golden_fig, 16u.euro + 80u.cents, Modifier)
    }
}