package com.example.latihanfragment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private val historyKali = mutableListOf<String>()
    private val historyBagi = mutableListOf<String>()
    private val historyTambah = mutableListOf<String>()
    private val historyKurang = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etAngka1 = findViewById<EditText>(R.id.etAngka1)
        val etAngka2 = findViewById<EditText>(R.id.etAngka2)
        val btnKali = findViewById<Button>(R.id.btnKali)
        val btnBagi = findViewById<Button>(R.id.btnBagi)
        val btnTambah = findViewById<Button>(R.id.btnTambah)
        val btnKurang = findViewById<Button>(R.id.btnKurang)

        btnKali.setOnClickListener {
            val angka1 = etAngka1.text.toString().toDoubleOrNull()
            val angka2 = etAngka2.text.toString().toDoubleOrNull()
            if (angka1 == null || angka2 == null) {
                Toast.makeText(this, "Masukkan kedua angka", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val hasil = angka1 * angka2
            historyKali.add("$angka1 * $angka2 = $hasil")
            bukaFragment(FragmentKali(), historyKali, hasil)
        }

        btnBagi.setOnClickListener {
            val angka1 = etAngka1.text.toString().toDoubleOrNull()
            val angka2 = etAngka2.text.toString().toDoubleOrNull()
            if (angka1 == null || angka2 == null) {
                Toast.makeText(this, "Masukkan kedua angka", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (angka2 == 0.0) {
                Toast.makeText(this, "Tidak bisa membagi dengan 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val hasil = angka1 / angka2
            historyBagi.add("$angka1 / $angka2 = $hasil")
            bukaFragment(FragmentBagi(), historyBagi, hasil)
        }

        btnTambah.setOnClickListener {
            val angka1 = etAngka1.text.toString().toDoubleOrNull()
            val angka2 = etAngka2.text.toString().toDoubleOrNull()
            if (angka1 == null || angka2 == null) {
                Toast.makeText(this, "Masukkan kedua angka", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val hasil = angka1 + angka2
            historyTambah.add("$angka1 + $angka2 = $hasil")
            bukaFragment(FragmentTambah(), historyTambah, hasil)
        }

        btnKurang.setOnClickListener {
            val angka1 = etAngka1.text.toString().toDoubleOrNull()
            val angka2 = etAngka2.text.toString().toDoubleOrNull()
            if (angka1 == null || angka2 == null) {
                Toast.makeText(this, "Masukkan kedua angka", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val hasil = angka1 - angka2
            historyKurang.add("$angka1 - $angka2 = $hasil")
            bukaFragment(FragmentKurang(), historyKurang, hasil)
        }
    }

    private fun bukaFragment(fragment: Fragment, daftarHistory: List<String>, hasil: Double) {
        val bundle = Bundle().apply {
            putString("HISTORY", daftarHistory.joinToString("\n"))
            putBoolean("IS_NEGATIF", hasil < 0)
        }
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}