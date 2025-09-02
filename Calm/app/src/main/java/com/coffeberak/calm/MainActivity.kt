package com.coffeberak.calm

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.coffeberak.calm.fragments.Diario
import com.coffeberak.calm.fragments.EspacioTerapeutico
import com.coffeberak.calm.fragments.ZonaActiva
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_ZonaActiva
        bottomNav.setOnItemSelectedListener(navListener)

        val selectedFragment: Fragment = ZonaActiva()

        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            selectedFragment
        ).commit()
    }

    private val navListener = NavigationBarView.OnItemSelectedListener {
        item: MenuItem ->
        val itemId = item.itemId /* obtain the selected item ID from your source */
        var selectedFragment: Fragment? = null

        if (itemId == R.id.nav_ZonaActiva) {
            selectedFragment = ZonaActiva()
        } else if (itemId == R.id.nav_Diario) {
            selectedFragment = Diario()
        } else if (itemId == R.id.nav_EspacioTerapeutico) {
            // Handle the profile case
            selectedFragment = EspacioTerapeutico()
        } else {
            selectedFragment = ZonaActiva()
        }
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            selectedFragment!!
        ).commit()
        true
    }
}