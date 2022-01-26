package com.example.appkode.presentation

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appkode.R
import com.example.appkode.databinding.FragmentUsersListBinding
import com.example.appkode.di.appComponent
import com.example.appkode.util.SortOrder
import com.example.appkode.util.changeTrueItemColor
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

//Toast.makeText(requireContext(),"2",Toast.LENGTH_SHORT).show()
class UsersListFragment : Fragment(), SearchView.OnQueryTextListener {

    @Inject
    lateinit var usersViewModelFactory: Lazy<UsersViewModel.Factory>
    private val viewModel: UsersViewModel by viewModels { usersViewModelFactory.get() }
    private val usersAdapter: UsersAdapter by lazy { UsersAdapter(requireActivity(), viewModel) }
    private var _binding: FragmentUsersListBinding? = null
    private val binding get() = _binding!!


    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.takeIf { it.containsKey("getDepartment") }?.apply {
            viewModel.department.value = getString("getDepartment").toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUsersListBinding.inflate(inflater, container, false)
        binding.usersList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }

        viewModel.filter.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                usersAdapter.submitList(it)
            } else if (it.isEmpty() && !viewModel.isOnline(requireContext())){
                findNavController().navigate(R.id.errorFragment)
            }else{
                lifecycleScope.launch { viewModel.getUser() }
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = ""
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            viewModel.queryFlow.value = newText
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.choice -> {
                val dialog = BottomSheetDialog(requireContext())
                val view=layoutInflater.inflate(R.layout.bottom_filter,null)

                val dialogFilterByAbc = view.findViewById<ImageView>(R.id.filterByAbc)
                val dialogFilterByBirthday = view.findViewById<ImageView>(R.id.filterByBirthday)

                dialogFilterByAbc.setOnClickListener {
                    //item.changeTrueItemColor(requireContext())
                    viewModel.sortOrder.value = SortOrder.BY_NAME
                    dialog.dismiss()
                }
                dialogFilterByBirthday.setOnClickListener {
                    viewModel.sortOrder.value = SortOrder.BY_DATA
                    //item.changeTrueItemColor(requireContext())
                    dialog.dismiss()
                }
                dialog.setContentView(view)
                dialog.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}