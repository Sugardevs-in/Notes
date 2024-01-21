package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.notes.navigation.Navigation

import com.example.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            NotesTheme {

                Surface {
                 Display()
                }

            }
        }
    }
}
@Composable
fun Display(){
   Navigation()
}

@Preview(showBackground = true)
@Composable
fun Show() {
    NotesTheme {
        Display()
    }
}