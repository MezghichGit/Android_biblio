package com.sip.biblio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity {
    public static SQLiteDatabase dbBiblio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

         // création de la base de données
        dbBiblio=openOrCreateDatabase("dbBiblio", Context.MODE_PRIVATE, null);

        // création des deux tables
        dbBiblio.execSQL("CREATE TABLE IF NOT EXISTS categorie(idCategorie INTEGER,TitreCategories VARCHAR);");
        dbBiblio.execSQL("CREATE TABLE IF NOT EXISTS livre(idLivre VARCHAR,Titre VARCHAR,Auteur VARCHAR,Année VARCHAR,Prix VARCHAR,Nombre VARCHAR,IdCat INTEGER);");
    }

    public void callSListeCategories(View view){
        Intent i = new Intent(getApplicationContext(), listeCategories.class);
        startActivity(i);
    }

    public void callSListeLivres(View view){
        Intent i = new Intent(getApplicationContext(), listeLivres.class);
        startActivity(i);
    }

}