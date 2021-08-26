package proemprender.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import proemprender.ListAdapterProducts;
import proemprender.Product;
import proemprender.dialogs.NewProductDialog;
import proemprender.model.DbAdapter;

public class ProductsActivity extends AppCompatActivity implements NewProductDialog.ProductDialogListener, Serializable {

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
        btn_products.setOnClickListener(view -> {
            openDialog();
        });
    }

    private void loadProducts() {
        adapter = null;
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

    public void openDialog() {
        NewProductDialog Dialog = new NewProductDialog();
        Dialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void setInputValues(String name, String price) {
        int priceInt = Integer.parseInt(price);
        helper.insertProduct(name, priceInt);
        onResume();
    }
}
