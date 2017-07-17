package exmcollapsing.test.com.kotlinhelloworld

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * Created by Boosal on 2017/7/13.
 */
class TestAdapter(private val mContext: Context,
                  private val list: MutableList<TestResponse.TypePhone>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View
        var holder:ViewHolder
        if(convertView==null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_lv,null)
            holder = ViewHolder(view)
            view.tag = holder
            Log.i("TAG","asd")
        }else{
            view = convertView;
            holder = view.tag as ViewHolder
        }
        holder.tv_1.text = list[position].title
        holder.tv_2.text = list[position].detail

        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.count()
    }

    inner class ViewHolder(var view:View) {
        var tv_1 = view.findViewById(R.id.tv_1) as TextView
        var tv_2 = view.findViewById(R.id.tv_2) as TextView
    }

    companion object {
        var a:Int = 2
        fun c():Int{
            return  a
        }
    }


}