package com.example.recyclerviewproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = generateListOfItems()
        val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter : recycler_view_adapter? = items?.let { recycler_view_adapter(it) }
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        val swipeRefresher = findViewById<SwipeRefreshLayout>(R.id.swipeRefresher)
        swipeRefresher.setOnRefreshListener{
            run {
                items?.add(RecyclerItems("title added", "description added", R.drawable.ic_android_))
                adapter?.notifyDataSetChanged()
                swipeRefresher.isRefreshing = false
            }
        }

    }


    private fun generateListOfItems(): ArrayList<RecyclerItems>? {

        val lines: List<String> = readlines(R.raw.test)
        var items = ArrayList<RecyclerItems>()

        lines.forEach { line ->
            run {
                val tab = line.split(':')
                if (tab.size >= 2)
                    items.add(RecyclerItems(tab[0], tab[1], R.drawable.ic_android_))
            }
        }
        return items
    }

    private fun readlines(file_resource : Int) : ArrayList<String>{
        val result = ArrayList<String>()
        val `is`: InputStream = this.resources.openRawResource(file_resource)
        val reader = BufferedReader(InputStreamReader(`is`))
        var string : String? = ""
        while (true) {
            try {
                if (reader.readLine().also { string = it} == null) break
            } catch (e: IOException) {
                e.printStackTrace()
            }
            result.add(string.toString())
        }
        `is`.close()
        return result
    }
}