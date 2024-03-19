package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
//androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.ui.theme.BMICalculatorTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                        Image(
                            painter = painterResource(id = R.drawable.spring),
                            contentDescription = null, // 이미지에 대한 설명은 없음
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )

                        BMICalculator()

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICalculator() {
    var inputHeight by remember { mutableStateOf("") }
    var inputWeight by remember { mutableStateOf("") }
    var Result=""
    var yourBMI = "0.00"

    if (inputHeight.isNotEmpty() && inputWeight.isNotEmpty()) {
        val height = inputHeight.toDoubleOrNull()
        val weight = inputWeight.toDoubleOrNull()
        if (height != null && weight != null) {
            val bmi = 10000 * weight / (height * height)
            val originalBmi = "%.2f".format(bmi)
            yourBMI = originalBmi.toString()

            if (bmi >= 0.00 && bmi <18.5){
                Result = "저체중"
            }else if(bmi >=18.5 && bmi < 25){
                Result = "정상"
            }else if (bmi >=25 && bmi <30){
                Result = "과체중"
            }else{
                Result = "비만"
            }
        } else {
            yourBMI = "숫자로만 입력하세요"

        }
    }else {
        yourBMI = "키와 몸무게를 숫자로만 입력하세요"
        }


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "BMI Calculator",
                fontSize = 24.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = inputHeight,
                onValueChange = { inputHeight = it },
                label = { Text(text = ("키를 입력하세요.(cm)")) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor =Color.Blue,
                    unfocusedContainerColor = White,
                    focusedContainerColor = White
                )
                )

            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = inputWeight,
                onValueChange = { inputWeight = it },
                label = { Text(text = ("몸무게를 입력하세요.(kg)")) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor =Color.Blue,
                    unfocusedContainerColor = White,
                    focusedContainerColor = White
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = yourBMI,
                onValueChange = { yourBMI = it },
                label = { Text(text = ("당신의 BMI지수는?")) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor =Color.Blue,
                    unfocusedContainerColor = White,
                    focusedContainerColor = White
                ))

            OutlinedTextField(

                value = Result,
                onValueChange = { Result = it },
                label = { Text(text = ("결과")) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor =Color.Blue,
                    unfocusedContainerColor = when (Result) {
                        "저체중" -> Color.Cyan
                        "정상" -> Color.Green
                        "과체중" -> Color.Yellow
                        "비만" -> Color.Red
                        else -> White
                    },
                    focusedContainerColor = when (Result) {
                        "저체중" -> Color.Cyan
                        "정상" -> Color.Green
                        "과체중" -> Color.Yellow
                        "비만" -> Color.Red
                        else -> White
                    }

                    )
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Made by MJSON",
                fontSize = 10.sp,
                color = Blue,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "손연우~~",
                fontSize = 10.sp,
                color = Blue,
                fontWeight = FontWeight.Bold
            )


        }
    }


@Preview(showBackground = true)
@Composable
fun BMICalculatorPreview() {
    BMICalculator()
}

