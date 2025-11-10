package tcs.app.dev.homework1.views.discounts

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Discount
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tcs.app.dev.R
import tcs.app.dev.homework1.data.Discount
import tcs.app.dev.homework1.data.Item
import tcs.app.dev.homework1.views.RemoveFromCartIcon
import tcs.app.dev.ui.theme.AppTheme

@Composable
fun DiscountDisplay(
    discount: Discount,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    rightActionIcon: @Composable () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .combinedClickable(
                onClick = {},
                onLongClick = { }
            ),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    Icons.Filled.Discount,
                    contentDescription = stringResource(R.string.description_go_to_cart),
                    modifier = Modifier.padding(horizontal = 4.dp).size(32.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )

                Column(
                    modifier = Modifier.weight(1f).padding(horizontal = 8.dp).heightIn(min = 32.dp, max = 64.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.label_discount),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    DiscountDescription(
                        discount = discount,
                    )
                }

                Column(
                    modifier = Modifier.padding(end = 0.dp).heightIn(min = 32.dp, max = 64.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    rightActionIcon()
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiscountDisplayPreview() {
    AppTheme {
        DiscountDisplay(
            discount = Discount.Bundle(Item("apple"), 5u, 3u),
            onClick = {},
            rightActionIcon = { RemoveFromCartIcon() },
            modifier = Modifier
        )
    }
}