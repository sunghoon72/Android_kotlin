package com.example.fecth_test


class DataModel {
    var id: Int? = null
    var listId: String? = null

    var name: String? = null


    fun getIds(): Int {
        return id!!
    }

    fun setIds(id: Int) {
        this.id = id
    }

    fun getListIds(): String {
        return listId.toString()
    }

    fun setListIds(listId: String) {
        this.listId = listId
    }


    fun getNames(): String {
        return name.toString()
    }

    fun setNames(name: String) {
        this.name = name
    }

}