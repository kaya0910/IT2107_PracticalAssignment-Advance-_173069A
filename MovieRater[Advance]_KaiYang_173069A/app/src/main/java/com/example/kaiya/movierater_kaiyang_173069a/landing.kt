package com.example.kaiya.movierater_kaiyang_173069a

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_landing.*
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.view.ViewGroup



class landing : AppCompatActivity() {

    companion object {
        var allMovies = arrayListOf<Movie>()
        var selectedMovie: Int = 0
    }

    class Movie(name: String, desc: String, reviewStars: Double, reviewText: String, lang: String, date: String, ageRating: Boolean, violence: Boolean, langUsed: Boolean)
    {
        var name: String
        var desc: String
        var reviewStars: Double
        var reviewText: String
        var lang: String
        var date: String
        var ageRating: Boolean
        var violence: Boolean
        var langUsed: Boolean

        init {
            this.name = name
            this.desc = desc
            this.reviewStars = reviewStars
            this.reviewText = reviewText
            this.lang = lang
            this.date = date
            this.ageRating = ageRating
            this.violence = violence
            this.langUsed = langUsed
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        for (i in allMovies.indices){

            //create new layout to append new textviews
            var layout = LinearLayout(this)
            findViewById<LinearLayout>(R.id.landing_layout).addView(layout)
            layout.orientation = LinearLayout.HORIZONTAL

            //Add image to the layout
            var image = ImageView(this)
            image.setImageResource(R.drawable.movie_img_landing)
            layout.addView(image)

            //tv to show movie name
            var newTv = TextView(this)
            newTv.setLayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
            newTv.setText(allMovies[i].name)
            newTv.setId(i)
            newTv.setMinHeight(150)
            newTv.gravity = Gravity.CENTER
            newTv.setClickable(true)
            layout.addView(newTv)

            //view movie details when click on
            newTv.setOnClickListener()
            {
                selectedMovie = i
                var intentView = Intent(this@landing, movie_details::class.java)
                startActivity(intentView)
            }

            //create a horizontal line to seperate textviews
            var line = View(this)
            line.setLayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3))
            line.setBackgroundColor(Color.parseColor("#cccccc"))
            findViewById<LinearLayout>(R.id.landing_layout).addView(line)

            registerForContextMenu(newTv)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_movie_landing, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.addMovie)
        {
            val intent = Intent(this@landing, movie_add::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        for(i in allMovies.indices)
        {
            if(v?.id == i)
            {
                menu?.add(1, 1001+i, 1, "Edit")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        for(i in allMovies.indices)
        {
            if (item?.itemId == 1001+i) {
                selectedMovie = i
                val intentEdit = Intent(this@landing, movie_edit::class.java)
                startActivity(intentEdit)
            }
        }
        return super.onContextItemSelected(item)
    }
}
