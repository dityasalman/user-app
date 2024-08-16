package com.ditya.contactapi.ui.theme.mainscreen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ditya.contactapi.data.network.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    
    Scaffold(
        floatingActionButton = {
            Button(onClick = { viewModel.onAddClick() }) {
                Text(text = "Add")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
        ){
            itemsIndexed(items = viewModel.usersResponse) {index, item ->
                Row {
                    UserItems(
                        user = item,
                        onClick = {
                            viewModel.deleteUser(it)
                            viewModel.getUserList()
                        })
                }
            }
        }
        if (viewModel.isAddingUser){
            AddNewUserDialog(
                onDismiss = { viewModel.onDismissClick() },
                onConfirm = {
                    viewModel.postUser(it)
                    viewModel.getUserList()
                    viewModel.onDismissClick()
                })
        }
    }
   


}

@Composable
fun UserItems(
    user: User,
    onClick: (id: Long)->Unit ) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column( modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth(0.75f)
            .height(100.dp)
        ) {
            Row {
                Text(text = user.firstName)
                Text(text = " ")
                Text(text = user.lastName)
            }
            Text(text = user.phone)
        }
        Button(onClick = { user.id?.let { onClick(it) } }) {
            Text(text = "dell")
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewUserDialog(
    onDismiss: () -> Unit,
    onConfirm: (User) -> Unit,
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismiss()}) {
        Column(
            modifier = Modifier
                .padding(16.dp, 8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            TextField(
                value = firstName,
                label = { Text(text = "First Name")},
                onValueChange = {
                    firstName = it
                })

            TextField(
                value = lastName,
                label = { Text(text = "Last Name")},
                onValueChange = {
                    lastName = it
                })

            TextField(
                value = phone,
                label = { Text(text = "Phone")},
                onValueChange = {
                    phone = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            Button(onClick = {
                onConfirm(User(
                    firstName = firstName,
                    lastName = lastName,
                    phone = phone
                ))
            }) {
                Text(text = "Save")
            }
        }
    }
}

@Preview
@Composable
fun UserItemPreview(){
    UserItems(
        user = User(
        firstName = "Joko",
        lastName = "Waluyo",
        phone = "3984"),
        onClick = {}
    )
}