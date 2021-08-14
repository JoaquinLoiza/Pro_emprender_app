package proemprender.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {
    ListView products;
    ArrayList<String> listaDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        products= (ListView) findViewById(R.id.dinamic_products);
        loadProducts();
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDatos);
        products.setAdapter(adapter);
    }

    private void loadProducts() {
        listaDatos = new ArrayList<String>();
        //products harcodeados
            listaDatos.add("Torta");
            listaDatos.add("Factura");
            listaDatos.add("Pastaflora");
            listaDatos.add("Media Luna");
            listaDatos.add("Brownie");
            listaDatos.add("Factura");
            listaDatos.add("Torta");
            listaDatos.add("Pastaflora");
            listaDatos.add("Media Luna");
            listaDatos.add("Brownie");
            listaDatos.add("Torta");
            listaDatos.add("Factura");
            listaDatos.add("Pastaflora");
            listaDatos.add("Media Luna");
            listaDatos.add("Brownie");
    }
}