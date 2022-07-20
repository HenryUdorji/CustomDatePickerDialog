package com.hashconcepts.customdatepickerdialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.hashconcepts.customdatepickerdialog.ui.theme.CustomDatePickerDialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomDatePickerDialogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CustomDatePickerDialog(label = "Date of Birth") {

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
                onClick = { /*TODO*/ }) {
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
fun DateSelectionSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        LazyColumn(content = {
            items(getDayList()) { item ->
                DOBItem(item)
            }
        })

        LazyColumn(content = {
            items(fullMonths()) { item ->
                DOBItem(item)
            }
        })

        LazyColumn(content = {
            items(getYearList()) { item ->
                DOBItem(item)
            }
        })
    }
}

@Composable
fun DOBItem(day: String) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.height(40.dp)
    ) {
        Text(text = day, style = MaterialTheme.typography.body1, modifier = Modifier.padding(10.dp))
    }
}


fun getYearList() = (1960..2100).map { it.toString() }
fun getMonthList() = (1..12).map { it.toString() }
fun getDayList() = (1..31).map { it.toString() }

fun fullMonths() = listOf(
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec"
)

@Preview(showBackground = true)
@Composable
fun DatePickerPreview() {
    CustomDatePickerDialogTheme {
        DatePickerUI(label = "Date of Birth") {

        }
    }
}
