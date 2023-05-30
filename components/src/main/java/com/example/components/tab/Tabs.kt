package com.example.components.tab

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabViewItem>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.surface,
        indicator = { tabPosition ->
            TabRowDefaults.Indicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier.pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPosition)
            )
        },

    ) {

        tabs.forEachIndexed { index, tabViewItem ->


            LeadingIconTab(
                selected = pagerState.currentPage == index,
                icon = {
                       Icon(imageVector = tabViewItem.icon, contentDescription = tabViewItem.title)
                },
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { 
                    Text(text = tabViewItem.title)
                }
            )
        }

    }

}