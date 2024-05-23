package ru.stroymig.workconnect.screens


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.stroymig.workconnect.R
import ru.stroymig.workconnect.ui.theme.PurpleGrey40
import ru.stroymig.workconnect.data.NavigationItem
import ru.stroymig.workconnect.ui.theme.Back04
import ru.stroymig.workconnect.ui.theme.Primary
import ru.stroymig.workconnect.ui.theme.Primary02
import ru.stroymig.workconnect.ui.theme.Primary03
import ru.stroymig.workconnect.ui.theme.Secondary

@Composable
fun NormalTextComponent(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.white),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.white),
        textAlign = TextAlign.Center
    )
}


@Composable
fun MyTextField(labelValue: String, onTextSelected: (String) -> Unit,
                errorStatus: Boolean = false){

    val textValue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .clip(shape = RoundedCornerShape(4.dp)),

        label = {Text(text = labelValue)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary02,
            focusedLabelColor = Primary02,
            cursorColor = Primary02,
            backgroundColor = colorResource(id = R.color.bg_color)
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },

        isError = !errorStatus

    )
}

@Composable
fun PasswordTextField(labelValue: String, onTextSelected: (String) -> Unit,
                      errorStatus: Boolean = false){

    val localFocusManager = LocalFocusManager.current

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember{
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .clip(shape = RoundedCornerShape(4.dp)),

        label = {Text(text = labelValue)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary02,
            focusedLabelColor = Primary02,
            cursorColor = Primary02,
            backgroundColor = colorResource(id = R.color.bg_color)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine = true,
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },

        trailingIcon = {
            val iconImage = if (passwordVisible.value){
                Icons.Filled.Visibility
            } else{
                Icons.Filled.VisibilityOff
            }

            val description = if(passwordVisible.value){
                stringResource(id = R.string.hide_password)
            }else{
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}


//@Composable
//fun CheckboxComponent(value: String){
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .heightIn(56.dp),
//        verticalAlignment = Alignment.CenterVertically,
//
//    ){
//
//        val checkedState = remember {
//            mutableStateOf(false)
//        }
//
//        Checkbox(checked = checkedState.value, onCheckedChange = {
//            checkedState.value = !checkedState.value
//        })
//        ClickableTextComponent(value = value)
//    }
//}



@Composable
fun ButtonComponent(value: String, onButtonClicked : () -> Unit, isEnabled :Boolean = false){
    Button(
        onClick = {
            onButtonClicked.invoke()
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .padding(start = 100.dp, end = 100.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Primary02),
        enabled = isEnabled
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    color = Secondary,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.white)
            )
        }
    }
}


@Composable
fun DividerTextComponent(){
    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Divider(modifier = Modifier
            .padding(start = 25.dp)
            .weight(1f),
        color = Color.Gray,
        thickness = 1.dp
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.or),
            fontSize = 14.sp,
            color = Color.Gray
        )
        Divider(modifier = Modifier
            .padding(end = 25.dp)
            .weight(1f),
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin:Boolean = true, onTextSelected : (String) -> Unit){

    val initialText = if (tryingToLogin)"Уже есть аккаунт? " else "Хотите зарегистрироваться? "
    val loginText = if (tryingToLogin) "Войти" else "Регистрация"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Color(0xFFFFFFFF))){
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also {span ->
                Log.d("ClickableText", "{$span")

                if (span.item == loginText) {
                    onTextSelected (span.item)
                }
            }
    })
}


//@Composable
//fun UnderLinedTextComponent(value:String){
//    Text(
//        text = value,
//        modifier = Modifier
//            .fillMaxWidth()
//            .heightIn(min = 40.dp),
//        style = TextStyle(
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Normal,
//            fontStyle = FontStyle.Normal
//        ),
//        color = Color.Gray,
//        textAlign = TextAlign.Center,
//        textDecoration = TextDecoration.Underline
//    )
//}


@Composable
fun AppToolbar(toolbarTitle :String, isSearchVisible: Boolean, searchText: String,
               onSearchTextChanged: (String) -> Unit, onSearchClicked: () -> Unit,
               navigationIconClicked :() -> Unit){
    TopAppBar (
        backgroundColor = Primary,
        title = {

            if (isSearchVisible){
                Box{}
                TextField(
                    value = searchText,
                    onValueChange = onSearchTextChanged,
                    modifier = Modifier
                        .height(35.dp)
                        .padding(start = 25.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, Primary02, shape = RoundedCornerShape(8.dp))
                        .background(Color(0xFFBBDEFB)),
                    placeholder = { Text(text = "Search") },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        backgroundColor = Primary03,
                        cursorColor = Color.White
                    ),
                )
            } else {
                Text(
                    text = toolbarTitle,
                    color = Color.White
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                navigationIconClicked.invoke()
            })
            {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = stringResource(R.string.menu),
                    tint = Color.White
                )
            }
        },
        actions = {

            IconButton(
                onClick = {
                    onSearchClicked()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    tint = Color.White,
                    contentDescription = stringResource(id = R.string.search)
                )
            }

        }
    )
}

@Composable
fun NavigationDrawerHeader(value: String?) {
    Box (
        modifier = Modifier
            .background(Primary)
            .fillMaxWidth()
            .height(180.dp)
            .padding(32.dp)
    ){
        NavigationDrawerText(title = value?: stringResource(R.string.myapp), 14.sp )
    }
}

@Composable
fun NavigationDrawerBody(navigationDrawerItems : List<NavigationItem>,
                         onNavigationItemClicked: (NavigationItem) -> Unit){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()

    ){
        items(navigationDrawerItems){
            NavigationItemRow(item = it, onNavigationItemClicked)
        }
    }
}

@Composable
fun NavigationItemRow(item : NavigationItem, onNavigationItemClicked :(NavigationItem) -> Unit){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNavigationItemClicked.invoke(item)
            }
            .padding(all = 16.dp)
    ){
        Icon(
            imageVector = item.icon,
            contentDescription = item.description,
            tint = Back04
        )
        Spacer(modifier = Modifier.width(18.dp))
        NavigationDrawerText(title = item.title, 18.sp)

    }
}

@Composable
fun NavigationDrawerText(title: String, textUnit: TextUnit){

    val shadowOffset = Offset(4f, 4f)

    Text(
        text = title,
        style = TextStyle(
            color = Color.White,
            fontSize = textUnit,
            fontStyle = FontStyle.Normal,
            shadow = Shadow(
                color = PurpleGrey40,
                offset = shadowOffset, 2f
            )

        )
    )
}

