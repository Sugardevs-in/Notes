package com.example.notes.legacyCode.screen//package com.example.notes.screen
//
//
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.CornerSize
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.AccountCircle
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.icons.filled.Done
//import androidx.compose.material.icons.filled.Edit
//import androidx.compose.material.icons.filled.KeyboardArrowDown
//import androidx.compose.material.icons.filled.KeyboardArrowUp
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.DropdownMenu
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.IconButtonDefaults
//import androidx.compose.material3.LargeTopAppBar
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.material3.rememberTopAppBarState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.input.nestedscroll.nestedScroll
//import androidx.compose.ui.text.buildAnnotatedString
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.example.notes.navigation.Screen
//import com.example.notes.model.Note
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(navController: NavController) {
//
//    var expanded by remember { mutableStateOf(false) }
//    val scrollBehavior =
//        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
//
//    // This function creates the to bar on the HomeScreen
//    Scaffold(
//        modifier = Modifier
//            .fillMaxSize()
//            .nestedScroll(scrollBehavior.nestedScrollConnection),
//        topBar = {
//            LargeTopAppBar(
//                title = {
//                    Text("Notes")
//                },
//                navigationIcon = {
//                    IconButton(onClick = { /*TODO*/ }) {
//                        Icon(
//                            imageVector = Icons.Default.AccountCircle,
//                            contentDescription = "Account"
//                        )
//                    }
//                },
//
//                actions = {
//                    IconButton(onClick = {
//                        navController.navigate(Screen.ScreenSetup.route)
//                    }) {
//                        Icon(
//                            imageVector = Icons.Default.Add,
//                            contentDescription = "Create a new note",
//
//                            )
//                    }
//                    IconButton(onClick = {
//                        expanded = true
//                    })
//                    {
//                        Icon(
//                            imageVector = Icons.Filled.MoreVert,
//                            contentDescription = "Menu",
//
//                            )
//                    }
//                    DropdownMenu(
//                        expanded = expanded,
//                        onDismissRequest = { expanded = false },
//                        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
//                    ) {
//                        // Delete button in drop down menu
//                        DropdownMenuItem(
//                            modifier = Modifier.padding(8.dp),
//                            text = { Text(text = "Delete") },
//                            onClick = { /*TODO*/ },
//                            leadingIcon = {
//                                IconButton(onClick = { /*TODO*/ }) {
//                                    Icon(
//                                        imageVector = Icons.Default.Delete,
//                                        contentDescription = "Delete Selected Notes"
//                                    )
//                                }
//                            }
//                        )
//                        // Select Button in Drop Down Menu
//                        DropdownMenuItem(
//                            modifier = Modifier.padding(8.dp),
//                            text = { Text(text = "Select") },
//                            onClick = { /*TODO*/ },
//                            leadingIcon = {
//                                IconButton(onClick = { /*TODO*/ }) {
//                                    Icon(
//                                        imageVector = Icons.Default.Done,
//                                        contentDescription = "Select"
//                                    )
//                                }
//                            }
//                        )
//                    }
//                },
//                scrollBehavior = scrollBehavior
//            )
//        }
//    )
//    {
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(it)
//        ) {
//
//            item { TextCardScroll(innerPadding = PaddingValues(0.dp), note = Note()) }
//
//        }
//
//
//    }
//}
//
//// This function creates cards on the HomeScreen to display the notes added.
//@Composable
//fun TextCardScroll(
//    note: Note,
//    innerPadding: PaddingValues = PaddingValues(),
//
//    icon1: ImageVector = Icons.Default.Edit,
//) {
//    var expanded by remember { mutableStateOf(false) }
//
//
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(innerPadding)
//    ) {
//        Card(
//            modifier = Modifier.padding(8.dp)
//                .clickable(onClick = { expanded = !expanded}),
//            shape = RoundedCornerShape(corner = CornerSize(12.dp)),
//            colors = CardDefaults.cardColors(
//                containerColor = MaterialTheme.colorScheme.surface
//            ),
//
//            elevation = CardDefaults.cardElevation(6.dp)
//        ) {
//
//
//            Row(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = buildAnnotatedString {
//                            append(note.title)
//                    },
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .fillMaxWidth(.8f),
//                    style = MaterialTheme.typography.titleLarge,
//                    maxLines = if (expanded) Int.MAX_VALUE else 2,
//                    overflow = TextOverflow.Ellipsis
//                )
//
//                Row(
//                    horizontalArrangement = Arrangement.End
//                ) {
//                    IconButton(
//                        onClick = { },
//                        colors = IconButtonDefaults.iconButtonColors(
//                            contentColor = MaterialTheme.colorScheme.primary
//                        )
//                    ) {
//                        Icon(imageVector = icon1, contentDescription = null)
//                    }
//
//                    IconButton(
//                        onClick = { expanded = !expanded },
//                        colors = IconButtonDefaults.iconButtonColors(
//                            contentColor = MaterialTheme.colorScheme.primary
//                        )
//                    ) {
//                        Icon(
//                            imageVector = if (expanded) {
//                                Icons.Default.KeyboardArrowUp
//                            } else {
//                                Icons.Default.KeyboardArrowDown
//                            },
//                            contentDescription = null
//                        )
//                    }
//                }
//            }
//
//
//            if (expanded) {
//
//                Text(
//                    text = note.description,
//                    modifier = Modifier
//                        .padding(8.dp),
//                    style = MaterialTheme.typography.bodyLarge,
//                    maxLines = Int.MAX_VALUE,
//                    overflow = TextOverflow.Ellipsis,
//                )
//
//            }
//        }
//    }
//}
//
//
//
//
//
//
