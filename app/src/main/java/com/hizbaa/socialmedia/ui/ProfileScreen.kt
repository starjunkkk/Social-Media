package com.hizbaa.socialmedia.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hizbaa.socialmedia.ui.theme.SocialMediaTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var tempname by remember { mutableStateOf(uiState.name) }
    var tempage by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Profile",
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        TextField(
            value = tempname,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            onValueChange = { tempname = it },
            label = { Text("Your name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    viewModel.updateName(tempname)
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        TextField(
            value = tempage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            onValueChange = { input ->
                if( input.all { it.isDigit() }){
                    tempage = input
                }
            },
            label = { Text("Your age") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    tempage.toIntOrNull()?.let{
                        viewModel.updateAge(it)
                    }
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(uiState.name)
        Text(uiState.age.toString())
    }

}

@Preview
@Composable
fun ProfileScreenPreview() {
    SocialMediaTheme {
        ProfileScreen()
    }
}