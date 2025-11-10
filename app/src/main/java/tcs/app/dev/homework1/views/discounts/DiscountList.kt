package tcs.app.dev.homework1.views.discounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.Discount
import tcs.app.dev.homework1.views.AddToCartIcon

@Composable
fun DiscountList(
    paddingValues: PaddingValues,
    cart: Cart,
    discounts:List<Discount> = emptyList(),
    addDiscountToCart: (Discount) -> Unit = {}
) {

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(discounts.size) { option ->
            DiscountDisplay(
                discount = discounts[option],
                onClick = {addDiscountToCart(discounts[option])},
                rightActionIcon = {
                    AddToCartIcon(
                        disabled = cart.discounts.contains(discounts[option])
                    )
                },
                modifier = Modifier
            )
        }
    }
}