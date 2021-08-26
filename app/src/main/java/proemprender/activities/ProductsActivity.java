package proemprender.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import proemprender.ListAdapterProducts;
import proemprender.Product;
import proemprender.model.DbAdapter;

public class ProductsActivity extends AppCompatActivity implements Serializable{

    List<Product> productList;
    DbAdapter helper;
    ListView productsListView;
    ListAdapterProducts adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        newProductActivity();
        productsListView = findViewById(R.id.dinamic_products);
        helper = new DbAdapter(this);
        productList = new ArrayList<>();
        onClickProduct();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProducts();
        adapter = new ListAdapterProducts(this, R.layout.card_product, productList);
        productsListView.setAdapter(adapter);
    }

    private void newProductActivity(){
        FloatingActionButton btn_products = findViewById(R.id.btn_newProduct);
        btn_products.setOnClickListener(view ->
                startActivity(new Intent(ProductsActivity.this, NewProductActivity.class)));
    }

    private void loadProducts() {
        productList.clear();
        Cursor cursor = helper.getProducts();

        while (cursor.moveToNext()){
            Product product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
            productList.add(product);
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
