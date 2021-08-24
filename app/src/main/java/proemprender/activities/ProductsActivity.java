package proemprender.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import proemprender.Product;
import proemprender.model.DbAdapter;

public class ProductsActivity extends AppCompatActivity implements Serializable{
    ListView products;
    ArrayList<String> dataList;
    DbAdapter helper;
    ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        newProductActivity();
        products= findViewById(R.id.dinamic_products);
        helper = new DbAdapter(this);
        loadProducts();
        refreshActivity();
        onClickProduct();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        products.setAdapter(adapter);
    }

    private void newProductActivity(){
        Button btn_products = findViewById(R.id.btn_newProduct);
        btn_products.setOnClickListener(view ->
                startActivity(new Intent(ProductsActivity.this, NewProductActivity.class)));
    }

    private void refreshActivity() {
        Button btn_refresh = findViewById(R.id.btn_recargar);
        btn_refresh.setOnClickListener(view -> recreate());
    }

    private void loadProducts() {

        dataList = new ArrayList<>();
        productList = new ArrayList<>();
        Cursor cursor = helper.getProducts();

        while (cursor.moveToNext()){
            Product product = new Product(cursor.getInt(0), cursor.getString(1));
            productList.add(product);
        }

        for (Product p : productList) {
            dataList.add(p.getName());
        }
    }

    private void onClickProduct(){
        products.setOnItemClickListener((parent, view, position, id) -> {
        Product p = productList.get(position);
        System.out.println("Nombre: " + p.getName() + ", Id: " + p.getId() + ", Position: " + position);
        /*
        Intent intent = new Intent(ProductsActivity.this, ProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", p);
        intent.putExtras(bundle);
        startActivity(intent);
         */
        });
    }
}
