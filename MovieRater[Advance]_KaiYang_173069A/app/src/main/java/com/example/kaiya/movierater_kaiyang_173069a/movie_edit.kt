package com.example.kaiya.movierater_kaiyang_173069a

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.kaiya.movierater_kaiyang_173069a.landing.Companion.allMovies
import com.example.kaiya.movierater_kaiyang_173069a.landing.Companion.selectedMovie
import kotlinx.android.synthetic.main.activity_movie_add.*
import kotlinx.android.synthetic.main.activity_movie_edit.*

class movie_edit : AppCompatActivity() {

    fun onCheckBoxClicked(v: View) {
        if (chkbxRating_edit.isChecked == true) {
            chkbxReason_edit.setVisibility(View.VISIBLE)
        } else {
            chkbxReason_edit.setVisibility(View.INVISIBLE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_edit)

        //reading values from movie entity class
        movie_name_edit.setText(allMovies[selectedMovie].name)
        movie_desc_edit.setText(allMovies[selectedMovie].desc)
        val currLang: String = allMovies[selectedMovie].lang
        if(currLang == "English")
        {
            english_edit.setChecked(true)
        }
        else if(currLang == "Chinese")
        {
            chinese_edit.setChecked(true)
        }
        else if(currLang == "Malay")
        {
            malay_edit.setChecked(true)
        }
        else if(currLang == "Tamil")
        {
            tamil_edit.setChecked(true)
        }
        movie_date_edit.setText(allMovies[selectedMovie].date)
        if(allMovies[selectedMovie].ageRating)
        {
            chkbxRating_edit.setChecked(true)
            chkbxReason_edit.setVisibility(View.VISIBLE)
        }
        if (allMovies[selectedMovie].violence)
        {
            Violence_edit.setChecked(true)
        }
        if (allMovies[selectedMovie].langUsed)
        {
            LangUsed_edit.setChecked(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_movie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.saveMovie)
        {
            //validation
            var count: Int = 0

            val movieName = movie_name_edit.text.toString().trim()
            val movieDate = movie_date_edit.text.toString().trim()
            val movieDesc = movie_desc_edit.text.toString().trim()

            if (movieName.isEmpty()) {
                movie_name_edit.setError("Field Empty!");
                count += 1
            }
            if (movieDate.isEmpty()) {
                movie_date_edit.setError("Field Empty!");
                count += 1
            }
            if (movieDesc.isEmpty()) {
                movie_desc_edit.setError("Field Empty!");
                count += 1
            }

            var langSelected: String = ""
            if (english_edit.isChecked) {
                langSelected = "English"
            } else if (chinese_edit.isChecked) {
                langSelected = "Chinese"
            } else if (malay_edit.isChecked) {
                langSelected = "Malay"
            } else if (tamil_edit.isChecked) {
                langSelected = "Tamil"
            }

            if(count == 0) {
                //update movie entity values
                allMovies[selectedMovie].name = movie_name_edit.text.toString()
                allMovies[selectedMovie].desc = movie_desc_edit.text.toString()
                allMovies[selectedMovie].lang = langSelected
                allMovies[selectedMovie].date = movie_date_edit.text.toString()
                allMovies[selectedMovie].ageRating = chkbxRating_edit.isChecked
                allMovies[selectedMovie].violence = Violence_edit.isChecked
                allMovies[selectedMovie].langUsed = LangUsed_edit.isChecked

                val intentSave = Intent(this@movie_edit, movie_details::class.java)
                startActivity(intentSave)
            }
        }
        if(item?.itemId == R.id.cancelEdit)
        {
            val intentCancel = Intent(this@movie_edit, landing::class.java)
            startActivity(intentCancel)
        }
        return super.onOptionsItemSelected(item)
    }
}
