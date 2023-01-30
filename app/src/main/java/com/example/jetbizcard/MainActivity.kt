package com.example.jetbizcard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetbizcard.ui.theme.JetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard()
{
    //speacial variable that remembers the state
    var buttonClickedState= remember {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(12.dp)) {

        Card(   modifier = Modifier
            .width(200.dp)
            .height(390.dp),
                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                backgroundColor = Color.Gray,
                elevation = 6.dp) {

            Column(modifier = Modifier.height(300.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally) {


                CreateImageProfile()
                Divider(color = Color.Black, thickness = 2.dp)
                CreateInfo()
                Button(
                    onClick =
                    {
                        //debug log
                    //    Log.d("Clicked!", "CreateBizCard: Clicked!")
                        buttonClickedState.value = !buttonClickedState.value

                    }

                ) {
                    Text(text = "Portfolio",
                    style = MaterialTheme.typography.button )
                }
                if (buttonClickedState.value)
                {
                    Content()
                }
                else
                {
                    Box() {
                        
                    }
                }

            }



        }

    }
}

@Composable
fun Content()
{
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)){
        
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {

            Portfolio(data = listOf("Project 1","Project 2", "Project 3"))

        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    //scroll + recycle views as it is being displayed
    LazyColumn{
        items(data){item ->
            Card(modifier = Modifier
                .padding(14.dp)
                .fillMaxHeight(),
                shape = RectangleShape,
                elevation = 7.dp){

                Row(modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface)
                    .padding(16.dp)){

                    CreateImageProfile(modifier = Modifier.size(20.dp))

                    Column(modifier = Modifier
                        .padding(7.dp)
                        .align(alignment = Alignment.CenterVertically)) {

                        Text(text=item, fontWeight = FontWeight.Bold)
                        Text(text="A great Project", style = MaterialTheme.typography.body2)


                    }


                    }
                }
            }
        }
    }


@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Mansimar B.",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )

        Text(
            text = "Android Developer",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1
        )


        Text(
            text = "@mansimars",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.Black),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {

        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "Profile Image",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetBizCardTheme {
        CreateBizCard()
    }
}