package proemprender.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import proemprender.Product;
import proemprender.model.DbAdapter;

public class ProductsActivity extends AppCompatActivity {
    ListView products;
    ArrayList<String> listaDatos;
    DbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        products= findViewById(R.id.dinamic_products);
        helper = new DbAdapter(this);
        newProduct();
        loadProducts();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaDatos);
        products.setAdapter(adapter);
    }

    private void newProduct() {
        helper.insertProduct("Torta");
        helper.insertProduct("Factura");
        helper.insertProduct("Pasta flora");
        helper.insertProduct("Media luna");
        helper.insertProduct("Brownie");
    }

    private void loadProducts() {
        listaDatos = new ArrayList<>();
        Cursor cursor = helper.getProducts();
        while (cursor.moveToNext()){
            listaDatos.add(cursor.getString(1));
        }
    }
}