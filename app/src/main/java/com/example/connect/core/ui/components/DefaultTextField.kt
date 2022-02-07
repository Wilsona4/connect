package com.example.connect.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.connect.core.ui.theme.Dimen_25dp
import com.example.connect.core.ui.theme.Dimen_8dp
import com.example.connect.core.util.TestTags


@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    error: String = "",
    maxLines: Int = Int.MAX_VALUE,
    isSingleLine: Boolean = false,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    isPasswordVisible: Boolean = false,
    onPasswordToggleClicked: (Boolean) -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.onSurface,
    style: TextStyle = TextStyle(
        color = MaterialTheme.colors.onBackground,
    ),
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        TextField(
            value = text,
            onValueChange = {
                if (it.length <= maxLines) {
                    onValueChange(it)
                }
            },
            maxLines = maxLines,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.body1
                )
            },
            textStyle = style,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = backgroundColor
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            isError = error.isNotEmpty(),
            singleLine = isSingleLine,
            leadingIcon = if (leadingIcon != null) {
                val icon: @Composable () -> Unit = {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier.size(Dimen_25dp)
                    )
                }
                icon
            } else null,
            visualTransformation = if (!isPasswordVisible && isPasswordToggleDisplayed) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            trailingIcon = if (isPasswordToggleDisplayed) {
                val icon: @Composable () -> Unit = {
                    IconButton(
                        onClick = {
                            onPasswordToggleClicked(!isPasswordVisible)
                        },
                        modifier = Modifier
                            .semantics {
                                testTag = TestTags.PASSWORD_TOGGLE
                            }) {
                        Icon(
                            imageVector = if (isPasswordVisible) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            },
                            tint = Color.White,
                            contentDescription = if (isPasswordVisible) {
                                stringResource(id = com.example.connect.R.string.password_visible_content_description)
                            } else {
                                stringResource(id = com.example.connect.R.string.password_hidden_content_description)
                            }

                        )
                    }
                }
                icon
            } else null,

            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    testTag = TestTags.DEFAULT_TEXT_FIELD
                }
        )
        if (error.isNotEmpty()) {
            Text(
                text = error,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = Dimen_8dp)
            )
        }

    }

}

//TODO fix preview
@Preview
@Composable
fun DefaultTextFieldPreview() {
    var text by remember {
        mutableStateOf("Wilson")
    }
    DefaultTextField(
        text = text,
        hint = "Name",
        error = "Error",
        isSingleLine = true,
        maxLines = 1,
        keyboardType = KeyboardType.Password,
        isPasswordVisible = true,
        onValueChange = {
            text = it
        }
    )
}