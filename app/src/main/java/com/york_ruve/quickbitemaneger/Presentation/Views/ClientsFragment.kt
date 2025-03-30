package com.york_ruve.quickbitemaneger.Presentation.Views

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.york_ruve.quickbitemaneger.Domain.Model.Clients
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.clientsAdapter
import com.york_ruve.quickbitemaneger.Presentation.ViewModels.clientsViewModel
import com.york_ruve.quickbitemaneger.Presentation.utils.OnClientClickListener
import com.york_ruve.quickbitemaneger.R
import com.york_ruve.quickbitemaneger.databinding.DialogEditClientBinding
import com.york_ruve.quickbitemaneger.databinding.FragmentClientsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientsFragment : Fragment(), OnClientClickListener {

    private lateinit var binding: FragmentClientsBinding

    private val clientsViewModel: clientsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setListeners()
    }

    private fun setListeners() {
        binding.btnAddClient.setOnClickListener {
            val client = Clients(null,"","","","")
            clientsViewModel.addClient(client)
            clientsViewModel.loadClients()
            Toast.makeText(this.context, requireContext().getString(R.string.ins_client), Toast.LENGTH_SHORT).show()
        }

    }

    private fun initRecyclerView() {
        clientsViewModel.loadClients()
        clientsViewModel.clients.observe(viewLifecycleOwner){
            binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
            binding.recyclerView.adapter = clientsAdapter(it,this)
        }
    }

    override fun onEditClientClick(client: Clients) {
        val dialog = Dialog(requireContext())
        val binding = DialogEditClientBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        binding.etName.setText(client.nombre)
        binding.etAddress.setText(client.direccion)
        binding.etPhone.setText(client.telefono)
        binding.etEmail.setText(client.email)
        binding.btnSaveIngredient.setOnClickListener {
            val clientUpdate = Clients(client.id,binding.etName.text.toString(),binding.etAddress.text.toString(),binding.etPhone.text.toString(),binding.etEmail.text.toString())
            clientsViewModel.updateClient(clientUpdate)
            clientsViewModel.loadClients()
            dialog.dismiss()
        }
        binding.ivClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onDeleteClientClick(client: Clients) {
        clientsViewModel.deleteClient(client)
        clientsViewModel.loadClients()
    }


}