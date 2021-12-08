package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculatorapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var output = 0f
    private var operator = ' '
    private var num1 = ""
    private var num2 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvOne.setOnClickListener{setNum('1')}
        binding.tvTwo.setOnClickListener{setNum('2')}
        binding.tvThree.setOnClickListener{setNum('3')}
        binding.tvFour.setOnClickListener{setNum('4')}
        binding.tvFive.setOnClickListener{setNum('5')}
        binding.tvSix.setOnClickListener{setNum('6')}
        binding.tvSeven.setOnClickListener{setNum('7')}
        binding.tvEight.setOnClickListener{setNum('8')}
        binding.tvNine.setOnClickListener{setNum('9')}



        // opreations


        binding.tvPlus.setOnClickListener{ handleOperator('+')}
        binding.tvMinus.setOnClickListener{ handleOperator('-')}
        binding.tvMul.setOnClickListener{ handleOperator('*')}
        binding.tvDivide.setOnClickListener{ handleOperator('/')}
        tvDot.setOnClickListener {
            if(operator==' '&&!num1.contains(".")){setNum('.')}
            if(operator!=' '&&!num2.contains(".")){setNum('.')}
        }
        binding.tvClear.setOnClickListener{ clearAll()}
        binding.tvBack.setOnClickListener{ deleteLast()}
        binding.tvEquals.setOnClickListener{ calculate()}





    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val savedOutput = output
        val savedOperator = operator
        val savedNum1 = num1
        val savedNum2 = num2

        outState.putFloat("savedOutput", savedOutput)
        outState.putChar("savedOperator", savedOperator)
        outState.putString("savedNum1", savedNum1)
        outState.putString("savedNum2", savedNum2)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        output = savedInstanceState.getFloat("savedOutput", 0f)
        operator = savedInstanceState.getChar("savedOperator", ' ')
        num1 = savedInstanceState.getString("savedNum1", "")
        num2 = savedInstanceState.getString("savedNum2", "")

        if(operator==' '){
            tvResult.text = num1
        }else{
            val text = num1 + operator + num2
            tvExp.text = text
        }

    }

    private fun setNum(num: Char){
        if(operator==' '){
            num1 += num
            tvExp.text = num1
        }else{
            num2 += num
            val text = num1 + operator + num2
            tvExp.text = text
        }
    }

    private fun handleOperator(op: Char){
        operator = op
        val text = num1 + operator
        tvExp.text = text
    }
    private fun calculate(){
        when (operator) {
            '+' -> output = num1.toFloat() + num2.toFloat()
            '-' -> output = num1.toFloat() - num2.toFloat()
            '*' -> output = num1.toFloat() * num2.toFloat()
            '/' -> if(num1.toFloat()!=0f&&num2.toFloat()!=0f){output = num1.toFloat() / num2.toFloat()}
        }
        num1 = output.toString()
        num2 = ""
        tvResult.text = output.toString()
    }


    private fun clearAll(){
        output = 0f
        operator = ' '
        num1 = ""
        num2 = ""
        tvExp.text = "0"
        tvResult.text = ""

    }

    private fun deleteLast(){
        if(operator==' '){
            if(num1.isNotEmpty()){
                num1 = num1.substring(0, num1.length - 1)
                if(num1.isEmpty()){tvExp.text = "0"}
                else{tvExp.text = num1}
            }
        }else{
            if(num2.isNotEmpty()){
                num2 = num2.substring(0, num2.length - 1)
                val text = num1 + operator + num2
                tvExp.text = text
            }else{
                operator=' '
                tvExp.text = num1
            }
        }
    }
}