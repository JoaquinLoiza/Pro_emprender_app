package proemprender.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

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
        refreshProducts();
        onClickProduct();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        refreshProducts();
    }

    protected void refreshProducts() {
        loadProducts();
        adapter = new ListAdapterProducts(this, R.layout.card_product, productList);
        productsListView.setAdapter(adapter);
    }

    //Escucha el boton para el dialogo de agregar nuevo producto
    private void newProductActivity(){
        FloatingActionButton btn_products = findViewById(R.id.btn_newProduct);
        btn_products.setOnClickListener(view -> {
            openDialog();
        });
    }

    //Trae todos los productos de la base de datos y crea los objetos "Product" de cada uno
    private void loadProducts() {
        adapter = null;
        productList.clear();
        Cursor cursor = helper.getProducts();

        while (cursor.moveToNext()){
            Product product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
            productList.add(product);
        }
    }

    //Escucha los clicks de cada item producto, y envia el objeto clickeado hacia otra actividad
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

    //Muestra el dialogo con los inputs para agregar un producto
    public void openDialog() {
        NewProductDialog Dialog = new NewProductDialog("Nuevo Producto", null, null);
        Dialog.show(getSupportFragmentManager(), "dialog");
    }

    //Inserta el producto en la base de datos
    @Override
    public void setInputValues(String name, String price) {
        if (name.isEmpty()) {
            Toast.makeText(this, "El campo nombre está vacio", Toast.LENGTH_LONG).show();
        }
        else if (price.isEmpty()){
            Toast.makeText(this, "El campo precio está vacio", Toast.LENGTH_LONG).show();
        }
        else {
            int priceInt = Integer.parseInt(price);
            helper.insertProduct(name, priceInt);
            refreshProducts();
        }
    }
}
