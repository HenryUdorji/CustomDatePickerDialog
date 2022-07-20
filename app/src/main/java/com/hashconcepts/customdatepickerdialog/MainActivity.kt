package com.hashconcepts.customdatepickerdialog

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.hashconcepts.customdatepickerdialog.ui.theme.CustomDatePickerDialogTheme
import com.hashconcepts.customdatepickerdialog.ui.theme.spinnerGradient
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomDatePickerDialogTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {

                    var showDialog by remember { mutableStateOf(false) }
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center),
                        onClick = {
                            showDialog = true
                        }) {
                        Text(text = "Date Picker", style = MaterialTheme.typography.body1)
                    }

                    if (showDialog) {
                        CustomDatePickerDialog(label = "Date Picker") {
                            showDialog = false
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomDatePickerDialog(
    label: String,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        DatePickerUI(label, onDismissRequest)
    }
}

@Composable
fun DatePickerUI(
    label: String,
    onDismissRequest: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        backgroundColor = Color.White,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.h1,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            DateSelectionSection()

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                onClick = { onDismissRequest() }
            ) {
                Text(
                    text = "Done",
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun DateSelectionSection(
    onYearChosen
) {
    val chosenYear = remember { mutableStateOf(currentYear) }
    val chosenMonth = remember { mutableStateOf(currentMonth) }
    val chosenDay = remember { mutableStateOf(currentDay) }


    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        InfiniteItemsPicker(
            items = days,
            firstIndex = Int.MAX_VALUE / 2 + (currentDay - 2),
            onItemSelected = { chosenDay.value = it.toInt() }
        )

        InfiniteItemsPicker(
            items = monthsNames,
            firstIndex = Int.MAX_VALUE / 2 - 4 + currentMonth,
            onItemSelected = { chosenMonth.value = monthsNames.indexOf(it) }
        )

        InfiniteItemsPicker(
            items = years,
            firstIndex = Int.MAX_VALUE / 2 + (currentYear - 1967),
            onItemSelected = { chosenYear.value = it.toInt() }
        )
    }
}

@Composable
fun InfiniteItemsPicker(
    modifier: Modifier = Modifier,
    items: List<String>,
    firstIndex: Int,
    onItemSelected: (String) -> Unit,
) {

    val listState = rememberLazyListState(firstIndex)
    val currentValue = remember { mutableStateOf("") }

    LaunchedEffect(key1 = !listState.isScrollInProgress) {
        onItemSelected(currentValue.value)
        listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
    }

    Box(modifier = Modifier.height(106.dp)) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState,
            content = {
                items(count = Int.MAX_VALUE, itemContent = {
                    val index = it % items.size
                    if (it == listState.firstVisibleItemIndex + 1) {
                        currentValue.value = items[index]
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = items[index],
                        modifier = Modifier.alpha(if (it == listState.firstVisibleItemIndex + 1) 1f else 0.3f),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                })
            }
        )
    }
}

val currentYear = Calendar.getInstance().get(Calendar.YEAR)
val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
val currentMonth = Calendar.getInstance().get(Calendar.MONTH)

val years = (1950..2050).map { it.toString() }
val monthsNumber = (1..12).map { it.toString() }
val days = (1..31).map { it.toString() }
val monthsNames = listOf(
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec"
)



