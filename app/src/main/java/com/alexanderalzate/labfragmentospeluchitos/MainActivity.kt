package com.alexanderalzate.labfragmentospeluchitos

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.versionedparcelable.ParcelUtils

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,Comunicador {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var recyclerView: RecyclerView
    private lateinit var peluche : Peluchito
    private lateinit var pelucheEliminar:String
    private var listPeluches:ArrayList<Peluchito> = ArrayList()
    private var quitar:Boolean = false

    var peluchito:ArrayList<Peluchito> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        val manager = supportFragmentManager
        val transaction =  manager.beginTransaction()
        val agregarFragment = AgregarFragment()
        transaction.add(R.id.contenedor,agregarFragment).commit()
    }



    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        when(p0.itemId){
            R.id.nav_agregar->{
                val agregarFragment = AgregarFragment()
                transaction.replace(R.id.contenedor,agregarFragment).commit()
            }
            R.id.nav_buscar->{
                val buscarFragment = BuscarFragment()
                transaction.replace(R.id.contenedor,BuscarFragment.newInstance(listPeluches)).commit()
            }
            R.id.nav_eliminar->{
                val eliminarFragment = EliminarFragment()
                transaction.replace(R.id.contenedor,eliminarFragment).commit()
            }
            R.id.nav_inventario->{
                val inventarioFragment = InventarioFragment()
                transaction.replace(R.id.contenedor,InventarioFragment.newInstance(listPeluches)).commit()
            }

        }
        val drawerLayout:DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun enviarDatos(id: String, nombre: String, cantidad: String, precio: String) {

        if(id!="" && nombre!="" && cantidad!="" && precio!=""){
        peluche = Peluchito(id, nombre, cantidad, precio)
        listPeluches.add(peluche)
        Toast.makeText(this,"Has Agregado el peluchito $nombre",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"Uno o más campos están vacíos",Toast.LENGTH_SHORT).show()
        }

    }

    override fun enviarEliminar(nombre: String) {

        var cont = 0
        if(nombre!=""){
            for (peluche in listPeluches) {
                cont++
                if (peluche.nombre == nombre) {
                    Toast.makeText(this, "Has eliminado el peluchito $nombre", Toast.LENGTH_SHORT).show()
                    listPeluches.remove(peluche)
                    break
                }
            }
            if(cont>=listPeluches.size){
                Toast.makeText(this, "Nombre del peluchito incorrecto", Toast.LENGTH_SHORT).show()
            }

        }else {
            Toast.makeText(this, "Nombre del peluchito incorrecto", Toast.LENGTH_SHORT).show()
        }
    }


}