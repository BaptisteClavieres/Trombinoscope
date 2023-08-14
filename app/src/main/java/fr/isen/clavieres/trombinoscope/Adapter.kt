package fr.isen.clavieres.trombinoscope

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class Adapter(private val items: List<MainActivity.Personne>, private val listener: MainActivity) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cellule, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
        val item = items[position]

        holder.firstNameTextView.text = item.prenom
        holder.lastNameTextView.text = item.nom

        // Utilisez Picasso pour charger l'image à partir de l'URL
        Picasso.get().load(item.photoUrl).into(holder.photoImageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val firstNameTextView: TextView = view.findViewById(R.id.first)
        val lastNameTextView: TextView = view.findViewById(R.id.last)
        val photoImageView: ImageView = view.findViewById(R.id.photo)

    }

}
