package com.example.kaiya.movierater_kaiyang_173069a

import android.content.ClipDescription
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_movie_add.*
import android.widget.*
import com.example.kaiya.movierater_kaiyang_173069a.landing.Companion.allMovies
import com.example.kaiya.movierater_kaiyang_173069a.landing.Companion.selectedMovie
import com.example.kaiya.movierater_kaiyang_173069a.landing.Movie
import kotlinx.android.synthetic.main.activity_movie_details.*

class movie_add : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_add)
    }

    fun onCheckBoxClicked(v: View) {
        if (chkbxRating.isChecked == true) {
            chkbxReason.setVisibility(View.VISIBLE)
        } else {
            chkbxReason.setVisibility(View.INVISIBLE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_movie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.addMovie) {

            var language: String = "";

            if (english.isChecked) {
                language = "English"
            } else if (chinese.isChecked) {
                language = "Chinese"
            } else if (malay.isChecked) {
                language = "Malay"
            } else if (tamil.isChecked) {
                language = "Tamil"
            }

            var all_reason: String = "";

            if (Violence.isChecked == true && LangUsed.isChecked == true) {
                all_reason = "Violence\nLanguage Used"
            } else if (Violence.isChecked == true) {
                all_reason = "Violence"
            } else if (LangUsed.isChecked == true) {
                all_reason = "Language Used"
            }

            var count: Int = 0

            val movieName = movie_name.text.toString().trim()
            val movieDate = movie_date.text.toString().trim()
            val movieDesc = movie_desc.text.toString().trim()

            if (movieName.isEmpty()) {
                movie_name.setError("Field Empty!");
                count += 1
            }

            if (movieDate.isEmpty()) {
                movie_date.setError("Field Empty!");
                count += 1
            }
            if (movieDesc.isEmpty()) {
                movie_desc.setError("Field Empty!");
                count += 1
            }

            //toast
            if (count == 0) {

                val intentAdd = Intent(this@movie_add, movie_details::class.java)
                //create new movie object
                var newMovie = Movie(movie_name.text.toString(), movie_desc.text.toString(), 0.0,
                    "No Reviews yet.\nLong press here to add your review", language, movie_date.text.toString(),
                                chkbxRating.isChecked, Violence.isChecked, LangUsed.isChecked)
                //add object into array
                allMovies.add(newMovie)
                //target new movie to show in details page
                selectedMovie = allMovies.indexOf(newMovie)
                startActivity(intentAdd)

                Toast.makeText(
                    this,
                    "Title = " + movie_name.text + "\nDescription = " + movie_desc.text + "\nRelease Date = " + movie_date.text + "\nLanguage = " + language + "\nSuitable for all ages = " + chkbxRating.isChecked + "\nReason: \n" + all_reason,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        else if(item?.itemId == R.id.clearEntry)
        {
            movie_name.setText("")
            movie_desc.setText("")
            english.setChecked(true)
            movie_date.setText("")
            chkbxRating.setChecked(false)
            Violence.setChecked(false)
            LangUsed.setChecked(false)
            if (chkbxRating.isChecked == true) {
                chkbxReason.setVisibility(View.VISIBLE)
            } else {
                chkbxReason.setVisibility(View.INVISIBLE)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
