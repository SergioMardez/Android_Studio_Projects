package com.example.sergio.drawerrecyclercardview.activities


import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.TextView
import com.example.mylibrary.ToolbarActivity
import com.example.sergio.drawerrecyclercardview.R
import com.example.sergio.drawerrecyclercardview.fragments.ArrivalsFragment
import com.example.sergio.drawerrecyclercardview.fragments.DeparturesFragment
import com.example.sergio.drawerrecyclercardview.fragments.HomeFragment
import com.example.sergio.drawerrecyclercardview.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ToolbarActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarToLoad(toolbar as Toolbar)

        setNavDrawer()
        setUserHeaderInfo()

        //onCreate se vuelve a llamar cuando se gira el telefono. Para que no recargue lo que se tenga
        //hay que ver si está vacío. En caso de estar vacío es la primera vez que se llama. En las siguientes
        // saveInstanceState ya no está vacío
        if(savedInstanceState == null) {
            fragmentTransaction(HomeFragment())
            navView.menu.getItem(0).isChecked = true
        }
    }

    //Para configurar el navigation drawer: implementation 'com.android.support:design:28.0.0'
    private fun setNavDrawer() {
        //Aquí es donde se pone el icono de la hambuguesa que abre y cierra el drawer
        val toggle = ActionBarDrawerToggle(this, drawerLayout, _toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    //Para cambiar de fragment
    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun loadFragmentByid(id: Int) {
        when (id) {
            R.id.nav_Home -> fragmentTransaction(HomeFragment())
            R.id.nav_Arrivals -> fragmentTransaction(ArrivalsFragment())
            R.id.nav_Departures -> fragmentTransaction(DeparturesFragment())
        }
    }

    private fun showMessageNavItemSelectedById(id: Int) {
        when (id) {
            R.id.nav_prof -> toast("Profile")
            R.id.nav_settings -> toast("Settings")
        }
    }

    //Poner los datos en los textView de la cabecera
    private fun setUserHeaderInfo() {
        val name = navView.getHeaderView(0).findViewById<TextView>(R.id.textViewName)
        val email = navView.getHeaderView(0).findViewById<TextView>(R.id.textViewEmail)

        name?.let { name.text = getString(R.string.user_name) }
        email?.let { email.text = getString(R.string.user_email) }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        showMessageNavItemSelectedById(item.itemId)
        loadFragmentByid(item.itemId)
        drawerLayout.closeDrawer(GravityCompat.START) //Para que se cierre al pinchar sobre un item del menu
        return true
    }

    //Se cierra el panel lateral si está abierto al pulsar la tecla back. Si se pulsa otra vez se sale de la app
    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
