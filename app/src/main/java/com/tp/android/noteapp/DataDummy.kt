package com.tp.android.noteapp

object DataDummy {
    private val data = ArrayList<NoteEntity>()

    fun generateNote(): List<NoteEntity>{
        data.add(
            NoteEntity(
            "0", "Pramuka", "Latihan tiap minggu persiapan LT 2021", "Tiap sabtu"
        ))
        data.add(NoteEntity(
            "1", "Belajar", "Persiapan UN 2021", "12 April 2021"
        ))
        data.add(NoteEntity(
            "2", "Gym", "Latihan Di Gym AA Gym", "Tiap Sabtu"
        ))
        data.add(
            NoteEntity(
                "3", "Main ps", "Level 15 untuk minggu ini", "Tiap Minggu"
            ))
        data.add(NoteEntity(
            "4", "Kerumah anis", "Persiapan Startup", "Maret 2021"
        ))
        data.add(NoteEntity(
            "5", "Rekreasi", "Gunung toba dan sungai Merbabu", "3 Januari"
        ))
        return data
    }
}