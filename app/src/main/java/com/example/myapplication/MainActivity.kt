package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MyApp(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.onBackground)
                )
                // TextCompose(modifier = Modifier)
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    // A surface container using the 'background' color from the theme
//    Surface(
//        modifier = modifier,
//        color = MaterialTheme.colorScheme.primary
//    ) {
//
//    }

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(modifier = modifier) {
                shouldShowOnboarding = false
            }
        } else {
            Greetings(modifier = modifier)
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(
        modifier = modifier
            .padding(vertical = 4.dp)
    ) {
        items(items = names) {
            Greeting(name = it)
        }
    }
}

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!", color = MaterialTheme.colorScheme.background)
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onClick
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding = if (expanded) 48.dp else 0.dp

    Surface(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(modifier = Modifier.padding(24.dp)/*.fillMaxWidth()*/) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello")
                Text(text = name)
            }
            ButtonComposable(
                text = if (expanded) "Show Less" else "Show More",
                color = MaterialTheme.colorScheme.onBackground
            ) {
                expanded = !expanded
            }
        }
    }
}

@Composable
fun ButtonComposable(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@Composable
fun TextCompose(modifier: Modifier) {
    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxSize()
            .border(width = 5.dp, color = Color.Black)
            .padding(5.dp)
            .border(width = 5.dp, color = Color.White)
            .padding(5.dp)
            .border(width = 5.dp, color = Color.Red)
    ) {
        Text(text = "Hello", modifier = Modifier.offset(50.dp, 20.dp))
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Compose")
        Text(text = "Compose")
    }

//    Row(
//        modifier = Modifier
//            .fillMaxSize(0.5f)
//            .background(Color.Green),
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(text = "Hello")
//        Text(text = "Compose")
//        Text(text = "Compose")
//    }
}