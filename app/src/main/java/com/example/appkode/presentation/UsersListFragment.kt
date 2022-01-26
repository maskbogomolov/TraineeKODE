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
import com.example.appkode.util.*
import com.example.appkode.util.Const.KEY
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

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
        arguments?.takeIf { it.containsKey(KEY) }?.apply {
            viewModel.department.value = getString(KEY).toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUsersListBinding.inflate(inflater, container, false)
        binding.usersList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }

        viewModel.filter.observe(viewLifecycleOwner) {
            if (!viewModel.isOnline(requireContext()) || viewModel.refreshUsers.value is NetworkResponse.Error) {
                findNavController().navigate(R.id.errorFragment)
            } else if (viewModel.queryFlow.value.isNotEmpty()) {
                showEmptyResponse()
            } else {
                usersAdapter.submitList(it)
                hideEmptyResponse()
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

        val filterItem = menu.findItem(R.id.choice)
        viewLifecycleOwner.lifecycleScope.launch {
            val key = arguments?.get(KEY).toString()
            if (viewModel.checkFilter(key)) filterItem.changeTrueItemColor(requireContext())
            else filterItem.changeFalseItemColor(requireContext())
        }

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
        val key = arguments?.get(KEY).toString()
        return when (item.itemId) {
            R.id.choice -> {
                val dialog = BottomSheetDialog(requireContext())
                val view = layoutInflater.inflate(R.layout.bottom_filter, null)

                val dialogFilterByAbc = view.findViewById<ImageView>(R.id.filterByAbc)
                val dialogFilterByBirthday = view.findViewById<ImageView>(R.id.filterByBirthday)

                viewLifecycleOwner.lifecycleScope.launch {
                    if (viewModel.checkFilter(key)) {
                        dialogFilterByBirthday.selectTrueFilterIcon(requireContext())
                        dialogFilterByAbc.selectFalseFilterIcon(requireContext())
                    } else {
                        dialogFilterByBirthday.selectFalseFilterIcon(requireContext())
                        dialogFilterByAbc.selectTrueFilterIcon(requireContext())
                    }
                }

                dialogFilterByAbc.setOnClickListener {
                    viewModel.saveFilterMode(key, false)
                    item.changeFalseItemColor(requireContext())
                    viewModel.sortOrder.value = SortOrder.BY_NAME
                    dialog.dismiss()
                }
                dialogFilterByBirthday.setOnClickListener {
                    viewModel.saveFilterMode(key, true)
                    viewModel.sortOrder.value = SortOrder.BY_DATA
                    item.changeTrueItemColor(requireContext())
                    dialog.dismiss()
                }
                dialog.setContentView(view)
                dialog.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showEmptyResponse() {
        usersAdapter.submitList(emptyList())
        binding.holderErrorMessage.visibility = View.VISIBLE
    }

    private fun hideEmptyResponse() {
        binding.holderErrorMessage.visibility = View.INVISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}