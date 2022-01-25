package com.example.appkode.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.appkode.R
import com.example.appkode.databinding.FragmentErrorBinding
import com.example.appkode.di.appComponent
import com.example.appkode.util.NetworkResponse
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject


class ErrorFragment : Fragment(R.layout.fragment_error) {

    @Inject
    lateinit var usersViewModelFactory: Lazy<UsersViewModel.Factory>
    private val viewModel: UsersViewModel by viewModels { usersViewModelFactory.get() }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bind = FragmentErrorBinding.bind(view)
        bind.reloadBottom.setOnClickListener {
            lifecycleScope.launch { viewModel.getUser() }
            viewModel.refreshUsers.observe(viewLifecycleOwner){ refreshUsers ->
                when(refreshUsers){
                    is NetworkResponse.Success -> findNavController().navigate(R.id.homeViewPagerFragment)
                }
            }
        }
    }

}