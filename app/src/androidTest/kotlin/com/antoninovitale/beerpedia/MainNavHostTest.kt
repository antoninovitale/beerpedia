package com.antoninovitale.beerpedia

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainNavHostTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testListAndNavigationToDetails() {
        hiltRule.inject()

        // List is displayed
        composeTestRule.waitUntilExactlyOneExists(hasTestTag("ListViewTag"))
        composeTestRule.onNodeWithTag("ListViewTag")
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag("ListViewTitleTag")
            .assertIsDisplayed()
            .assertTextEquals("Beers from PunkAPI")

        // Items are visible
        composeTestRule.onNodeWithTag("ListViewItemsTag")
            .assertIsDisplayed()
            .performScrollToNode(hasTestTag("BeerRowTag 1"))
            .assertIsDisplayed()
            .performScrollToNode(hasTestTag("BeerRowTag 2"))
            .assertIsDisplayed()
            .performScrollToNode(hasTestTag("BeerRowTag 3"))
            .assertIsDisplayed()

        assertItem(1)
        assertItem(2)
        assertItem(3)

        // Navigate to details
        composeTestRule.onNodeWithTag("BeerRowTag 2")
            .performClick()

        composeTestRule.waitUntilExactlyOneExists(hasTestTag("DetailsTag"))
        composeTestRule.onNodeWithTag(("DetailsTag"))
            .assertIsDisplayed()
    }

    private fun assertItem(id: Int) {
        composeTestRule.onNodeWithTag("BeerRowTag $id")
            .assertTextEquals("Beer $id", "Tagline $id")
            .assertHasClickAction()
    }
}
