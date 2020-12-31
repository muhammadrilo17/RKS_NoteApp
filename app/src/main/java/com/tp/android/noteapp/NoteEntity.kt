package com.tp.android.noteapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoteEntity (
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var date: String = "",
):Parcelable