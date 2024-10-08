package com.example.appchonhinh08012024

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var imgRandom: ImageView? = null
    private var imgUserSelect: ImageView? = null
    private var resourceRandom: Int = 0
    private val arrNameAnimals by lazy {
        resources.getStringArray(R.array.array_animal)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgRandom = findViewById(R.id.image_view_random)
        imgUserSelect = findViewById(R.id.image_view_user_select)
        imgUserSelect?.setOnClickListener {
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
        resourceRandom = resources.getIdentifier(arrNameAnimals[indexRandom], "drawable", packageName)
        imageView.setImageResource(resourceRandom)
    }

    private val launcherListAnimal = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val resource = result.data?.getIntExtra("resource", 0) ?: return@registerForActivityResult
            if (resource == 0) return@registerForActivityResult
            imgUserSelect?.setImageResource(resource)
            if (resource == resourceRandom) {
                Toast.makeText(this@MainActivity, "Chinh xac!!!", Toast.LENGTH_LONG).show()
            }
            Handler().postDelayed({
                setImageRandom(imgRandom)
            }, 2000)
        }
    }
}