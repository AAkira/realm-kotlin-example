package com.github.aakira.realmexample.androidApp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.aakira.realmexample.shared.Database
import com.github.aakira.realmexample.shared.Greeting

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)

        Database.addPerson("abc", 20)
        val person = Database.queryPerson("abc")
        tv.text = person.toString()
    }
}
