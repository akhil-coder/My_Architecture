package com.example.components.tab

import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs: List<TabViewItem>, pagerState: PagerState) {

    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screenComponent()
    }
}