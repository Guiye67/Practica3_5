package baeza.guillermo.practica3_5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import baeza.guillermo.practica3_5.ui.theme.Practica3_5Theme
import androidx.compose.ui.graphics.Color
import baeza.guillermo.practica3_5.ui.theme.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.Role

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var myName by remember{ mutableStateOf("") }
            var mySurname by remember{ mutableStateOf("") }
            var myEmail by remember{ mutableStateOf("") }

            var myContacts by rememberSaveable{ mutableStateOf(false) }
            var myVisible by rememberSaveable{ mutableStateOf(false) }
            var myTelefono by rememberSaveable{ mutableStateOf(false) }

            var myPhone by remember{ mutableStateOf("") }

            var myDeporte by rememberSaveable { mutableStateOf(false) }
            var myMuseo by rememberSaveable { mutableStateOf(false) }
            var myCine by rememberSaveable { mutableStateOf(false) }
            var myVideojuego by rememberSaveable { mutableStateOf(false) }
            var myViaje by rememberSaveable { mutableStateOf(false) }
            var myAnime by rememberSaveable { mutableStateOf(false) }

            var mostrarContenido by remember{ mutableStateOf(false) }

            var contador: Int
            Practica3_5Theme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Registro",
                        textAlign = TextAlign.Center,
                        fontSize = 64.sp,
                        color = darkRed,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Separador()
                    MyTextField(myName, "Nombre"){myName = it}
                    Separador()
                    MyTextField(mySurname, "Apellidos"){mySurname = it}
                    Separador()
                    MyTextField(myEmail, "Email"){myEmail = it}
                    Separador()
                    Row(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(text = "Añadir texto")
                        SeparadorH()
                        Text(text = "Visible")
                        SeparadorH()
                        Text(text = "Teléfono")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        MySwitch(myContacts){myContacts = it}
                        SeparadorH()
                        MySwitch(myVisible){myVisible = it}
                        SeparadorH()
                        MySwitch(myTelefono){myTelefono = it}
                    }
                    Separador()
                    TextField(
                        value = myPhone,
                        onValueChange = {myPhone = it},
                        label = {Text(text = "Teléfono")},
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .background(lightRed)
                    )
                    Separador()
                    Text(
                        text = "Intereses",
                        textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Row(modifier = Modifier.fillMaxWidth(0.8f)){
                        Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                            MyCheckBox("Deporte", state = myDeporte){myDeporte = it}
                            MyCheckBox("Cine", state = myCine){myCine = it}
                            MyCheckBox("Viajes", state = myViaje){myViaje = it}
                        }
                        Column {
                            MyCheckBox("Museos", state = myMuseo){myMuseo = it}
                            MyCheckBox("Videojuegos", state = myVideojuego){myVideojuego = it}
                            MyCheckBox("Anime", state = myAnime){myAnime = it}
                        }
                    }
                    Text(text ="Estudios",
                        textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Separador()
                    MultipleRadioButtons()
                    Separador()
                    Button(
                        onClick = { mostrarContenido = !mostrarContenido },
                        colors = ButtonDefaults.buttonColors(backgroundColor = strongOrange),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Enviar",
                            color = Color.White
                        )
                    }

                    if(mostrarContenido){
                        LinearProgressIndicator(
                            modifier = Modifier.padding(top = 2.dp),
                            color = Color.Red,
                            backgroundColor = Color.Green
                        )
                        contador = 0
                        if(myDeporte)
                            contador++
                        if(myMuseo)
                            contador++
                        if(myCine)
                            contador++
                        if(myVideojuego)
                            contador++
                        if(myViaje)
                            contador++
                        if(myAnime)
                            contador++

                        val texto = "Ha seleccionado $contador interes"
                        if(contador == 1)
                            Text(texto)
                        else
                            Text(texto + "es")
                    }
                }
            }
        }
    }
}

@Composable
fun Separador() {
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun MyTextField(name: String,texto : String, onValueChanged: (String) -> Unit){
    TextField(
        value = name,
        onValueChange = {onValueChanged(it)},
        label = {Text(text = texto)},
        modifier = Modifier.fillMaxWidth(0.8f)
    )
}

@Composable
fun MySwitch(state: Boolean, onCheckedChange: (Boolean) -> Unit){
    Switch(
        checked = state,
        onCheckedChange = { onCheckedChange(!state) }
    )
}

@Composable
fun SeparadorH(){
    Spacer(modifier = Modifier.width(50.dp))
}

@Composable
fun MyCheckBox(texto: String,state: Boolean, onCheckedChange: (Boolean) -> Unit){
    Row(modifier = Modifier.fillMaxWidth()) {
        Checkbox(checked = state, onCheckedChange = { onCheckedChange(!state) })
        Spacer(modifier = Modifier.width(2.dp))
        Text(texto, modifier = Modifier.padding(vertical = 12.dp))
    }
}

@Composable
fun MultipleRadioButtons() {
    val selectedValue = remember { mutableStateOf("") }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

    val items = listOf("No", "Secundaria", "Superior")
    Row(Modifier.fillMaxWidth(0.8f)) {
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .selectable(
                        selected = isSelectedItem(item),
                        onClick = { onChangeState(item) },
                        role = Role.RadioButton
                    )
                    .padding(end = 27.dp)
            ) {
                RadioButton(
                    selected = isSelectedItem(item),
                    onClick = null
                )
                Text(
                    text = item,
                )
            }
        }
    }
}