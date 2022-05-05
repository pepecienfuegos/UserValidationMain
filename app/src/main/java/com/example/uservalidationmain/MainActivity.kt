package com.example.uservalidationmain

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.uservalidationmain.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    lateinit var autenticacion: FirebaseAuth//
    lateinit var autenticacion2: FirebaseFirestore//


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        autenticacion = FirebaseAuth.getInstance() //
        autenticacion2 = FirebaseFirestore.getInstance() //


        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onStart(){
        super.onStart()
        val user: FirebaseUser? = autenticacion.currentUser

        if(user == null){
            //Mandarlo al login
            // Navigation.findNavController(view).navigate(R.id.nav_slideshow)
            Navigation.createNavigateOnClickListener(R.id.nav_gallery)


        }else{
            //Avanzar a la pantalla principal
            Navigation.createNavigateOnClickListener(R.id.nav_home)


        }
    }
    fun registrarse() {
        val nombre: String = findViewById<EditText>(R.id.nombreTxt).text.toString()
        val telefono: String = findViewById<EditText>(R.id.telefonoTxt).text.toString()
        val correo: String = findViewById<EditText>(R.id.correoTxt).text.toString()
        val password: String = findViewById<EditText>(R.id.passwordTxt).text.toString()
        autenticacion.createUserWithEmailAndPassword(correo, password)

        var

    }

    fun login(){

        val correo: String = findViewById<EditText>(R.id.correoTxt).text.toString()
        val password: String = findViewById<EditText>(R.id.passwordTxt).text.toString()
        if(correo == null){
            Toast.makeText(this, "Completa el capo usuario", Toast.LENGTH_SHORT).show()

        }else if(password == null){

            Toast.makeText(this, "Completa el capo password", Toast.LENGTH_SHORT).show()

        }else{
            autenticacion.signInWithEmailAndPassword(correo, password).addOnCompleteListener{
                Toast.makeText(this, "Usuario previamente registrado", Toast.LENGTH_SHORT).show()

                if(it.isSuccessful){
                    Toast.makeText(this, "Usuario previamente registrado", Toast.LENGTH_SHORT).show()
                    findViewById<TextView>(R.id.textView).text = "SIIIIIIUUUUU"

                }else{
                    Toast.makeText(this, "Usuario previamente NO registrado", Toast.LENGTH_SHORT).show()
                    findViewById<TextView>(R.id.textView).text = "NOOOOOOUUUU"

                }
            }
        }



    }

    fun verificar2(view: View) {
        login()
    }

    fun onclics(view: View) {
        registrarse()
    }


}