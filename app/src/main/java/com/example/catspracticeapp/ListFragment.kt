package com.example.catspracticeapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catspracticeapp.adapter.RecyclerViewAdapter
import com.example.catspracticeapp.databinding.AddCatsItemBinding
import com.example.catspracticeapp.databinding.FragmentListBinding
import com.example.catspracticeapp.db.CatEntity
import com.example.catspracticeapp.view_model.CatListViewModel
import java.lang.Exception

class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    private lateinit var catListViewModel: CatListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        catListViewModel = ViewModelProvider(this).get(CatListViewModel::class.java)

        //RecyclerView
        val recyclerViewAdapter = RecyclerViewAdapter()
        val recyclerView = binding.recyclerView

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        catListViewModel.catList.observe(viewLifecycleOwner){
            recyclerViewAdapter.submitList(it)
        }
        recyclerViewAdapter.onCatItemClickListener = { it ->
            deleteItem(it)
        }
        readList(recyclerViewAdapter)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imButtonAdd.setOnClickListener { onAddPressed() }
        binding.imCatsInfo.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, CatsInfoFragment())
                .addToBackStack(null)
                .commit()

        }
    }
    private fun readList(recyclerViewAdapter: RecyclerViewAdapter){
        catListViewModel.catList.observe(viewLifecycleOwner){
            recyclerViewAdapter.setData(it)
        }
     }

    private fun onAddPressed() {
        val dialogBinding = AddCatsItemBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add Cat")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") {d, whitch ->
                val name = dialogBinding.edCatName.text.toString()
                val description = dialogBinding.edCatDescription.text.toString()
                if (name.isNotBlank()){
                    createCat(name,description)
                }
            }
            .create()
        dialog.show()
    }


    private fun deleteItem(catEntity: CatEntity) {
        val listener = DialogInterface.OnClickListener { dialog, which ->
            if (which == DialogInterface.BUTTON_POSITIVE){
                catListViewModel.deleteCatById(catEntity)
            }
        }
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Delete cat")
            .setMessage("Delete this cat ?")
            .setPositiveButton("Ok", listener)
            .setNegativeButton("No", listener)
            .create()
        dialog.show()
    }
    private fun createCat(name : String, description: String){
        val cat = CatEntity(
            id = 0,
            name = name,
            description = description
        )
        try{

            catListViewModel.addCat(cat)
            Toast.makeText(requireContext(), "Added completed", Toast.LENGTH_SHORT).show()
        }catch(e:Exception){
            Toast.makeText(requireContext(), "Added completed", Toast.LENGTH_SHORT).show()
        }
    }
    companion object{
        const val KEY_TITLE = "title"
        const val KEY_DESCRIPTION = "description"
    }
}
