package com.chavesdev.mvvmsamplecode.movies.popular.presentation.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.chavesdev.mvvmsamplecode.MainActivity
import com.chavesdev.mvvmsamplecode.R
import org.junit.Rule
import org.junit.Test

class PopularMovieListScreenKtTest{

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun verifyTitle() {
        val title = composeTestRule.activity.getString(R.string.title_popular_movies)
        composeTestRule.onNodeWithText(title).assertExists()
    }
}