package tcs.app.dev.homework1.views.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import tcs.app.dev.R
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.Discount
import tcs.app.dev.homework1.data.Item
import tcs.app.dev.homework1.data.MockData
import tcs.app.dev.homework1.data.Shop
import tcs.app.dev.homework1.data.euro
import tcs.app.dev.homework1.data.times
import tcs.app.dev.homework1.views.RemoveFromCartIcon
import tcs.app.dev.homework1.views.discounts.DiscountDisplay

@Composable
fun CartItemList(
    paddingValues: PaddingValues,
    shop: Shop,
    cart: Cart,
    onItemQuantityUpdate: (item: Item, newQty: UInt) -> Unit = {_, _ ->},
    onRemoveDiscount: (discount: Discount) -> Unit = { _ -> }
) {
    val entries = cart.items.entries.toList();
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(entries.size, key = {entries[it].key.id}) { index ->
           val (item, qty) = entries[index];
            CartItem(
                name = MockData.getName(item),
                image = MockData.getImage(item),
                totalCost = shop.prices[item]?.times(qty) ?: 0u.euro,
                quantity = qty,
                modifier = Modifier,
                onQtyUpdate = { newQty ->
                    onItemQuantityUpdate(item, newQty)
                }
            )
        }

        if (cart.discounts.isNotEmpty()) {
            item {
                HorizontalDivider()
                Text(
                    text = stringResource(R.string.label_discount),
                    style = MaterialTheme.typography.titleMedium,
                )
            }

            items(cart.discounts.size, key = {cart.discounts[it]}) { index ->
                DiscountDisplay(
                    discount = cart.discounts[index],
                    modifier = Modifier,
                    rightActionIcon = {
                        RemoveFromCartIcon()
                    },
                    onClick = { onRemoveDiscount(cart.discounts[index]) }
                )
            }
        }
    }
}