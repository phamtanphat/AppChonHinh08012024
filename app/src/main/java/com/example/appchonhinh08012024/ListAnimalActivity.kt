package com.example.appchonhinh08012024

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Collections

class ListAnimalActivity : AppCompatActivity() {

    private var tableLayout: TableLayout? = null
    private val arrNameAnimals by lazy {
        resources.getStringArray(R.array.array_animal)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_animal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.table_layout_list_animal)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Shuffle list animals
        arrNameAnimals.shuffle()

        displayListAnimal()
    }

    private fun displayListAnimal() {
        tableLayout = findViewById(R.id.table_layout_list_animal)

        // 3 cot chinh la 3 image view
        // 6 dong chinh la 6 table row
        val totalRow = 6
        val totalColumn = 3
        var index: Int
        var resource: Int

        for (i in 0 until totalRow) {
            val tableRow = TableRow(this)
            for (y in 0 until totalColumn) {
                val imageView = ImageView(this)
                index = (i * 3) + y
                resource = resources.getIdentifier(arrNameAnimals[index], "drawable", packageName)
                imageView.setImageResource(resource)
                imageView.tag = resource
                imageView.setOnClickListener {
                    val intent = Intent(this@ListAnimalActivity, MainActivity::class.java)
                    intent.putExtra("resource", (imageView.tag as? Int) ?: 0)
                    setResult(RESULT_OK, intent)
                    finish()
                }
                tableRow.addView(imageView)
            }
            tableLayout?.addView(tableRow)
        }
    }
}