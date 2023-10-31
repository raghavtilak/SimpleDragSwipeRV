package com.raghav.simpledragswiperv

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raghav.library.DragSwipeAdapter.OnItemClick

class MainActivity : AppCompatActivity(), OnItemClick<UserModel?> {
    private var recyclerView: RecyclerView? = null
    private var listAdapter: RVAdapter? = null
    private var userList: MutableList<UserModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        userList = ArrayList()
        loadData()
        listAdapter = RVAdapter(
            this, userList, R.layout.item_layout,
            recyclerView,
            true, false, RVAdapter.VERTICAL,
            16, this
        )
    }

    private fun loadData() {
        userList?.apply {
            add(UserModel("Raghav", "8619146260", "India"))
            add(UserModel("Jack", "524575", "UK"))
            add(UserModel("Tilak", "995036482", "India"))
            add(UserModel("Monica", "514894", "USA"))
            add(UserModel("Hayat", "+1248546", "Istanbul"))
        }
    }

    override fun onClick(view: View?, position: Int, item: UserModel?) {
        Toast.makeText(
            this, """
     $position
     ${item?.name}
     """.trimIndent(), Toast.LENGTH_SHORT
        ).show()
    }
}