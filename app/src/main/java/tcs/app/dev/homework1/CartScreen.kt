package tcs.app.dev.homework1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tcs.app.dev.R
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.Discount
import tcs.app.dev.homework1.data.Item
import tcs.app.dev.homework1.data.Shop
import tcs.app.dev.homework1.views.cart.CartBottomBar
import tcs.app.dev.homework1.views.CustomAppBar
import tcs.app.dev.homework1.views.cart.CartItemList

@Composable
fun CartScreen(
    shop: Shop,
    cart: Cart,
    modifier: Modifier = Modifier,
    onItemQuantityUpdate: (item: Item, newQty: UInt) -> Unit = { _, _ ->},
    onRemoveDiscount: (discount: Discount) -> Unit = { _ ->},
    doPlaceOrder: () -> Unit = { },
    showShopScreen: () -> Unit = {},
) {

    Scaffold(
        topBar =
            {
                CustomAppBar(
                    title = stringResource(R.string.label_cart),
                    leftActionIcon = { _ ->
                        Icon(
                            Icons.Filled.ArrowBackIosNew,
                            contentDescription = stringResource(R.string.description_go_to_shop),
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(32.dp).clickable(onClick = showShopScreen)
                        )
                    },
                    modifier = modifier
                )
            },
        bottomBar = {
            CartBottomBar(
                cart = cart,
                doPlaceOrder = doPlaceOrder
            )
        }
    ) { innerPadding ->

        CartItemList(
            shop = shop,
            cart = cart,
            paddingValues = innerPadding,
            onItemQuantityUpdate = onItemQuantityUpdate,
            onRemoveDiscount = onRemoveDiscount
        )
    }

}