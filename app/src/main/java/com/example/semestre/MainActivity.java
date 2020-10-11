package com.example.semestre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView menu;
    NavController nav;
    Asignatura as;
    BaseDatos miBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = findViewById(R.id.bottomNavigationView);
        nav = Navigation.findNavController(this,R.id.fragment);
        NavigationUI.setupWithNavController(menu, nav);


    }
}