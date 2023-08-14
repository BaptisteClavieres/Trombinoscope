package fr.isen.clavieres.trombinoscope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    data class Personne(
        val prenom: String,
        val nom: String,
        val photoUrl: String,
        val titre: String,
        val email: String,
        val telephone: String,
        val ville: String,
        val region: String,
        val pays: String,
        val codepostal: String,
        val numero: String,
        val name: String,
        val date: String,
        val age: String,
        val nationalite: String,
        val username: String,
        val motdepasse: String,
        val latitude: String,
        val longitude: String,
        val fuseauhoraire: String,
        val description: String
    )

    private val items = mutableListOf<Personne>()
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.Liste)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = Adapter(items, this)
        recyclerView.adapter = adapter

        val queue = Volley.newRequestQueue(this)
        val url = "https://randomuser.me/api/?results=25"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                // Parsez les données JSON avec GSON
                val results = response.getJSONArray("results")
                val gson = Gson()
                for (i in 0 until results.length()) {
                    val personJson = results.getJSONObject(i)
                    val personData = gson.fromJson(personJson.toString(), PersonData::class.java)
                    val city = personData.location.city ?: "Ville inconnue"
                    val state = personData.location.state ?: "Région inconnue"
                    val country = personData.location.country ?: "pays inconnu"
                    val postcode = personData.location.postcode ?: "Code postal inconnu"
                    val title = personData.name.title ?: "Titre inconnu"
                    val nationality = personData.nat ?: "Nationalité inconnue"
                    val username = personData.login.username ?: "Username inconnu"
                    val password = personData.login.password ?: "Mot de passe inconnu"
                    val cell = personData.cell ?: "Téléphone inconnu"
                    val email = personData.email ?: "Email inconnu"
                    val latitude = personData.location.coordinates?.latitude ?: "Latitude inconnue"
                    val longitude = personData.location.coordinates?.longitude ?: "Longitude inconnue"
                    val offset = personData.location.timezone?.offset ?: "Fuseau horaire inconnu"
                    val description = personData.location.timezone?.description ?: "Description inconnue"

                    val person = Personne(
                        personData.name.first,
                        personData.name.last,
                        personData.picture.large,
                        title,
                        email,
                        cell,
                        city,
                        state,
                        country,
                        postcode,
                        personData.location.street.number,
                        personData.location.street.name,
                        personData.dob.date,
                        personData.dob.age,
                        nationality,
                        username,
                        password,
                        latitude,
                        longitude,
                        offset,
                        description
                    )
                    items.add(person)
                }
                adapter.notifyDataSetChanged()
            },
            Response.ErrorListener { error ->
                Log.e(TAG, "Error: ${error.message}")
            })

        // Ajoutez la requête à la RequestQueue
        queue.add(request)

    }
    fun onItemClick(position: Int) {
        val intent = Intent(this, DescriptionActivity::class.java)
        val person = items[position]

        intent.putExtra("user_photo", person.photoUrl)
        intent.putExtra("user_firstname", person.prenom)
        intent.putExtra("user_lastname", person.nom)
        intent.putExtra("user_title", person.titre)
        intent.putExtra("user_email", person.email)
        intent.putExtra("user_cell", person.telephone)
        intent.putExtra("user_city", person.ville)
        intent.putExtra("user_state", person.region)
        intent.putExtra("user_country", person.pays)
        intent.putExtra("user_postcode", person.codepostal)
        intent.putExtra("user_number", person.numero)
        intent.putExtra("user_name", person.name)
        intent.putExtra("user_date", person.date)
        intent.putExtra("user_age", person.age)
        intent.putExtra("user_nationality", person.nationalite)
        intent.putExtra("user_username", person.username)
        intent.putExtra("user_password", person.motdepasse)
        intent.putExtra("user_latitude", person.latitude)
        intent.putExtra("user_longitude", person.longitude)
        intent.putExtra("user_offset", person.fuseauhoraire)
        intent.putExtra("user_description", person.description)
        startActivity(intent)
    }


    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }
}


data class PersonData(
    val gender: String,
    val name: NameData,
    val location: LocationData,
    val email: String,
    val login: LoginData,
    val dob: DobData,
    val registered: RegisteredData,
    val phone: String,
    val cell: String,
    val id: IdData,
    val picture: PictureData,
    val nat: String,
    val timezone:TimezoneData
)

data class NameData(
    val title: String,
    val first: String,
    val last: String
)

data class LocationData(
    val street: StreetData,
    val city: String?,
    val state: String?,
    val country: String?,
    val postcode: String?,
    val coordinates: CoordinatesData,
    val timezone: TimezoneData
)

data class StreetData(
    val number: String,
    val name: String
)

data class CoordinatesData(
    val latitude: String,
    val longitude: String
)

data class TimezoneData(
    val offset: String,
    val description: String
)

data class LoginData(
    val uuid: String,
    val username: String?,
    val password: String?,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)

data class DobData(
    val date: String,
    val age: String
)

data class RegisteredData(
    val date: String,
    val age: Int
)

data class IdData(
    val name: String,
    val value: String
)

data class PictureData(
    val large: String,
    val medium: String,
    val thumbnail: String
)
