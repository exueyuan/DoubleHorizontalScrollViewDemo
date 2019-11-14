package com.duyi.horizontalscrollviewdemo.autorecycler


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.duyi.horizontalscrollviewdemo.R
import com.duyi.horizontalscrollviewdemo.autorecycler.view.DoubleAutoRecyclerView


class AutoRecyclerRecyclerActivity : AppCompatActivity() {

    val list = arrayListOf<BaseData>()
    val adapter = MyAdapter(list)
    lateinit var darcv_recycler: DoubleAutoRecyclerView<BaseData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_recycler_recycler)
        darcv_recycler = findViewById(R.id.darcv_recycler)


        darcv_recycler.setDoubleAutoRecyclerAdapterAdapter(adapter)

        for (i in 0..10) {
            list.add(MyData("item:::$i"))
        }
        adapter.notifyDataSetChanged()
    }
}
