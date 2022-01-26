package com.example.appkode.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.appkode.R
import com.example.appkode.databinding.FragmentDetailsBinding
import com.example.appkode.domain.User


class DetailsFragment : Fragment(R.layout.fragment_details) {

    val args  by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bind = FragmentDetailsBinding.bind(view)
        bind.teedededxt.text = args.user.department
    }

}
// @BindingAdapter("onRecipeClickListener")
//        @JvmStatic
//        fun onRecipeClickListener(recipeRowLayout: ConstraintLayout, result: Result){
//            recipeRowLayout.setOnClickListener{
//                try {
//                    val action = RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(result)
//                    recipeRowLayout.findNavController().navigate(action)
//                }catch (e: Exception){
//                    Log.d("onRecipesClickListener", e.toString())
//                }
//            }
//        }