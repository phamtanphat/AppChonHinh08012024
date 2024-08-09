package com.example.appchonhinh08012024

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var imgRandom: ImageView? = null
    private var imgUserSelect: ImageView? = null
    private val arrNameAnimals by lazy {
        resources.getStringArray(R.array.array_animal)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgRandom = findViewById(R.id.image_view_random)
        imgUserSelect = findViewById(R.id.image_view_user_select)

        val indexRandom = Random.nextInt(arrNameAnimals.size)
        val resourceRandom = resources.getIdentifier(arrNameAnimals[indexRandom], "drawable", packageName)
        imgRandom?.setImageResource(resourceRandom)
    }
}