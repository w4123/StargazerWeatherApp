package com.stargazerweatherapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.text.font.FontWeight
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun Glossary(navigateBack: () -> Unit = {})
{
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Glossary")
            },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                })
        }
    ) {
        Text(modifier = Modifier.padding(it), text = "Crescent Moon", fontWeight= FontWeight.Bold)
        Text(modifier = Modifier.padding((it)), text = "Lorem Ipsum")
    }
}