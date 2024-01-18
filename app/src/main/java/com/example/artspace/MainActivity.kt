package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}


@Composable
fun ArtSpaceApp(modifier: Modifier=Modifier){
    var iteration by remember { mutableStateOf(1) }
    var curImg:Int=when(iteration){
        1->R.drawable.img1
        2->R.drawable.img2
        3->R.drawable.img3
        4->R.drawable.img4
        else -> {R.drawable.img5}
    }
    var curArtWorkArtist:Int=when(iteration){
        1->R.string.img1_artwork_artist
        2->R.string.img2_artwork_artist
        3->R.string.img3_artwork_artist
        4->R.string.img4_artwork_artist
        else -> {R.string.img5_artwork_artist}
    }
    var curArtWorkYear:Int=when(iteration){
        1->R.string.img1_artwork_year
        2->R.string.img2_artwork_year
        3->R.string.img3_artwork_year
        4->R.string.img4_artwork_year
        else -> {R.string.img5_artwork_year}
    }
    var curArtWorkTitle:Int=when(iteration){
        1->R.string.img1_artwork_title
        2->R.string.img2_artwork_title
        3->R.string.img3_artwork_title
        4->R.string.img4_artwork_title
        else -> {R.string.img5_artwork_title}
    }

    Column(
        modifier= modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtImages(imagePainter = curImg)
        Spacer(modifier = Modifier.height(60.dp))
        ArtDescription(
            artWorkTitle = curArtWorkTitle,
            artistName = curArtWorkArtist,
            year = curArtWorkYear
        )
        Spacer(modifier = Modifier.height(22.dp))
//        Text(text = "$iteration")
        DisplayController(
            handleNext = {
                if(iteration==5){
                    iteration=1
                }
                else iteration++
             },
            handlePrevious={
                if(iteration==1){
                    iteration=5
                }
                else iteration--
            }
        )
    }
}

@Composable
fun DisplayController(
    handleNext:()->Unit,
    handlePrevious:()->Unit,
    modifier: Modifier=Modifier
){
    Row(
        modifier=modifier
            .width(IntrinsicSize.Min),
//            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick =handlePrevious ,
            modifier=Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 73, green = 93, blue = 146)
            )
        ) {
            Text(text = "Previous",modifier=Modifier.padding(start = 16.dp,end=16.dp))
        }
        Spacer(modifier = Modifier.width(80.dp))
        Button(
            onClick =  handleNext,
            modifier=Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 73, green = 93, blue = 146)
            )
        )
        {
            Text(text = "Next",modifier=Modifier.padding(start = 16.dp,end=16.dp))
        }
    }
}

@Composable
fun ArtDescription(artWorkTitle:Int,artistName:Int,year:Int,modifier: Modifier=Modifier){
    Column(
        modifier= modifier
            .padding(20.dp)
            .background(Color(0xFFecebf4))
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = artWorkTitle),
            fontWeight = FontWeight.Light,
            fontSize = 24.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier=Modifier.padding(top=18.dp, start = 12.dp,end=12.dp)
        )
        Row(modifier=modifier.padding(bottom=18.dp, start = 12.dp)) {
            Text(text = stringResource(id = artistName), fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text= stringResource(id = year), fontWeight = FontWeight.ExtraLight)
        }
    }
}

@Composable
fun ArtImages(imagePainter: Int,modifier: Modifier=Modifier){
        Image(
            painter = painterResource(id =imagePainter),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier= modifier
                .padding(start = 16.dp, end = 16.dp, top = 80.dp)
                .width(350.dp)
                .height(400.dp)
                .border(32.dp, Color.White, RectangleShape)
                .shadow(12.dp, RectangleShape)
        )
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }

}