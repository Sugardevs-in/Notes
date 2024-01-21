package com.example.notes.legacyCode.screen//package com.example.notes.screen
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.CornerSize
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.Button
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.material3.rememberTopAppBarState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.input.nestedscroll.nestedScroll
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.example.notes.navigation.Screen
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CreateNewNoteScreen(navController: NavController) {
//    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
//    Scaffold (
//        modifier = Modifier
//            .fillMaxSize()
//            .nestedScroll(scrollBehaviour.nestedScrollConnection),
//
//            topBar = {
//                TopAppBar(
//                    scrollBehavior = scrollBehaviour,
//
//                    title = {
//                            Text(text = "Notes")
//                    },
//                    navigationIcon = {
//                        IconButton(onClick = {
//                            navController.navigate(Screen.StatusBar.route)
//                        }
//                        ) {
//                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back" )}
//                    }
//
//                )
//            },
//
//    ){value ->
//        com.example.notes.TextEntry(value)
//
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//
//@Composable()
//fun com.example.notes.TextEntry(value: PaddingValues) {
//
//    Surface(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(value),
//        color = MaterialTheme.colorScheme.background
//    ) {
//
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 24.dp),
//            verticalArrangement = Arrangement.Top,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            item{
//                var titleText by remember {
//                    mutableStateOf("")
//                }
//                var contentText by remember {
//                    mutableStateOf("")
//                }
//
//                OutlinedTextField(
//                    value = titleText,
//                    onValueChange = { titleText = it },
//                    label = {
//                        Text("Enter the title here")
//                    },
//                    modifier = Modifier
//                        .padding(12.dp)
//                        .fillMaxWidth(),
//                    shape = RoundedCornerShape(corner = CornerSize(24.dp)),
//                    keyboardOptions = KeyboardOptions(
//                        imeAction = ImeAction.Next
//                    )
//
//                )
//
//                OutlinedTextField(
//                    value = contentText,
//                    onValueChange = { contentText = it },
//                    label = {
//                        Text("Start Writing here...")
//                    },
//                    modifier = Modifier
//                        .padding(12.dp)
//                        .fillMaxWidth(),
//                    shape = RoundedCornerShape(corner = CornerSize(24.dp))
//                )
//
//                Button(
//                    onClick = { /*TODO*/ },
//                    modifier = Modifier.padding(bottom = 24.dp)
//                ) {
//                    Text("Save")
//                }
//                Text(titleText)
//            }
//            }
//        }
//    }
//
//
