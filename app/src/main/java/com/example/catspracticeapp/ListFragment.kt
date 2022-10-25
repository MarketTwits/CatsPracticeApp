package com.example.catspracticeapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.example.catspracticeapp.databinding.AddCatsItemBinding
import com.example.catspracticeapp.databinding.FragmentListBinding

import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    private lateinit var arrayAdapter: ArrayAdapter<Cat>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListWithArrayAdapter()
        binding.imButtonAdd.setOnClickListener { onAddPressed() }
        binding.imCatsInfo.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, CatsInfoFragment())
                .addToBackStack(null)
                .commit()

        }
    }

    private fun setupListWithArrayAdapter() {
        val data = mutableListOf(
            Cat(UUID.randomUUID().toString(), "Maga"),
            Cat(UUID.randomUUID().toString(), "Ivan"),
            Cat(UUID.randomUUID().toString(), "Loren"),
            Cat(UUID.randomUUID().toString(), "Mask"),
        )
        arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.cats_item_list,
            R.id.tvCatsName,
            data
        )
        binding.listView.adapter = arrayAdapter

        binding.listView.setOnItemClickListener { parent, view, postition, it ->
            deleteCharecter(arrayAdapter.getItem(postition)!!)
        }
    }
    private fun onAddPressed() {
        val dialogBinding = AddCatsItemBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add Cat")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") {d, whitch ->
                val name = dialogBinding.edCatName.text.toString()
                if (name.isNotBlank()){
                    createCat(name)
                }
            }
            .create()
        dialog.show()
    }

    private fun deleteCharecter(cat: Cat) {
        val listener = DialogInterface.OnClickListener { dialog, which ->
            if (which == DialogInterface.BUTTON_POSITIVE){
                arrayAdapter.remove(cat)
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
    private fun createCat(name : String){
        val cat = Cat(
            id = UUID.randomUUID().toString(),
            name = name
        )
        arrayAdapter.add(cat)
    }
    companion object{
        const val KEY_TITLE = "title"
        const val KEY_DESCRIPTION = "description"
    }
}
