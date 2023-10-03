package com.example.taskmanager.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.ui.utils.loadImage
import com.google.android.material.textfield.TextInputLayout


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val galleryLauncher =
        registerForActivityResult<Intent, androidx.activity.result.ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result: androidx.activity.result.ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK
                && result.data != null
            ) {
                val photoUri = result.data?.data
                pref.saveImage(photoUri.toString())
                binding.profileImage.loadImage(photoUri.toString())
            }
        }

    private val pref:Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveAndGetName()
    }

    private fun saveAndGetName() {
        binding.profileImage.loadImage(pref.getImage().toString())
        binding.textInputEt.addTextChangedListener {
            pref.saveName(binding.textInputEt.text.toString())
            binding.profileImage.loadImage(pref.getImage().toString())
        }
        binding.profileImage.setOnClickListener { view ->
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryLauncher.launch(intent)
            }
        }
    }
