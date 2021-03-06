package com.slx.dinnerdecider2

import android.content.Context
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.addFoodButton
import kotlinx.android.synthetic.main.activity_main.addFoodEditText
import kotlinx.android.synthetic.main.activity_main.decideButton
import kotlinx.android.synthetic.main.activity_main.selectedFoodTextView
import java.util.*

class MainActivity : AppCompatActivity() {

    private val foodList  = arrayListOf("Vegan Pizza", "Sweet Potatoes", "Hummus", "Falafel", "Red Lentil Burger")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var occupation = "developer"
        var name = "JonnyB"


        decideButton.setOnClickListener {
            val random = Random()
            selectedFoodTextView.text = foodList[random.nextInt(foodList.count())]
        }

        addFoodButton.setOnClickListener {
            var newFood = addFoodEditText.text.toString()
            if (newFood.trim().isNullOrEmpty()) {
                return@setOnClickListener
            }

            foodList.add(newFood)
            addFoodEditText.text.clear()
            println(foodList)
        }

        addFoodEditText.setOnEditorActionListener(TextView.OnEditorActionListener { view, keyCode, keyEvent ->
            if (keyCode == EditorInfo.IME_ACTION_DONE){
                hideKeyboard(view as TextView)
                return@OnEditorActionListener true
            }
            return@OnEditorActionListener false
        })

    }

    private fun hideKeyboard(sender: TextView)
    {
        val imm = baseContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow((sender).applicationWindowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        sender.clearFocus()
    }
}
