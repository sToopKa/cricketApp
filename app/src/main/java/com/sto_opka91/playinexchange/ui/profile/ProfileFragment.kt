package com.sto_opka91.playinexchange.ui.profile

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sto_opka91.playinexchange.R
import com.sto_opka91.playinexchange.data.room.ArticleEntity
import com.sto_opka91.playinexchange.data.room.UserEntity
import com.sto_opka91.playinexchange.databinding.FragmentMainBinding
import com.sto_opka91.playinexchange.databinding.FragmentProfileBinding
import com.sto_opka91.playinexchange.databinding.InformLayoutBinding
import com.sto_opka91.playinexchange.ui.MainViewModel
import com.sto_opka91.playinexchange.utils.LIST_Article
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val savedPath = copyUriToInternalStorage(requireContext(), uri)
            uriImage = savedPath
            Glide.with(requireContext())
                .load(uri)
                .circleCrop()
                .into(binding.imFace)
        }
    }
    private var uriImage = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUser()
        observeViewModel()
        binding.btnBack.setOnClickListener {
            val name = binding.edName.text.toString()
            val tg = binding.edTg.text.toString()

            val user = UserEntity(null, name, tg, uriImage)
            viewModel.clearUser()
            viewModel.saveUser(user)

            findNavController().popBackStack()
        }

        binding.imFace.setOnClickListener {
            pickImage.launch("image/*")
        }

        binding.edName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Не нужно
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Не нужно
            }

            override fun afterTextChanged(s: Editable?) {
                binding.tvName.text = s.toString()
            }
        })

        binding.edTg.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Не нужно
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Не нужно
            }

            override fun afterTextChanged(s: Editable?) {
                binding.tvTg.text = "@"+s.toString()
            }
        })

        binding.ivGoPrivacy.setOnClickListener {
            val url ="https://www.google.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.tvTg.setOnClickListener {
            val url ="https://web.telegram.org/k/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.ivGoApp.setOnClickListener {
            val bindingAlert = InformLayoutBinding.inflate(LayoutInflater.from(context))
            val mDialogBuilder = android.app.AlertDialog.Builder(context)
            mDialogBuilder.setView(bindingAlert.root)

            val alertDialog = mDialogBuilder.create()
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            bindingAlert.Image2.setOnClickListener { alertDialog.cancel() }
            alertDialog.show()
        }



        binding.tvTg.text = "@SalemSgt"
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

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.settingsState.collect { state ->
                if(state.user != null && state.user.isNotEmpty()) {

                    val user = state.user[0]
                    Log.d("myLog", user.toString())
                    if(user.photoUri != "") {
                        uriImage = user.photoUri
                        Glide.with(requireContext())
                            .load(user.photoUri)
                            .circleCrop()
                            .into(binding.imFace)
                    }
                    if(user.name.isNotEmpty()){
                        binding.edName.setText(user.name)
                    }
                    if(user.tg.isNotEmpty()){
                        binding.edTg.setText(user.tg)
                    }




                }
            }
        }
    }

}