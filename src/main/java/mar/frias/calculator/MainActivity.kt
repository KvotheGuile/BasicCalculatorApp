package mar.frias.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var oper: Int = 0
    var numero1: Double = 0.0
    var numero2: Double = 0.0

    lateinit var txt_num1: TextView
    lateinit var txt_num2: TextView
    lateinit var btn_clear: Button
    lateinit var btn_clear_soft: Button
    lateinit var btn_equal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txt_num1 = findViewById<TextView>(R.id.num1)
        txt_num2 = findViewById<TextView>(R.id.num2)
        btn_clear = findViewById<Button>(R.id.btn_delete)
        btn_equal = findViewById<Button>(R.id.btn_equal)
        btn_clear_soft = findViewById<Button>(R.id.btn_delete_soft)

        btn_clear_soft.setOnClickListener {
            var current_text = txt_num2.text.toString()
            if (current_text == "0"){
                current_text = txt_num1.text.toString()
                if (oper != 0){
                    oper = 0
                    current_text = current_text.dropLast(2)
                }
                txt_num1.setText("")
                numero1 = 0.0
            }
            else if (current_text.length >= 1){
                current_text = current_text.dropLast(1)
            }
            if (current_text == ""){
                current_text = "0"
            }
            txt_num2.setText(current_text)
        }

        btn_clear.setOnClickListener {
            txt_num1.setText("")
            txt_num2.setText("0.0")
            numero1 = 0.0
            numero2 = 0.0
            oper = 0
        }

        btn_equal.setOnClickListener {
            calculate()
        }

    }

    fun calculate(){
        if (oper == 0){
            return
        }

        var result: Double = 0.0
        numero2 = try { txt_num2.text.toString().toDouble()
        } catch (e: NumberFormatException){
            0.0
        }


        if (oper == 1){
            result = numero1 + numero2
        }
        if (oper == 2){
            result = numero1 - numero2
        }
        if (oper == 3){
            result = numero1 * numero2
        }
        if (oper == 4){
            if (numero2 != 0.0){
                result = numero1 / numero2
            }
        }

        oper = 0
        txt_num1.text = ""
        txt_num2.text = result.toString()
    }

    fun digitPress(view: View){

        var num2: String = txt_num2.text.toString()

        if (num2 == "0.0"){
            num2 = ""
        }

        when(view.id){
            R.id.btn_0 -> txt_num2.setText(num2 + "0")
            R.id.btn_1 -> txt_num2.setText(num2 + "1")
            R.id.btn_2 -> txt_num2.setText(num2 + "2")
            R.id.btn_3 -> txt_num2.setText(num2 + "3")
            R.id.btn_4 -> txt_num2.setText(num2 + "4")
            R.id.btn_5 -> txt_num2.setText(num2 + "5")
            R.id.btn_6 -> txt_num2.setText(num2 + "6")
            R.id.btn_7 -> txt_num2.setText(num2 + "7")
            R.id.btn_8 -> txt_num2.setText(num2 + "8")
            R.id.btn_9 -> txt_num2.setText(num2 + "9")
            R.id.btn_point -> {
                if (!txt_num2.text.contains('.')){
                    txt_num2.setText(num2 + ".")
                }
            }
        }

    }

    fun operationPress(view: View){

        if (oper != 0){
            calculate()
        }

        numero1 = txt_num2.text.toString().toDouble()
        var num2_text: String = txt_num2.text.toString()

        when(view.id){
            R.id.btn_plus -> {
                txt_num1.setText(num2_text + " +")
                oper = 1
            }

            R.id.btn_minus -> {
                txt_num1.setText(num2_text + " -")
                oper = 2
            }

            R.id.btn_mult -> {
                txt_num1.setText(num2_text + " *")
                oper = 3
            }

            R.id.btn_div -> {
                txt_num1.setText(num2_text + " /")
                oper = 4
            }
        }

        txt_num2.setText("")

    }
}