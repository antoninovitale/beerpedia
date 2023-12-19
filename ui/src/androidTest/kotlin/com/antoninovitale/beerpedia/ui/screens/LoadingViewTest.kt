package com.antoninovitale.beerpedia.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class LoadingViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoadingViewIsDisplayed() {
        composeTestRule.setContent {
            LoadingView()
        }

        composeTestRule
            .onNodeWithTag("LoadingViewTag")
            .assertIsDisplayed()
    }
}
