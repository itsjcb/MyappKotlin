package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class HomeActivity : AppCompatActivity() {
    lateinit var lvPost: ListView
    var postArray = ArrayList<Post>()
    lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //val tvHello = findViewById<TextView>(R.id.tvHello)

        //--Get the email sent by MainActivity
        val email = intent.getStringExtra("email")
        //--Display the mail @
        // tvHello.text = "Welcome : $email"

        lvPost = findViewById<ListView>(R.id.lvPosts)
        //object to represent in the lv
        postArray = arrayListOf(
            Post("Post1","A Description 1 of Post 1 must be shown here instead of nothing. " +
                    "What is your favourite language.", R.drawable.image1),
            Post("Post2","A Description 2 of Post 2 must be shown here instead of nothing. " +
                    "What is your favourite language.", R.drawable.image2),
            Post("Post3","A Description 3 of Post 3 must be shown here instead of nothing. " +
                    "What is your favourite language.", R.drawable.image3),
            Post("Post4","A Description 4 of Post 4 must be shown here instead of nothing. " +
                    "What is your favourite language.", R.drawable.image4),
            Post("Post5","A Description 5 of Post 5 must be shown here instead of nothing. " +
                    "What is your favourite language.", R.drawable.image5),
        )
        adapter = PostsAdapter(this, R.layout.item_post, postArray)
        lvPost.adapter = adapter

        lvPost.setOnItemClickListener{ adapterView , view, position, id ->
            // retrieve the position passed in param
            val clickPost = postArray[position]
            // Toast.makeText(this, "Position: $position", Toast.LENGTH_LONG).show()
            Intent(this, PostDetailsActivity::class.java).also{
                it.putExtra("title", clickPost.title)
                startActivity(it)

            }
        }
        // - 1st step for ContextMenu :
        // register all items for a context menu
        registerForContextMenu(lvPost)
    }
    //Implementation of the onCreateContextMenu() method
    override fun onCreateOptionsMenu(menu: Menu?): Boolean  {
        //change xml menu view to menu view
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    //when the user selects a menu item, the system calls this method
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemAdd->{
                    Toast.makeText(this, "Add clicked!", Toast.LENGTH_SHORT).show()
            }
            R.id.itemSetting->{
                Toast.makeText(this, "Setting clicked!", Toast.LENGTH_SHORT).show()
            }
            R.id.itemLogout->{
                //finish()
                // show alertDialog
                showLogoutConfirmationDialog()
            }
        }

        return super.onOptionsItemSelected(item)
    }
    // method AlertDialog
    fun showLogoutConfirmationDialog(){
        // builder constructs the alertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation!")
        builder.setMessage("Are you sure that you want to leave ?")
        builder.setPositiveButton("Yes") { dialogInterface, id ->
            // in logging out: call sharedpref and open it in mode edit
            val editor = this.getSharedPreferences("app_state", Context.MODE_PRIVATE).edit()
            editor.remove("is_authentificated").apply()
            //exit the app
            finish()
        }
        builder.setNegativeButton("No") { dialogInterface, id ->
            dialogInterface.dismiss()
        }
        builder.setNeutralButton("Cancel") { dialogInterface, id ->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
/*
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.list_context_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }
    //when the user selects a menu item, the system calls this method
    override fun onContextItemSelected(item: MenuItem): Boolean {
        // callback when a context menu is brought up for this AdapterView.
        val info: AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        // find the position clicked from the context menu
        val position: Int = info.position
        when (item.itemId) {
            R.id.itemShow ->{
                //Toast.makeText(this, "show: $position", Toast.LENGTH_SHORT).show()
                Intent(this, PostDetailsActivity::class.java).also{
                    it.putExtra("title", postArray[position].title)
                    startActivity(it)
                }
            }
            R.id.itemDelete ->{
                //Toast.makeText(this, "delete: $position", Toast.LENGTH_SHORT).show()
                // delete at that selected position
                postArray.removeAt(position)
                // notify the changes by refreshing
                adapter.notifyDataSetChanged()
            }
        }
        return super.onContextItemSelected(item)

    }
 */
}