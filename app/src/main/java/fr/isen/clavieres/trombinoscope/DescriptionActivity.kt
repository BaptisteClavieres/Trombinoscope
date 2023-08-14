package fr.isen.clavieres.trombinoscope

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class DescriptionActivity : AppCompatActivity() {
    private lateinit var imageViewPhoto: ImageView
    private lateinit var textViewTitle: TextView
    private lateinit var textViewFirstName: TextView
    private lateinit var textViewLastName: TextView
    private lateinit var textViewEmail: TextView
    private lateinit var textViewCell: TextView
    private lateinit var textViewCity: TextView
    private lateinit var textViewState: TextView
    private lateinit var textViewCountry: TextView
    private lateinit var textViewPostcode: TextView
    private lateinit var textViewNumber: TextView
    private lateinit var textViewName: TextView
    private lateinit var textViewDate: TextView
    private lateinit var textViewAge: TextView
    private lateinit var textViewNationality: TextView
    private lateinit var textViewUsername: TextView
    private lateinit var textViewPassword: TextView
    private lateinit var textViewLatitude: TextView
    private lateinit var textViewLongitude: TextView
    private lateinit var textViewOffset: TextView
    private lateinit var textViewDescription: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        imageViewPhoto = findViewById(R.id.imageViewPhoto)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewFirstName = findViewById(R.id.textViewFirstName)
        textViewLastName = findViewById(R.id.textViewLastName)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewEmail = findViewById(R.id.textViewEmail)
        textViewCell = findViewById(R.id.textViewCell)
        textViewCity = findViewById(R.id.textViewCity)
        textViewState = findViewById(R.id.textViewState)
        textViewCountry = findViewById(R.id.textViewCountry)
        textViewPostcode = findViewById(R.id.textViewPostcode)
        textViewNumber = findViewById(R.id.textViewNumber)
        textViewName = findViewById(R.id.textViewStreet)
        textViewNationality = findViewById(R.id.textViewNationality)
        textViewUsername = findViewById(R.id.textViewUsername)
        textViewPassword = findViewById(R.id.textViewPassword)
        textViewDate = findViewById(R.id.textViewDOB)
        textViewAge = findViewById(R.id.textViewAge)
        textViewLatitude = findViewById(R.id.textViewLatitude)
        textViewLongitude = findViewById(R.id.textViewLongitude)
        textViewOffset = findViewById(R.id.textViewOffset)
        textViewDescription = findViewById(R.id.textViewDescription)

        // initialisation d'autres vues

        val userPhotoUrl = intent.getStringExtra("user_photo")
        val userFirstName = intent.getStringExtra("user_firstname")
        val userLastName = intent.getStringExtra("user_lastname")
        val userTitle = intent.getStringExtra("user_title")
        val userEmail = intent.getStringExtra("user_email")
        val userCell = intent.getStringExtra("user_cell")
        val userCity = intent.getStringExtra("user_city")
        val userState = intent.getStringExtra("user_state")
        val userCountry = intent.getStringExtra("user_country")
        val userPostcode = intent.getStringExtra("user_postcode")
        val userNumber = intent.getStringExtra("user_number")
        val userName = intent.getStringExtra("user_name")
        val userUsername = intent.getStringExtra("user_username")
        val userPassword = intent.getStringExtra("user_password")
        val userNationality = intent.getStringExtra("user_nationality")
        val userDate = intent.getStringExtra("user_date")
        val userAge = intent.getStringExtra("user_age")
        val userLatitude = intent.getStringExtra("user_latitude")
        val userLongitude = intent.getStringExtra("user_longitude")
        val userOffset = intent.getStringExtra("user_offset")
        val userDescription = intent.getStringExtra("user_description")

        // Charger l'image de l'URL avec Picasso
        Picasso.get().load(userPhotoUrl).into(imageViewPhoto)

        textViewFirstName.text = userFirstName
        textViewLastName.text = userLastName
        textViewTitle.text = userTitle
        textViewEmail.text = userEmail
        textViewCell.text = userCell
        textViewCity.text = userCity
        textViewCountry.text = userCountry
        textViewState.text = userState
        textViewNumber.text = userNumber
        textViewName.text = userName
        textViewNationality.text = userNationality
        textViewUsername.text = userUsername
        textViewPassword.text = userPassword
        textViewPostcode.text = userPostcode
        textViewDate.text = userDate
        textViewAge.text = userAge
        textViewLatitude.text = userLatitude
        textViewLongitude.text = userLongitude
        textViewOffset.text = userOffset
        textViewDescription.text = userDescription
        // autres détails
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
