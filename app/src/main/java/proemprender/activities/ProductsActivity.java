package proemprender.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import proemprender.Product;

public class ProductsActivity extends AppCompatActivity {
    ListView products;
    ArrayList<String> listaDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        products= findViewById(R.id.dinamic_products);
        loadProducts();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDatos);
        products.setAdapter(adapter);
    }

    private void loadProducts() {

        Product torta = new Product(1, "Torta");
        Product factura = new Product(2, "Factura");
        Product pastaflora = new Product(3, "Pasta flora");
        Product medialuna = new Product(4, "Media luna");
        Product brownie = new Product(5, "Brownie");

        listaDatos = new ArrayList<>();
            //products harcodeados
            listaDatos.add(torta.getName());
            listaDatos.add(factura.getName());
            listaDatos.add(pastaflora.getName());
            listaDatos.add(medialuna.getName());
            listaDatos.add(brownie.getName());
    }
}