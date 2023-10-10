package com.example.taskmanager.ui.auth.phone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentPhoneBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneFragment : Fragment() {

    private lateinit var binding: FragmentPhoneBinding
    private val auth:FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentPhoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicker()
    }

    private fun initClicker() {
        binding.btnSendCode.setOnClickListener {
            val phone = binding.etPhone.prefixText.toString() + binding.textInputEt3.text.toString()
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phone)
                .setTimeout(60L,TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(callbackAnswer)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    private val callbackAnswer = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {}

        override fun onVerificationFailed(p0: FirebaseException) {
            Toast.makeText(requireContext(), "error:$p0", Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(verficationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            findNavController().navigate(R.id.codeFragment, bundleOf("VERIFY_KEY" to verficationId))
        }

    }
}