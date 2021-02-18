package com.sip.biblio;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListCategoriesActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categories);


        listView = (ListView)findViewById(R.id.listView);

        ArrayList<Categorie> categories = new ArrayList<Categorie>();
        Cursor c = MainActivity.dbBiblio.rawQuery("SELECT * FROM categorie", null);

        if(c.getCount()==0)
        {
            return;
        }

        while(c.moveToNext())
        {
            Categorie categorie = new Categorie(c.getString(0),c.getString(1));
            categories.add(categorie);

        }
        ArrayAdapter<Categorie> arrayAdapter = new ArrayAdapter<Categorie>(this, android.R.layout.simple_list_item_1 , categories);
        listView.setAdapter(arrayAdapter);

    }
}