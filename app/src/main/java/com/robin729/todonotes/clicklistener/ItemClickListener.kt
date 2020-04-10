package com.robin729.todonotes.clicklistener

import com.robin729.todonotes.db.Notes

interface ItemClickListener {
    fun onClick(notes: Notes)
    fun onUpdate(notes: Notes)
}