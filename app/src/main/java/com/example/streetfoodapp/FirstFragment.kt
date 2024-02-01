package com.example.streetfoodapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.streetfoodapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
import android.content.Intent
class FirstFragment : Fragment() {

    private lateinit var adapter: MyAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodArrayList: ArrayList<Food>

    private lateinit var imageId: Array<Int>
    private lateinit var names: Array<String>
    private lateinit var prix: Array<Float>

    private var _binding: FragmentFirstBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize();
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MyAdapter(foodArrayList)
        recyclerView.adapter= adapter
        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
        adapter.onItemClick={
            val intent = Intent(requireContext(), DetailsActivity::class.java)
            intent.putExtra("food",it)
            startActivity(intent)
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun dataInitialize(){
        foodArrayList = arrayListOf<Food>()
        imageId = arrayOf(
            R.drawable.pizza_fruits_de_mer,
            R.drawable.pizza_neptune,
            R.drawable.pizza_pepperoni,
            R.drawable.makloub_chawarma,
            R.drawable.makloub_escalope,
            R.drawable.tacos_chawarma,
            R.drawable.tacos_cordon,
            R.drawable.tacos_escalope,
        )
        names = arrayOf(
            "pizza fruits de mer",
            "pizza neptune",
            "pizza pepperoni",
            "makloub chawarma",
            "makloub escalope",
            "tacos chawarma",
            "tacos cordon bleu",
            "tacos escalope"
        )
        prix = arrayOf(
            20f,
            15f,
            18f,
            9f,
            8f,
            10f,
            10f,
            10f
        )
        for(i in imageId.indices){
            val food = Food(imageId[i],names[i],prix[i])
            foodArrayList.add(food)
        }


    }



}