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

public class listeLivres extends AppCompatActivity {

    EditText editIdLivre,editTitreLivre,editAuteursLivre,editAnneeLivre,editPrixLivre,editNombreLivre,editLivreIdCat;
    Button ajouterLivre,afficherLivres,supprimerLivre;

    SQLiteDatabase dbLivres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_livres);

        editIdLivre=(EditText)findViewById(R.id.editIdLivre);
        editTitreLivre=(EditText)findViewById(R.id.editTitreLivre);
        editAuteursLivre=(EditText)findViewById(R.id.editAuteursLivre);
        editAnneeLivre=(EditText)findViewById(R.id.editAnneeLivre);
        editPrixLivre=(EditText)findViewById(R.id.editPrixLivre);
        editNombreLivre=(EditText)findViewById(R.id.editNombreLivre);
        editLivreIdCat=(EditText)findViewById(R.id.editLivreIdCat);

        ajouterLivre=(Button)findViewById(R.id.ajouterLivre);
        afficherLivres=(Button)findViewById(R.id.afficherLivres);
        supprimerLivre=(Button)findViewById(R.id.supprimerLivre);

        dbLivres=openOrCreateDatabase("livreDB", Context.MODE_PRIVATE, null);
        dbLivres.execSQL("CREATE TABLE IF NOT EXISTS livre(idLivre VARCHAR,Titre VARCHAR,Auteur VARCHAR,Année VARCHAR,Prix VARCHAR,Nombre VARCHAR,IdCat VARCHAR);");
    }

    public void ajouterLivre(View view) {
        if (view==ajouterLivre)
        {
            if(editIdLivre.getText().toString().trim().length()==0||
                    editTitreLivre.getText().toString().trim().length()==0||
                    editAuteursLivre.getText().toString().trim().length()==0||
                    editAnneeLivre.getText().toString().trim().length()==0||
                    editPrixLivre.getText().toString().trim().length()==0||
                    editNombreLivre.getText().toString().trim().length()==0||
                    editLivreIdCat.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            dbLivres.execSQL("INSERT INTO livre VALUES('"+editIdLivre.getText()+"','"+editTitreLivre.getText()+"','"+editAuteursLivre.getText()+"','"+editAnneeLivre.getText()+"','"+editPrixLivre.getText()+"','"+editNombreLivre.getText()+"','"+editLivreIdCat.getText()+"');");
            showMessage("Success", "Record added");
            clearText();
        }
    }

    public void viewAllLivre(View view) {
        if(view==afficherLivres)
        {
            Cursor c=dbLivres.rawQuery("SELECT * FROM livre", null);
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
                buffer.append("Auteur: "+c.getString(2)+"\n");
                buffer.append("Année: "+c.getString(3)+"\n");
                buffer.append("Prix: "+c.getString(4)+"\n");
                buffer.append("Nombre: "+c.getString(5)+"\n");
                buffer.append("IdCat: "+c.getString(6)+"\n");
            }
            showMessage("Liste des livres", buffer.toString());
        }
    }

    public void supprimerunlivre(View view) {
        if(view==supprimerLivre)
        {
            if(editIdLivre.getText().toString().trim().length()==0)
            {
                showMessage("Erreur", "Veuillez entrer l'ID");
                return;
            }
            Cursor c=dbLivres.rawQuery("SELECT * FROM livre WHERE idLivre='"+editIdLivre.getText()+"'", null);
            if(c.moveToFirst())
            {
                dbLivres.execSQL("DELETE FROM livre WHERE idLivre='"+editIdLivre.getText()+"'");
                showMessage("Success", "Record Deleted");
            }
            else
            {
                showMessage("Error", "Invalid ID");
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
        editIdLivre.setText("");
        editTitreLivre.setText("");
        editAuteursLivre.setText("");
        editAnneeLivre.setText("");
        editPrixLivre.setText("");
        editNombreLivre.setText("");
        editLivreIdCat.setText("");

    }

}