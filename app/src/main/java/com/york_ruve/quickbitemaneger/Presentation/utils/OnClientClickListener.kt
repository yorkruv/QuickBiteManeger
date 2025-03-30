package com.york_ruve.quickbitemaneger.Presentation.utils

import com.york_ruve.quickbitemaneger.Domain.Model.Clients

interface OnClientClickListener {
    fun onEditClientClick(client: Clients)
    fun onDeleteClientClick(client: Clients)
}