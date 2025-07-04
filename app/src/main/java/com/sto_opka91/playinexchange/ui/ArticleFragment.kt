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
import androidx.navigation.fragment.findNavController
import com.sto_opka91.playinexchange.R
import com.sto_opka91.playinexchange.data.room.ArticleEntity
import com.sto_opka91.playinexchange.data.room.Match
import com.sto_opka91.playinexchange.databinding.FragmentArticleBinding
import com.sto_opka91.playinexchange.databinding.FragmentMatchesBinding
import com.sto_opka91.playinexchange.databinding.FragmentMyMatchesBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null

    private val binding get() = _binding!!

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
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
            val title = binding.edTitle.text.toString()
            val text = binding.edRequirements.text.toString()


            if(title==""||text==""){

            }else{
                val article = ArticleEntity(null, null,title,text, uriImage)
                viewModel.saveArticle(article)
                findNavController().popBackStack()
            }
        }

        viewModel.sharedVariable.observe(viewLifecycleOwner) { article ->
            if(article != null) {
                binding.edTitle.setText(article.title)
                binding.edRequirements.setText(article.text)
                binding.llAd.visibility = View.GONE
                binding.cvImage.visibility = View.VISIBLE
                if(article.picture!= null){
                    binding.imPicture.setImageResource(article.picture)
                }
                binding.btnCreate.visibility = View.GONE

            }
        }

        binding
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