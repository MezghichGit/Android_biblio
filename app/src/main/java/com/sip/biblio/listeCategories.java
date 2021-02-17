package com.sip.biblio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.database.Cursor;

public class listeCategories extends AppCompatActivity {
    SQLiteDatabase dbBiblio;
    EditText editIdCategorie,editTitreCategorie;
    Button ajouterCategories,afficherCategories,supprimerCategories,modifierCategorie;

    //SQLiteDatabase dbCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_categories);

        editIdCategorie=(EditText)findViewById(R.id.editIdCategorie);
        editTitreCategorie=(EditText)findViewById(R.id.editTitreCategorie);

        ajouterCategories=(Button)findViewById(R.id.ajouterCategories);
        afficherCategories=(Button)findViewById(R.id.afficherCategories);
        modifierCategorie=(Button)findViewById(R.id.modifierCategorie);
        supprimerCategories=(Button)findViewById(R.id.supprimerCategories);

        //dbCategories=openOrCreateDatabase("CategorieDB", Context.MODE_PRIVATE, null);
        //dbCategories.execSQL("CREATE TABLE IF NOT EXISTS categorie(idCategorie VARCHAR,TitreCategories VARCHAR);");
        dbBiblio=openOrCreateDatabase("dbBiblio", Context.MODE_PRIVATE, null);
    }
        public void ajouterCat(View view) {
            if (view==ajouterCategories)
            {
                if(editIdCategorie.getText().toString().trim().length()==0||
                    editTitreCategorie.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                dbBiblio.execSQL("INSERT INTO categorie VALUES('"+editIdCategorie.getText()+"','"+editTitreCategorie.getText()+"');");
                showMessage("Success", "Record added");
                clearText();
            }
        }

    public void viewAllCat(View view) {
        if(view==afficherCategories)
        {
            Cursor c=dbBiblio.rawQuery("SELECT * FROM categorie", null);
            if(c.getCount()==0)
            {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("ID: "+c.getString(0)+"\n");
                buffer.append("Titre: "+c.getString(1)+"\n");
            }
            showMessage("Liste des catégories", buffer.toString());
        }
    }



    public void supprimerCat(View view) {
        if(view==supprimerCategories)
        {
            if(editIdCategorie.getText().toString().trim().length()==0)
            {
                showMessage("Erreur", "Veuillez entrer l'ID");
                return;
            }
            Cursor c=dbBiblio.rawQuery("SELECT * FROM categorie WHERE idCategorie='"+editIdCategorie.getText()+"'", null);
            if(c.moveToFirst())
            {
                dbBiblio.execSQL("DELETE FROM categorie WHERE idCategorie='"+editIdCategorie.getText()+"'");
                showMessage("Success", "Record Deleted");
            }
            else
            {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
    }

    public void updateCat(View view) {
        if(view==modifierCategorie)
        {
            if(editIdCategorie.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter ID");
                return;
            }
            Cursor c=dbBiblio.rawQuery("SELECT * FROM categorie WHERE idCategorie='"+editIdCategorie.getText()+"'", null);
            if(c.moveToFirst())
            {
                dbBiblio.execSQL("UPDATE categorie SET TitreCategories='"+editTitreCategorie.getText()+"'WHERE idCategorie='"+editIdCategorie.getText()+"'");
                showMessage("Success", "Record Modified");
            }
            else
            {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

        public void clearText()
    {
        editIdCategorie.setText("");
        editTitreCategorie.setText("");

    }
    }
