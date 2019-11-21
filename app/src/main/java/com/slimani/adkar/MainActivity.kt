package com.slimani.adkar

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tikitadkar.view.*
import kotlinx.android.synthetic.main.tikitadkar.view.buadd
import kotlinx.android.synthetic.main.tikitadkar.view.tvnam

class MainActivity : AppCompatActivity() {

    private var adapter:myAdairAdapters?=null
    private var listdicr=ArrayList<dikre>()

    var tv0=0
    var tv1=0
    var tv2=0
    var tv3=0
    var tv4=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadDkr()

        adapter= this.myAdairAdapters(listdicr,this)
        LvAdkar.adapter= this.adapter
        val sharedPreference: SharedPreferences = getSharedPreferences("ListOfAthkar", Context.MODE_PRIVATE)

         tv0 = sharedPreference.getInt("0", 0)
         tv1 = sharedPreference.getInt("1", 0)
         tv2 = sharedPreference.getInt("2", 0)
         tv3 = sharedPreference.getInt("3", 0)
         tv4 = sharedPreference.getInt("4", 0)
    }




    private fun loadDkr(){
        listdicr.run {
            add(dikre(0,"المجموع"      ,tv0))
            add(dikre(1,"سبحان الله"   ,tv1))
            add(dikre(2,"الحمد لله"    ,tv2))
            add(dikre(3,"لا إله إلا الله",tv3))
            add(dikre(4,"الله أكبر"    ,tv4))
        }
    }


    inner class myAdairAdapters(listdikr: ArrayList<dikre>, context: Context) : BaseAdapter() {
         var listdikrlocal= listdikr
         var context:Context?= context


        @SuppressLint("ViewHolder", "InflateParams")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val note = listdikrlocal[position]
            val inflate = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val myView = inflate.inflate(R.layout.tikitadkar, null)
                myView.tvnam.text = note.nodeName
                myView.tvNamber.text = note.nodeNumber.toString()
                myView.buadd.setOnClickListener {
                    val sharedPreference: SharedPreferences = getSharedPreferences("ListOfAthkar", Context.MODE_PRIVATE)

                    val numberTv = note.nodeId.toString()
                    val tv = sharedPreference.getInt(numberTv, 0)
                    myView.tvNamber.text = tv.toString()


                    var nambertv1 = myView.tvNamber.text.toString().toInt()

                    nambertv1 += 1

                    myView.tvNamber.text = nambertv1.toString()


                    // add to data

                    val editier = sharedPreference.edit()
                    editier.putInt(numberTv, nambertv1)
                    editier.apply()


                }

                return myView

        }

        override fun getItem(position: Int): Any {
            return  listdikrlocal[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listdikrlocal.size
        }
    }
}
