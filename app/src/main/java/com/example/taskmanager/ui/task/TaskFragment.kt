package com.example.taskmanager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.ui.task.model.Task
import com.example.taskmanager.ui.utils.App

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener{
            if (binding.textInputEt2.text.toString().isNotEmpty()){
                findNavController().navigateUp()
            }else{
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                binding.textInputEt2.error = "ERROR"
                return@setOnClickListener
            }
            val data = Task(
                title = binding.textInputEt2.text.toString(),
                desc = binding.textInputEt3.text.toString()
            )
            App.db.taskDao().insert(data)
            setFragmentResult(RESULT_KEY, bundleOf(TASK_KEY to data))
            findNavController().navigateUp()
        }
    }
    companion object{
        const val RESULT_KEY = "result.key"
        const val TASK_KEY = "task.key"
    }
}