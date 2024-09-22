package de.olk90.suggestiontextfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SuggestionTextField(
    input: MutableState<String>,
    suggestions: List<String>,
    onContentUpdate: (String) -> Unit
) {

    var text by remember { mutableStateOf(input.value) }
    var expanded by remember { mutableStateOf(false) }
    val filteredSuggestions = suggestions.filter { it.contains(input.value, ignoreCase = true) }

    Column {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                onContentUpdate(it)
                expanded = true
            },
            label = { Text("Enter a fruit ...") },
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    onContentUpdate("")
                }) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
                }
            }
        )

        DropdownMenu(
            expanded = expanded && filteredSuggestions.isNotEmpty(),
            modifier = Modifier.padding(10.dp).fillMaxWidth(.3f).fillMaxHeight(.5f),
            onDismissRequest = { expanded = false }
        ) {
            filteredSuggestions.forEach { kw ->
                DropdownMenuItem(onClick = {
                    text = kw
                    onContentUpdate(kw)
                    expanded = false
                }) {
                    Text(text = kw)
                }
            }
        }

    }
}