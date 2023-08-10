import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apolis.booklibararyhwday17.R
import com.apolis.booklibararyhwday17.database.entity.Author

class AuthorAdapter(private val authors: List<Author>) : RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.author_item, parent, false)
        return AuthorViewHolder(view)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val author = authors[position]

        holder.tvAuthorName.text = "Name: ${author.name}"
        holder.ratingBar.rating = author.rating.toFloat()
    }

    override fun getItemCount(): Int {
        return authors.size
    }

    class AuthorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAuthorName: TextView = itemView.findViewById(R.id.tvAuthorName)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
    }
}
