package tcs.app.dev.homework1.views.items

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
import tcs.app.dev.homework1.data.Item
import tcs.app.dev.homework1.data.MockData
import tcs.app.dev.homework1.data.Shop
import tcs.app.dev.homework1.data.euro

@Composable
fun ItemList (
    paddingValues: PaddingValues,
    shop: Shop,
    addItemToCart: (Item) -> Unit,
) {

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(shop.items.size, key = {ind -> shop.items.elementAt(ind).id}) { option ->
            val item: Item = shop.items.elementAt(option);
            ItemDisplay(
                name = MockData.getName(item),
                image = MockData.getImage(item),
                modifier = Modifier,
                price = shop.prices[item] ?: 0u.euro,
                onClick = {
                    addItemToCart(item);
                }
            )
        }
    }
}