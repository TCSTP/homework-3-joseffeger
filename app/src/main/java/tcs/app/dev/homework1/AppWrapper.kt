package tcs.app.dev.homework1

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.MockData
import tcs.app.dev.homework1.data.minus
import tcs.app.dev.homework1.data.plus
import tcs.app.dev.homework1.data.set

@Composable
fun AppWrapper(
    modifier: Modifier = Modifier
) {
    var cart by rememberSaveable { mutableStateOf(Cart(shop = MockData.ExampleShop)) }
    var showCart by rememberSaveable { mutableStateOf(false) }

    Crossfade(targetState = showCart, animationSpec = tween(durationMillis = 300)) { isCart ->
        if (isCart) {
            CartScreen(
                cart = cart,
                shop = MockData.ExampleShop,
                onItemQuantityUpdate = { item, newQty ->
                    cart = cart.set(item to newQty)
                },
                onRemoveDiscount = { discount -> cart -= discount },
                doPlaceOrder = {
                    cart = Cart(shop = MockData.ExampleShop)
                    showCart = false
                },
                showShopScreen = { showCart = false }
            )
        } else {
            ShopScreen(
                shop = MockData.ExampleShop,
                cart = cart,
                availableDiscounts = MockData.ExampleDiscounts,
                addItemToCart = { item ->
                    cart += (item to 1u)
                },
                addDiscountToCart = { discount ->
                    if (!cart.discounts.contains(discount)) {
                        cart += discount
                    }
                },
                onShowCart = {
                    if (cart.totalCount > 0u) {
                        showCart = true
                    }
                },
                modifier = modifier
            )
        }
    }




}