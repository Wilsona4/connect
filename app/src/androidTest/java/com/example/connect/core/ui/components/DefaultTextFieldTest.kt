package com.example.connect.core.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.connect.MainActivity
import com.example.connect.core.util.TestTags
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DefaultTextFieldTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun enterTooLongString() {
        composeTestRule.setContent {
            var text by remember {
                mutableStateOf("")
            }
            MaterialTheme {
                DefaultTextField(
                    text = text,
                    onValueChange = {
                        text = it
                    },
                    modifier = Modifier
                        .semantics {
                            testTag = TestTags.DEFAULT_TEXT_FIELD
                        }
                )
            }
        }

        val expectedString = "wilso"

        composeTestRule
            .onNodeWithTag(TestTags.DEFAULT_TEXT_FIELD)
            .performTextClearance()
        composeTestRule
            .onNodeWithTag(TestTags.DEFAULT_TEXT_FIELD)
            .performTextInput(expectedString)
        composeTestRule
            .onNodeWithTag(TestTags.DEFAULT_TEXT_FIELD)
            .performTextInput("n")

        composeTestRule
            .onNodeWithTag(TestTags.DEFAULT_TEXT_FIELD)
            .assertTextEquals(expectedString)
    }
}