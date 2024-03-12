package com.example.myapplication

import android.os.Build
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import kotlinx.coroutines.delay


import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme


import androidx.compose.material.Text
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface


import androidx.compose.runtime.getValue

import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.scale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()){
                Navigation()
            }
                }
            }


}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController= navController, startDestination = "splash_screen"){
        composable("splash_screen"){
            SplashScreen(navController = navController)
        }
        composable("main"){
            UserBox(navController)
        }
    }
}
@Composable
fun SplashScreen(navController: NavController){
    val scale = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate("main")
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = (R.drawable.ste)),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}

@Composable
fun UserBox(navController: NavController) {
    var text by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Row{
        Spacer(modifier = Modifier.width(60.dp))
        Column {
            Row {
                Spacer(modifier = Modifier.width(40.dp))
                Image(
                    painter = painterResource(R.drawable.ste),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(200.dp)

                )
            }
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = {Text("Username")}
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {Text("Enter password")}
            )
            Button(onClick = {
                navController.navigate("MainMenu"){
                    popUpTo("MainMenu"){
                        inclusive = true
                    }
                }
            }
            ){
                Text(text = "Log In")
            }
            Button(onClick = {

            }) {
                Text(text = "Request permission")
            }

        }
    }



}