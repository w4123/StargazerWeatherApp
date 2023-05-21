package com.stargazerweatherapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
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
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                Column(Modifier.padding(12.dp)) {
                    Text(text = "Crescent Moon", fontWeight= FontWeight.Bold)
                    Text(
                        buildAnnotatedString {
                            append("Lorem ipsum dolor sit amet, consectetur adipiscing elit ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                                append("gibbon moon")
                            }
                            append(" euismod eleifend arcu eget placerat. Donec interdum mauris sit amet mi pharetra faucibus")
                        }
                    )
                }
            }

            item {
                Column(Modifier.padding(12.dp)) {
                    Text(text = "Gibbon Moon", fontWeight= FontWeight.Bold)
                    Text(buildAnnotatedString {
                        append("Aliquam quis ante porttitor, egestas turpis sed, dictum ligula ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append("crescent moon")
                        }
                        append(". Proin ac lectus efficitur, fermentum turpis et, efficitur felis.")
                    })
                }
            }

            item {
                Column(Modifier.padding(12.dp)) {
                    Text(text = "Seeing", fontWeight= FontWeight.Bold)
                    Text(text =
                    "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Integer et euismod justo. Aenean et magna nec enim viverra sollicitudin.")
                }
             }

            item {
                Column(Modifier.padding(12.dp)) {
                    Text(text = "Transparency", fontWeight= FontWeight.Bold)
                    Text(text = "Quisque ligula urna, ultricies et tempus sollicitudin, aliquet at quam. Vivamus tempor magna id sollicitudin lobortis. Donec a odio ut ex venenatis ultricies ut eu mi.")
                }
            }
        }

    }
}