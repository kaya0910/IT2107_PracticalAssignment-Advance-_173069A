package com.example.kaiya.movierater_kaiyang_173069a

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RatingBar
import com.example.kaiya.movierater_kaiyang_173069a.landing.Companion.allMovies
import com.example.kaiya.movierater_kaiyang_173069a.landing.Companion.selectedMovie
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.activity_movie_review.*

class movie_review : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_review)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.submit_review, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.save_review)
        {
            val intentAddReview = Intent(this@movie_review, movie_details::class.java)

            //allow stars to be seen
            val stars_1 = findViewById<RatingBar>(R.id.ratingBar)

            //ADD STARS TO MOVIE_DETAILS
            allMovies[selectedMovie].reviewStars = stars_1.rating.toDouble() //change current movie numStars value
            allMovies[selectedMovie].reviewText = review_text.text.toString() // change current movie current review text

            startActivity(intentAddReview)
        }
        return super.onOptionsItemSelected(item)
    }
}