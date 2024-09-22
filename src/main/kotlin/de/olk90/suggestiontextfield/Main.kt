package de.olk90.suggestiontextfield

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun App() {

    var label by remember { mutableStateOf("") }
    val input = remember { mutableStateOf("") }

    val onContentUpdate: (String) -> Unit = {
        label =
            if (fruitList.contains(it)) "You selected $it"
            else "No selection made"
    }

    MaterialTheme {
        Column {
            SuggestionTextField(
                input = input,
                suggestions = fruitList,
                onContentUpdate = onContentUpdate
            )

            Text(text = label)

        }

    }
}

fun main() = application {
    Window(title = "Suggestion TextField Example", onCloseRequest = ::exitApplication) {
        App()
    }
}
