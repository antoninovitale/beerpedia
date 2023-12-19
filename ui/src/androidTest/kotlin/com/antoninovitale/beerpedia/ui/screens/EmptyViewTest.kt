package com.antoninovitale.beerpedia.ui.screens

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class EmptyViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testEmptyViewIsDisplayedWithContent() {
        composeTestRule.setContent {
            EmptyViewContent {}
        }

        composeTestRule
            .onNodeWithTag("EmptyViewTag")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Oops!")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("It seems like we had no luck getting you a beer this time.\nPlease try again later.")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Refresh")
            .assertIsDisplayed()
            .assertHasClickAction()
    }
}
