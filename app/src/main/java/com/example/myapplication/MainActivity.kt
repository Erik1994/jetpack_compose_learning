package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MyApp(modifier = Modifier.fillMaxSize())
                // TextCompose(modifier = Modifier)
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    // A surface container using the 'background' color from the theme
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
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onClick
        ) {
            Text("Continue")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ), modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
fun CardContent(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    //val extraPadding = if (expanded) 48.dp else 0.dp
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        /*.fillMaxWidth()*/
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
                //.padding(bottom = extraPadding.coerceAtLeast(0.dp))
        ) {
            Text(text = "Hello")
            Text(
                text = name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
//        ButtonComposable(
//            text = if (expanded) stringResource(R.string.show_less) else stringResource(R.string.show_more)
//        ) {
//            expanded = !expanded
//        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

@Composable
fun ButtonComposable(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier,
        /*colors = ButtonDefaults.buttonColors(containerColor = color),*/
        onClick = onClick
    ) {
        Text(text = text /*color = MaterialTheme.colorScheme.primary*/)
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