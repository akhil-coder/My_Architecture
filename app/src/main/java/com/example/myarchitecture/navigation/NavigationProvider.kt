package com.example.myarchitecture.navigation

import com.example.auth.navigation.AuthNavigation
import com.example.movie.navigation.MovieNavigation
import com.example.my_list.navigation.MyListNavigation
import com.example.profile.navigation.ProfileNavigation
import com.example.tvShow.navigation.TvShowScreenNavigation
import com.example.ui_main.navigation.UiMainNavigation

data class NavigationProvider(
    val uiMainNavigation: UiMainNavigation,
    val tvShowScreenNavigation: TvShowScreenNavigation,
    val authNavigation: AuthNavigation,
    val movieNavigation: MovieNavigation,
    val profileNavigation: ProfileNavigation,
    val myListNavigation: MyListNavigation
)
