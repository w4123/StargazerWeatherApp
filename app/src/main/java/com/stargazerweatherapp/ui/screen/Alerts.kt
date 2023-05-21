package com.stargazerweatherapp.ui.screen

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.isContainer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.stargazerweatherapp.data.models.DateGetter
import com.stargazerweatherapp.data.repository.LocationRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AlertPage(
    navigateBack: () -> Unit = {},
    navigateToAlertsPage: () -> Unit = {},
    date: LocalDate = LocalDate.now(),
    snackBar: Boolean = false
) {
    val ThingsToBeNotifiedOf = remember {
        listOf(
            "Conditions Heavily Unfavourable to Stargazing",
            "Rain",
            "Mild Cold",
            "Freezing Temperatures",
            "Low Visibility"
        )
    }
    //if (snackBar) {
    //    rememberCoroutineScope().launch {
    //        SnackbarHostState().showSnackbar("Saved")
    //    }
    //}
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Set Alert")
            },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                })
        }
    ) {
        LazyColumn(Modifier.padding(it)) {
            item {
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = "Select A Date:",
                    fontSize = 16.sp
                )
            }
            item {
                DateSelector(date)
            }
            item {
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    text = "Select What You Want to be notified of:",
                    fontSize = 16.sp
                )
            }
            items(ThingsToBeNotifiedOf.size) { index ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    var thisChecked by remember {
                        mutableStateOf(index == 0)
                    }
                    Checkbox(
                        checked = thisChecked,
                        onCheckedChange = { thisChecked = it }
                    )
                    Text(
                        text = ThingsToBeNotifiedOf[index],
                        fontSize = 14.sp
                    )
                }
            }
            item {
                val context = LocalContext.current;
                Button(onClick = {
                        Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
                        navigateBack()
                    }
                    , modifier = Modifier.padding(horizontal = 8.dp)) {
                    Text("Set Alert")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelector(
    date: LocalDate
) {
    var text by rememberSaveable {mutableStateOf(date.toString())}
    var active by rememberSaveable { mutableStateOf(false) }
    val dates = DateGetter.getDates()
    val datesStrings = dates.map {
        it.toString()
    }
    Box(
        Modifier
            .semantics { isContainer = true }
            .zIndex(1f)
            .padding(6.dp)
            .fillMaxWidth()) {
        Row(modifier = Modifier) {
            DockedSearchBar(
                modifier = Modifier.padding(horizontal = 3.dp),
                query = text,
                onQueryChange = { text = it },
                onSearch = { active = false },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = { Text("Select Date") },
                leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val possibleDates = datesStrings.filter {
                        it.startsWith(text, true)
                    }
                    items(possibleDates.size) { idx ->
                        val resultText = possibleDates[idx]
                        ListItem(
                            headlineContent = { Text(resultText) },
                            modifier = Modifier.clickable {
                                text = resultText
                                active = false
                            }
                        )
                    }
                }
            }
        }
    }
}