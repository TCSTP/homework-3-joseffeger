package tcs.app.dev.homework1.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import tcs.app.dev.R

data class TabItem(val labelRes: Int, val icon: ImageVector, val iconDesc: Int)

val DEFAULT_TABS = listOf(
    TabItem(R.string.label_shop, Icons.Filled.Shop, iconDesc = R.string.tab_description_shop),
    TabItem(R.string.label_discounts, Icons.Filled.LocalOffer, iconDesc = R.string.tab_description_discounts)
)

@Composable
fun ScaffoldWrapper(
    tabs: List<TabItem> = DEFAULT_TABS,
    selectedIndex: Int = 0,
    onTabSelected: (Int) -> Unit = {},
    rightActionIcon: @Composable (Modifier) -> Unit = { _ -> },
    onRightIconClick: () -> Unit = {},
    content: @Composable (paddingValues: PaddingValues) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar =
            {
                CustomAppBar(
                    title = stringResource(R.string.label_shop),
                    leftActionIcon = { _ ->
                    },
                    rightActionIcon = { modifier ->
                        rightActionIcon( modifier.padding(start = 4.dp, end = 8.dp).clickable(
                            onClick = onRightIconClick
                        ))
                    },
                    modifier = modifier
                )
            },
        bottomBar = {
            PrimaryTabRow(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                selectedTabIndex = 0, modifier = modifier.navigationBarsPadding()) {
                tabs.forEachIndexed { index, item ->

                    var mod: Modifier = Modifier
                    if (selectedIndex != index) {
                        mod = mod.background(MaterialTheme.colorScheme.secondary);
                    }

                    Tab(
                        selected = selectedIndex == index,
                        modifier = mod,
                        onClick = {
                            onTabSelected(index);
                        },
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = stringResource(item.iconDesc),
                                modifier = Modifier.size(if (selectedIndex == index) 24.dp else 15.dp),
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                        text = {
                            Text(
                                text = stringResource(item.labelRes),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        content(innerPadding)

    }
}