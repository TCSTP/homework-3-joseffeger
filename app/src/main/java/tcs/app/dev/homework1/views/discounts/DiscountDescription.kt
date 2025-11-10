package tcs.app.dev.homework1.views.discounts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import tcs.app.dev.R
import tcs.app.dev.homework1.data.Discount
import tcs.app.dev.homework1.data.MockData

@Composable
fun DiscountDescription(
    discount: Discount,
) {
    var text:String = ""
    when(discount){
        is Discount.Fixed -> {
            text = stringResource( R.string.amount_off, discount.amount)
        }
        is Discount.Percentage -> {
            text = stringResource(R.string.percentage_off, discount.value)
        }
        is Discount.Bundle -> {
            text = stringResource(R.string.pay_n_items_and_get, discount.amountItemsPay, stringResource(MockData.getName(discount.item)), discount.amountItemsGet)
        }
    }
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .padding(horizontal = 16.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}