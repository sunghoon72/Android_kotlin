package com.example.fecth_test


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(private val context: Context, private val dataModelArrayList: List<DataModel>) : BaseAdapter() {

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return dataModelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return dataModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.item, null, true)



            holder.tvid = convertView.findViewById<View>(R.id.id) as TextView
            holder.tvlistid = convertView.findViewById<View>(R.id.listId) as TextView
            holder.tvname = convertView.findViewById<View>(R.id.name) as TextView

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }
        holder.tvid!!.text = "Id: " + dataModelArrayList[position].getIds()

        holder.tvlistid!!.text = "ListId: " + dataModelArrayList[position].getListIds()

        holder.tvname!!.text = "Name: " + dataModelArrayList[position].getNames()


        return convertView
    }

    private inner class ViewHolder {

        var tvname: TextView? = null
        var tvlistid: TextView? = null
        var tvid: TextView? = null
    }

}