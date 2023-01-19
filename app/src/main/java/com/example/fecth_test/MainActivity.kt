package com.example.fecth_test

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.ListAdapter
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val URLstring = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
    private var listView: ListView? = null
    internal lateinit var dataModelArrayList: ArrayList<DataModel>
    private var listAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.fetch_data)

        retrieveJSON()

    }

    private fun retrieveJSON() {

        showSimpleProgressDialog(this, "Loading...", "Fetching Data", false)


        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            URLstring,
            null,
            Response.Listener<JSONArray>
            {
                    response ->
                Log.d("MainActivity", ">>$response")
                dataModelArrayList = ArrayList()
                for(i in 0 until response.length()){

                    val jsonObject = response[i] as JSONObject

                    if(jsonObject.getString("name") != null && jsonObject.getString("name") !="" && jsonObject.getString("name") !="null"){
                        val playerModel = DataModel()
                        playerModel.setIds(jsonObject.getInt("id"))
                        playerModel.setListIds(jsonObject.getString("listId"))
                        playerModel.setNames(jsonObject.getString("name"))

                        dataModelArrayList.add(playerModel)
                    }
                }



                setupListview()
            },
            {
                    error ->
                Log.d("MainActivity", "http connection6")
            })


        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonArrayRequest)


    }

    private fun setupListview() {
        removeSimpleProgressDialog()  //will remove progress dialog
        val dataModelArrayListTest = dataModelArrayList.sortedWith(compareBy({it.listId},{it.id}))

        listAdapter = ListAdapter(this, dataModelArrayListTest)
        listView!!.adapter = listAdapter
    }

    companion object {
        private var mProgressDialog: ProgressDialog? = null

        fun removeSimpleProgressDialog() {
            try {
                if (mProgressDialog != null) {
                    if (mProgressDialog!!.isShowing) {
                        mProgressDialog!!.dismiss()
                        mProgressDialog = null
                    }
                }
            } catch (ie: IllegalArgumentException) {
                ie.printStackTrace()

            } catch (re: RuntimeException) {
                re.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        fun showSimpleProgressDialog(
            context: Context, title: String,
            msg: String, isCancelable: Boolean
        ) {
            try {
                if (mProgressDialog == null) {
                    mProgressDialog = ProgressDialog.show(context, title, msg)
                    mProgressDialog!!.setCancelable(isCancelable)
                }

                if (!mProgressDialog!!.isShowing) {
                    mProgressDialog!!.show()
                }

            } catch (ie: IllegalArgumentException) {
                ie.printStackTrace()
            } catch (re: RuntimeException) {
                re.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}