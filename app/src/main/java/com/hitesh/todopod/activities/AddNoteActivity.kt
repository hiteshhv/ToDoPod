package com.hitesh.todopod.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hitesh.todopod.R
import com.hitesh.todopod.component.DaggerDateComponent
import com.hitesh.todopod.items
import com.hitesh.todopod.model.DateModel
import com.hitesh.todopod.model.ItemsViewModel
import kotlinx.android.synthetic.main.activity_add_note.*
import javax.inject.Inject

class AddNoteActivity : AppCompatActivity() {
    private var saveButton: Button? = null
    private var userInput: String? = null
    private var headerInput: String? = null

    private var itemsViewModel: ItemsViewModel? = null

    @Inject lateinit var dateModel: DateModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        saveButton = findViewById<View>(R.id.saveButton) as Button
        itemsViewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory(application))
                .get(ItemsViewModel::class.java)

        val dateModelComponent = DaggerDateComponent.create()
        dateModelComponent.inject(this)

    }

    /**
     * Method to save note
     *
     * @param view
     * @return null
     * */
    fun saveNote(view: View?) {
        headerInput = headerText?.text.toString()
        userInput = editText?.text.toString()
        val input = items(userInput, headerInput, 0)
        itemsViewModel?.insert(input)
        val mainActivity = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainActivity)
    }
}