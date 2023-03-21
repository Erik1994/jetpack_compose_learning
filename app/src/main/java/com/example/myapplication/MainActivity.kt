package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//
//                }

                TextCompose(modifier = Modifier)
            }
        }
    }
}

@Composable
fun TextCompose(modifier: Modifier) {
    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxSize()
            .padding(top = 15.dp)
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