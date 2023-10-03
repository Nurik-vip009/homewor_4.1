package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.ui.home.adapter.TaskAdapter
import com.example.taskmanager.ui.task.model.Task
import com.example.taskmanager.ui.utils.App

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val adapter = TaskAdapter(this::OnLongClick)

    private fun OnLongClick(task: Task) {
        onLongClick(task)
    }


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        setData()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun onLongClick(task: Task) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setMessage(getString(R.string.delete_title))
            .setTitle(getString(R.string.delete_message))
            .setNegativeButton(getString(R.string.no)
            ) { dialog, _ -> dialog?.cancel() }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                App.db.taskDao().delete(task)
                setData()
            }
            .show()
    }

    private fun setData() {
        val tasks = App.db.taskDao().getAll()
        adapter.addData(tasks)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}