package proemprender.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        newProductActivity();
        products= findViewById(R.id.dinamic_products);
        helper = new DbAdapter(this);
        loadProducts();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaDatos);
        products.setAdapter(adapter);
    }

    private void newProductActivity(){
        Button btn_products = (Button) findViewById(R.id.btn_newProduct);
        btn_products.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductsActivity.this, NewProductActivity.class));
            }
        });
    }

    private void loadProducts() {

        listaDatos = new ArrayList<>();
        ArrayList<Product> listProduct = new ArrayList<>();
        Cursor cursor = helper.getProducts();

        while (cursor.moveToNext()){
            Product product = new Product(cursor.getInt(0), cursor.getString(1));
            listProduct.add(product);
        }

        for (Product p : listProduct) {
            listaDatos.add(p.getName());
        }
    }
}
