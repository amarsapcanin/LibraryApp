import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.apolis.booklibararyhwday17.R
import com.apolis.booklibararyhwday17.database.DbHelper
import com.apolis.booklibararyhwday17.database.dao.AuthorDao
import com.apolis.booklibararyhwday17.databinding.FragmentAddAuthorsBinding
import com.google.android.material.snackbar.Snackbar

class FragmentAddAuthors : Fragment() {

    private lateinit var binding: FragmentAddAuthorsBinding
    private lateinit var authorDao: AuthorDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAuthorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authorDao = AuthorDao(DbHelper(requireContext()))

        binding.seekBarRating.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Display the selected rating in the TextView
                binding.tvSelectedRating.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // No specific action needed when the user starts tracking the SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // No specific action needed when the user stops tracking the SeekBar
            }
        })

        binding.btnUpdateAuthor.setOnClickListener {
            val authorName = binding.etAuthorName.text.toString()
            val rating = binding.seekBarRating.progress

            val existingAuthor = authorDao.getAllAuthors().find { it.name == authorName }

            if (existingAuthor != null) {
                existingAuthor.rating = rating
                val rowsUpdated = authorDao.updateAuthor(existingAuthor)
                if (rowsUpdated > 0) {
                    showSnackbar("Author rating updated successfully")
                } else {
                    showSnackbar("Update failed (author with the provided name not found)")
                }
            } else {
                showSnackbar("Author with the provided name not found")
            }
        }

    }

    private fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }
}
