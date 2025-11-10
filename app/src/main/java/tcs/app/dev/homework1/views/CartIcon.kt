package tcs.app.dev.homework1.views

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import tcs.app.dev.R
import tcs.app.dev.homework1.data.Cart

@Composable
fun CartIcon(
    cart: Cart,
    modifier: Modifier = Modifier,
    iconSize: Dp = 32.dp
) {

    BadgedBox(
        modifier = modifier,
        badge = {
            Crossfade(targetState = cart.itemCount, animationSpec = tween(150)) { count ->
                if (count > 0u) {
                    Badge {
                        if (count > 99u) {
                            Text(stringResource(R.string.more_than_99))
                        } else {
                            Text(count.toString())
                        }
                    }
                }
            }
        }
    ) {
        Icon(
            Icons.Filled.ShoppingCart,
            contentDescription = stringResource(R.string.description_go_to_cart),
            tint = if (cart.itemCount > 0u) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(iconSize)
        )
    }

}