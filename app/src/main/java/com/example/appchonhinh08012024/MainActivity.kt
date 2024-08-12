package com.example.appchonhinh08012024

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
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
        imgRandom?.setOnClickListener {
            val intent = Intent(this@MainActivity, ListAnimalActivity::class.java)
            launcherListAnimal.launch(intent)
        }
        setImageRandom(imgRandom)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_random -> setImageRandom(imgRandom)
        }
        return true
    }

    private fun setImageRandom(imageView: ImageView?) {
        imageView ?: return
        val indexRandom = Random.nextInt(arrNameAnimals.size)
        val resourceRandom = resources.getIdentifier(arrNameAnimals[indexRandom], "drawable", packageName)
        imageView.setImageResource(resourceRandom)
    }

    private val launcherListAnimal = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

    }
}