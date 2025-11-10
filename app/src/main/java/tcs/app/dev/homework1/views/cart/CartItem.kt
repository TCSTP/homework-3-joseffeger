package tcs.app.dev.homework1.views.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import tcs.app.dev.ui.theme.AppTheme
import kotlin.math.roundToInt

@Composable
fun CartItem (
    name:Int,
    image:Int,
    quantity: UInt,
    totalCost: Euro,
    onQtyUpdate: (UInt) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val minQty = 0f;
    val maxQty = 99f;

    var sliderValue by rememberSaveable { mutableStateOf(quantity.toFloat()) }

    LaunchedEffect(quantity) {
        sliderValue = quantity.toFloat()
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Column {
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

                Text(
                    text = stringResource(id = name),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Column(
                    modifier = Modifier.padding(horizontal = 8.dp).heightIn(min = 32.dp, max = 64.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = stringResource(R.string.total_label),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = totalCost.toString(),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(start = 4.dp, end = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.label_amount)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Not really usable for fine adjustments though
                    Slider(
                        value = sliderValue,
                        onValueChange = { newFloat ->
                            sliderValue = newFloat
                        },
                        onValueChangeFinished = {
                            val newInt = sliderValue.roundToInt().coerceIn(minQty.toInt(), maxQty.toInt()).toUInt()
                            onQtyUpdate(newInt)
                        },
                        valueRange = minQty..maxQty,
                        steps = (maxQty - minQty - 1).coerceAtLeast(0f).toInt(),
                        colors = SliderDefaults.colors(
                            activeTrackColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "${sliderValue.roundToInt()}x",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
    AppTheme {
        CartItem(R.string.name_golden_fig, R.drawable.golden_fig, quantity = 5u, 16u.euro + 80u.cents)
    }
}