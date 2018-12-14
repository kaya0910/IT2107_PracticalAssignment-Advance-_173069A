package com.example.kaiya.movierater_kaiyang_173069a

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.TextView
import com.example.kaiya.movierater_kaiyang_173069a.landing.Companion.allMovies
import com.example.kaiya.movierater_kaiyang_173069a.landing.Companion.selectedMovie
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.activity_movie_review.*

class movie_details : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        if(allMovies[selectedMovie].reviewStars == 0.0) {
            //add movie
            movieTitle.text = allMovies[selectedMovie].name
            movieOverview.text = allMovies[selectedMovie].desc
            movieLang.text = allMovies[selectedMovie].lang
            movieDate.text = allMovies[selectedMovie].date
            if (allMovies[selectedMovie].ageRating) //chkbox for "suitable for age 13 under"
            {
                if (allMovies[selectedMovie].violence && allMovies[selectedMovie].langUsed) {
                    movieAgeRating.text = "No (Violence & Language Used)"
                } else if (allMovies[selectedMovie].violence) {
                    movieAgeRating.text = "No (Violence)"
                } else if (allMovies[selectedMovie].langUsed) {
                    movieAgeRating.text = "No (Language Used)"
                }
            } else {
                movieAgeRating.text = "Yes"
            }
        } else{
            movieTitle.text = allMovies[selectedMovie].name
            movieOverview.text = allMovies[selectedMovie].desc
            reviewStars.setVisibility(View.VISIBLE)
            reviewStars.setRating(allMovies[selectedMovie].reviewStars.toFloat())
            movieReviews.text = allMovies[selectedMovie].reviewText
            movieLang.text = allMovies[selectedMovie].lang
            movieDate.text = allMovies[selectedMovie].date
            if (allMovies[selectedMovie].ageRating) //chkbox for "suitable for age 13 under"
            {
                if (allMovies[selectedMovie].violence && allMovies[selectedMovie].langUsed) {
                    movieAgeRating.text = "No (Violence & Language Used)"
                } else if (allMovies[selectedMovie].violence) {
                    movieAgeRating.text = "No (Violence)"
                } else if (allMovies[selectedMovie].langUsed) {
                    movieAgeRating.text = "No (Language Used)"
                }
            } else {
                movieAgeRating.text = "Yes"
            }
        }

        registerForContextMenu(movieReviews)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menuInflater.inflate(R.menu.add_review, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.addReview)
        {
            val intent = Intent(this@movie_details, movie_review::class.java)
            startActivity(intent)
        }
        return super.onContextItemSelected(item)
    }
}
