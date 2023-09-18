package com.siddiqui.androidlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.siddiqui.androidlogin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    var userdata = mutableListOf<UserData>()
    var userdataAddList = mutableListOf<UserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        FirebaseApp.initializeApp(this)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onResume() {
        super.onResume()

        val database = FirebaseDatabase.getInstance()
        val itemsRefs = database.getReference("Android Login App")
        itemsRefs.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userdata.clear()
                userdataAddList.clear()
                Log.d("TAG", "onDataChange: " + snapshot.value)
                for (itemSnapshot in snapshot.children) {
                    val item = itemSnapshot.getValue(UserData::class.java)
                    item?.let { userdata.add(it) }
                }

                for (userDataList in userdata) {
                    binding.linearLayout.visibility = View.GONE
                    userdataAddList.add(
                        UserData(
                            userDataList.Name,
                            userDataList.Age,
                            userDataList.City
                        )
                    )

                }
                adapter = UserAdapter(userdataAddList)
                binding.recyclerView.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "failed to reload data!! ${error.message}")
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sortByName -> {
                adapter.sortByName()
                true
            }

            R.id.sortByAge -> {
                adapter.sortByAge()
                true
            }

            R.id.sortByCity -> {
                adapter.sortByCity()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}

