package tcs.app.dev.homework1.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import tcs.app.dev.R

@Composable
fun AddToCartIcon (
    text:String = stringResource( R.string.description_add_to_cart ),
    disabled: Boolean = false,
    disabledLineColor: Color = MaterialTheme.colorScheme.error,
    disabledLineStroke: Dp = 2.dp,
    modifier: Modifier = Modifier
) {

    val strikeModifier = modifier
        .drawBehind {
            if (disabled) {
                drawLine(
                    color = disabledLineColor,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, 0f),
                    strokeWidth = disabledLineStroke.toPx()
                )
            }
        }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = strikeModifier.padding(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = text,
            modifier = Modifier
                .size(32.dp)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }

}