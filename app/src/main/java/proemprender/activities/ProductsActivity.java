package proemprender.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import proemprender.Product;
import proemprender.model.DbAdapter;

public class ProductsActivity extends AppCompatActivity implements Serializable{
    ArrayList<String> dataList;
    ArrayList<Product> productList;
    DbAdapter helper;
    ArrayAdapter<String> adapter;
    ListView productsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        newProductActivity();
        productsListView = findViewById(R.id.dinamic_products);
        helper = new DbAdapter(this);
        dataList = new ArrayList<>();
        productList = new ArrayList<>();
        onClickProduct();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProducts();
        adapter = new ArrayAdapter<>(this, R.layout.product_card, dataList);
        productsListView.setAdapter(adapter);
    }

    private void newProductActivity(){
        FloatingActionButton btn_products = findViewById(R.id.btn_newProduct);
        btn_products.setOnClickListener(view ->
                startActivity(new Intent(ProductsActivity.this, NewProductActivity.class)));
    }

    private void loadProducts() {
        adapter = null;
        dataList.clear();
        productList.clear();
        Cursor cursor = helper.getProducts();

        while (cursor.moveToNext()){
            Product product = new Product(cursor.getInt(0), cursor.getString(1));
            productList.add(product);
        }

        for (Product p : productList) {
            dataList.add(p.getName().toUpperCase());
        }
    }

    private void onClickProduct(){
        productsListView.setOnItemClickListener((parent, view, position, id) -> {
        Product p = productList.get(position);
        Intent intent = new Intent(ProductsActivity.this, ProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", p);
        intent.putExtras(bundle);
        startActivity(intent);
        });
    }
}
