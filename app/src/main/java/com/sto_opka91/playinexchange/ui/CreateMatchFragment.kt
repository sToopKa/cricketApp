package com.sto_opka91.playinexchange.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sto_opka91.playinexchange.R
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.databinding.FragmentCreateMatchBinding
import com.sto_opka91.playinexchange.databinding.FragmentMatchesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class CreateMatchFragment : Fragment() {

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val savedPath = copyUriToInternalStorage(requireContext(), uri)
            uriImage = savedPath
            binding.cvImage.visibility = View.VISIBLE
            binding.imPicture.setImageURI(Uri.fromFile(File(savedPath)))
        }
    }
    private var uriImage = ""

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentCreateMatchBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateMatchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.cvAddFoto.setOnClickListener {
            pickImage.launch("image/*")
        }

        binding.btnCreate.setOnClickListener {
            val location = binding.edLocation.text.toString()
            val date = binding.edDateTime.text.toString()
            val price = binding.edContribution.text.toString()

            if(location==""||date==""||price==""){

            }else{
                val match = Match(null, null,location,date, price,uriImage, "My team vs Friends")
                lifecycleScope.launch {
                    viewModel.saveMatch(match)
                    delay(300)
                    findNavController().popBackStack()
                }

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun copyUriToInternalStorage(context: Context, uri: Uri): String {
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.filesDir, "match_image_${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
        return file.absolutePath
    }

}