//
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.rounded.Check
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.FloatingActionButton
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.example.notes.model.NoteState
//import com.example.notes.model.NotesEvent
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddNoteScreen(
//    state: NoteState,
//    navController: NavController,
//    onEvent: (NotesEvent) -> Unit
//) {
//
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(onClick = {
//
//                onEvent(
//                    NotesEvent.SaveNote(
//                    title = state.title.value,
//                    description = state.description.value
//                ))
//                navController.popBackStack()
//            }) {
//
//                Icon(
//                    imageVector = Icons.Rounded.Check,
//                    contentDescription = "Save Note"
//                )
//
//            }
//        }
//    ) { paddingValues ->
//
//        Column(
//            modifier = Modifier
//                .padding(paddingValues)
//                .fillMaxSize()
//        ) {
//
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                value = state.title.value,
//                onValueChange = {
//                    state.title.value = it
//                },
//                textStyle = TextStyle(
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 17.sp
//                ),
//                placeholder = {
//                    Text(text = "Title")
//                }
//
//            )
//
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                value = state.description.value,
//                onValueChange = {
//                    state.description.value = it
//                },
//                placeholder = {
//                    Text(text = "Description")
//                }
//
//            )
//
//        }
//
//    }
//
//}


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notes.model.NoteState
import com.example.notes.model.NotesEvent
import com.example.notes.legacyCode.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NotesEvent) -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehaviour.nestedScrollConnection),

        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehaviour,

                title = {
                    Text(text = "Notes")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back",
                            )
                    }
                }

            )
        },

        ) { value ->
        AddNotesScreen(
            state = state,
            navController = navController,
            onEvent = onEvent,
            paddingValues = value
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AddNotesScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NotesEvent) -> Unit,
    paddingValues: PaddingValues
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        color = MaterialTheme.colorScheme.background
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {

                OutlinedTextField(
                    value = state.title.value,
                    onValueChange = { state.title.value = it },
                    label = {
                        Text("Enter the title here")
                    },
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(corner = CornerSize(24.dp)),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )

                )

                OutlinedTextField(
                    value = state.description.value,
                    onValueChange = { state.description.value = it },
                    label = {
                        Text("Start Writing here...")
                    },
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(corner = CornerSize(24.dp))
                )

                Button(
                    onClick = {
                        onEvent(
                            NotesEvent.SaveNote(
                                title = state.title.value,
                                description = state.description.value
                            )
                        )
                        navController.popBackStack()
                    },
                    modifier = Modifier.padding(bottom = 24.dp)
                ) {
                    Text("Save")
                }

            }
        }
    }
}


