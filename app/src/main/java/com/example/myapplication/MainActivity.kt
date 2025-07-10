package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = ViewModelProvider(this)[ViewModel::class.java]

        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Click(

                        modifier = Modifier.padding(innerPadding), viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Click(modifier: Modifier = Modifier, viewModel: ViewModel) {

    var input1 by remember { mutableStateOf("") }

    var input2 by remember { mutableStateOf("") }

    val data by viewModel.data.observeAsState()




    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(38.dp))


        OutlinedTextField(
            value = input1,
            onValueChange = { input1 = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Item") }
        )



        Spacer(modifier = Modifier.height(15.dp))



        OutlinedTextField(
            value = input2,
            onValueChange = { input2 = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Price") }

        )



        Spacer(modifier = Modifier.height(15.dp))

        Button({
            viewModel.addQuotess(qoutes = input1, item = input2)
            input2 = ""
            input1 = ""
        }) {
            Text("Add")
        }



        Spacer(modifier = Modifier.height(15.dp))

        data?.let {
            LazyColumn { itemsIndexed(it) { index: Int, item: Data -> list(item,viewModel) } }
        }

    }


}

@Composable
fun list(data: Data,viewModel: ViewModel) {

    Card(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column( verticalArrangement = Arrangement.SpaceEvenly) {
                Text(text = data.quotes,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,

                )

                Text(
                    text = "Price = $${data.item}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(
                    {viewModel.delete(data.id)}, modifier = Modifier.padding(10.dp), colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,       // background color
                        contentColor = Color.White        // text/icon color
                    )
                ) { Text("Delete") }

                Button(
                    {},
                    modifier = Modifier.padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,       // background color
                        contentColor = Color.White
                    )       // text/icon color)
                ) { Text("Edit") }
            }
        }
    }

}