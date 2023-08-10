import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolis.booklibararyhwday17.database.DbHelper
import com.apolis.booklibararyhwday17.database.dao.AuthorDao
import com.apolis.booklibararyhwday17.databinding.FragmentAuthorsBinding

class FragmentAuthors : Fragment() {

    private lateinit var binding: FragmentAuthorsBinding // Replace 'FragmentAuthorsBinding' with your actual binding class name
    private lateinit var authorDao: AuthorDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using your binding class
        binding = FragmentAuthorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authorDao = AuthorDao(DbHelper(requireContext()))

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvAllAuthors.layoutManager = layoutManager

        val authors = authorDao.getAllAuthors()

        val adapter = AuthorAdapter(authors)
        binding.rvAllAuthors.adapter = adapter
    }
}
