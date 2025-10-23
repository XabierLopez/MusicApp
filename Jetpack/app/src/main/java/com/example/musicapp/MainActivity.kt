package com.example.musicapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.ui.theme.MusicAppTheme

const val hasierakoBolumena=0.7f
const val hasierakoDenbora=0.25f
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                val configuration = LocalConfiguration.current
                when (configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        MusicAppHorizontala(R.drawable.backgroundvertical3)
                    }
                    else -> {
                        MusicAppBertikala(R.drawable.backgroundvertical3)
                    }
                }
            }
        }
    }
}
@Composable
fun MusicAppBertikala(atzekoIrudia: Int) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(atzekoIrudia),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
            ,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(20.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.weight(0.1f))
                Portada(Modifier.weight(1f))
                Bolumena(Modifier.weight(0.1f) )
            }
            Edukiak(Modifier.fillMaxSize())
        }
    }
}
@Composable
fun MusicAppHorizontala(atzekoIrudia: Int){
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(atzekoIrudia),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
                .padding(20.dp)
        ) {
            Portada(Modifier.weight(1f).fillMaxHeight())
            Edukiak(Modifier.weight(0.75f).fillMaxHeight())
            Bolumena(Modifier.weight(0.25f))
        }
    }
}
@Preview(
    device = "spec:parent=pixel_8_pro"
)
@Composable
fun MusicAppBertikalaPreview() {
    MusicAppTheme {
        MusicAppBertikala(R.drawable.backgroundvertical3)
    }
}
@Preview(
    device = "spec:parent=pixel_8_pro,orientation=landscape"
)
@Composable
fun MusicAppHorizontalaPreview() {
    MusicAppTheme {
        MusicAppHorizontala(R.drawable.backgroundhorizontal3)
    }
}
@Composable
fun Bolumena(modifier:Modifier){
    var bolumena by remember { mutableStateOf(hasierakoBolumena) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        Ikonoa(R.drawable.soinua, Modifier.requiredWidth(72.dp).requiredHeight(56.dp))
        Box(
            modifier = Modifier
                .height(210.dp)
                .width(10.dp)
        ) {
            Slider(
                value = bolumena,
                onValueChange = { bolumena=it},
                modifier = Modifier
                    .fillMaxSize()
                    .requiredWidth(200.dp)
                    .rotate(-90f)
            )
        }
    }
}
@Composable
fun Portada(modifier: Modifier){
    Image(
        painter = painterResource(id = R.drawable.blacksabbathcover),
        contentDescription = "albumaren portada",
        modifier = modifier
            .width(250.dp)
            .height(250.dp),
    )
}
@Composable
fun Ikonoa(baliabideId:Int, modifier: Modifier){
    Image(
        painter = painterResource(id = baliabideId),
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = null,
        modifier = modifier
            .size(72.dp, 56.dp)
    )
}
@Composable
fun IkonoAldakorra(baliabideId1: Int, baliabideId2: Int){
    var isPlaying by remember { mutableStateOf(false) }
    val baliabideId = if (isPlaying) baliabideId1 else baliabideId2
    Ikonoa(baliabideId, Modifier.clickable{isPlaying=!isPlaying})
}
@Composable
fun Edukiak(modifier: Modifier){
    var progress by remember { mutableStateOf(hasierakoDenbora) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier=modifier
    ) {
        Text(
            text = "Heaven and Hell",
            fontSize = 36.sp,
            color = Color.White,
            fontFamily = FontFamily.SansSerif
        )
        Text(
            text = "Black Sabbath",
            fontSize = 24.sp,
            color = Color.White,
            fontFamily = FontFamily.SansSerif
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "0:25",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(end = 8.dp)
            )
            Slider(
                value = progress,
                onValueChange = { progress = it },
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "1:15",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Ikonoa(R.drawable.aurrekoa, Modifier.weight(1f))
            IkonoAldakorra(R.drawable.pause,R.drawable.play)
            Ikonoa(R.drawable.hurrengoa, Modifier.weight(1f))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Ikonoa(R.drawable.letra, Modifier.weight(1f))
            Ikonoa(R.drawable.lista, Modifier.weight(1f))
            Ikonoa(R.drawable.partekatu, Modifier.weight(1f))
        }
    }
}