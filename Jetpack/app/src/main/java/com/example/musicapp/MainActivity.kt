package com.example.musicapp


import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                val configuration = LocalConfiguration.current

                when (configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        MusicAppHorizontala(0.7f,0.25f,R.drawable.backgroundvertical3)
                    }
                    else -> { // Incluye ORIENTATION_PORTRAIT y el resto
                        MusicAppBertikala(0.7f,R.drawable.backgroundvertical3)
                    }
                }

            }
        }
    }
}
//
//@Composable
//fun MusicApp(atzekoIrudia:Int, bertikala: Boolean){
//    if (bertikala){
//        MusicAppBertikala(0.7f, R.drawable.backgroundvertical3)
//    }else{
//        MusicAppHorizontala(0.7f,0.25f, R.drawable.backgroundvertical3)
//    }
//
//}



@Composable
fun MusicAppBertikala(bolumena:Float, atzekoIrudia: Int) {



    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(atzekoIrudia),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
            ,
            contentScale = ContentScale.Crop
        )

        // Aquí pones el contenido UI que ya tenías
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(20.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
                        ,
                verticalAlignment = Alignment.CenterVertically
//                horizontalArrangement = Arrangement.Center
            ){

                Spacer(modifier = Modifier.weight(0.1f))

                   Portada(Modifier.weight(1f))

//                    Spacer(modifier = Modifier.weight(1f))

//                    Spacer(modifier = Modifier.weight(1f))

                Bolumena(Modifier.weight(0.1f), bolumena )


            }




//                Spacer(modifier = Modifier.height(8.dp))

            Edukiak(Modifier.fillMaxSize())
        }
    }

}



@Composable
fun MusicAppHorizontala(bolumena: Float, denbora: Float, atzekoIrudia: Int){

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
            verticalAlignment = Alignment.CenterVertically
        ) {

            Portada(Modifier.weight(1f))
            Edukiak(Modifier.fillMaxSize())
        }
    }
}

@Preview(
    device = "spec:parent=pixel_8_pro"
)
@Composable
fun MusicAppBertikalaPreview() {
    MusicAppTheme {
        MusicAppBertikala(0.7f,R.drawable.backgroundvertical3)
    }
}

@Preview(
    device = "spec:parent=pixel_8_pro,orientation=landscape"
)
@Composable
fun MusicAppHorizontalaPreview() {
    MusicAppTheme {
        MusicAppHorizontala(0.7f, 0.25f, R.drawable.backgroundhorizontal3)
    }
}

@Composable
fun Bolumena(modifier:Modifier, bolumena: Float){
    var bolumena by remember { mutableStateOf(bolumena) }
    // Volumen vertical + icono
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
//                            .padding(end = 16.dp)
    ) {
        Ikonoa(R.drawable.soinua, Modifier.requiredWidth(72.dp).requiredHeight(56.dp))
//        Image(
//            painter = painterResource(id = R.drawable.soinua),
//            contentDescription = null,
//            modifier = Modifier
//                .requiredWidth(72.dp)
//                .requiredHeight(56.dp)
////                                .size(width = 72.dp, height = 56.dp)
//        )
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
    // Imagen principal
    Image(
        painter = painterResource(id = R.drawable.blacksabbathcover),
        contentDescription = "albumaren portada",
        modifier = modifier
//            .fillMaxSize()
            .width(250.dp)
            .height(250.dp),
//        contentScale = ContentScale.Crop
    )
}

@Composable
fun Ikonoa(baliabideId:Int, modifier: Modifier){
    Image(
        painter = painterResource(id = baliabideId),
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = null,
        modifier = modifier
//            .weight(1f)
            .size(72.dp, 56.dp)
    )
}

@Composable
fun Edukiak(modifier: Modifier){
    // Estados para sliders
    var progress by remember { mutableStateOf(0.25f) }

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

//            Image(
//                painter = painterResource(id = R.drawable.aurrekoa),
//                colorFilter = ColorFilter.tint(Color(255,255,255)),
//                contentDescription = null,
//                modifier = Modifier
//                    .weight(1f)
//                    .size(72.dp, 56.dp)
//            )
            Ikonoa(R.drawable.play, Modifier.weight(1f))
//            Image(
//                painter = painterResource(id = R.drawable.play),
//                colorFilter = ColorFilter.tint(Color(255,255,255)),
//                contentDescription = null,
//                modifier = Modifier
//                    .weight(1f)
//                    .size(72.dp, 56.dp)
//
//            )
            Ikonoa(R.drawable.hurrengoa, Modifier.weight(1f))
//            Image(
//                painter = painterResource(id = R.drawable.hurrengoa),
//                colorFilter = ColorFilter.tint(Color(255,255,255)),
//                contentDescription = null,
//                modifier = Modifier
//                    .weight(1f)
//                    .size(72.dp, 56.dp)
//            )

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Ikonoa(R.drawable.letra, Modifier.weight(1f))
//        Image(
//            painter = painterResource(id = R.drawable.letra),
//            contentDescription = null,
//            modifier = Modifier
//                .weight(1f)
//                .size(72.dp, 56.dp)
//        )
            Ikonoa(R.drawable.lista, Modifier.weight(1f))
//        Image(
//            painter = painterResource(id = R.drawable.lista),
//            contentDescription = null,
//            modifier = Modifier
//                .weight(1f)
//                .size(72.dp, 56.dp)
//        )
            Ikonoa(R.drawable.partekatu, Modifier.weight(1f))
//        Image(
//            painter = painterResource(id = R.drawable.partekatu),
//            contentDescription = null,
//            modifier = Modifier
//                .weight(1f)
//                .size(72.dp, 56.dp)
//        )
        }

    }
//    // Título
//
//
//    Spacer(modifier = Modifier.height(4.dp))
//
//    // Banda
//
//
//    Spacer(modifier = Modifier.height(16.dp))
//
//    // Barra de tiempo y tiempos
//
//
//    Spacer(modifier = Modifier.height(16.dp))
//
//    // Contenedor horizontal para volumen y controles
//
//
//    Spacer(modifier = Modifier.height(24.dp))
//
//    // Iconos inferiores

}