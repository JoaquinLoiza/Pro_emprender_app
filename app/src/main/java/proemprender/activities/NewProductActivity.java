package proemprender.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import proemprender.model.DbAdapter;

public class NewProductActivity extends AppCompatActivity {
    DbAdapter helper;
    EditText name;
    EditText price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        name = findViewById(R.id.input_nameProduct);
        price = findViewById(R.id.input_price);
        addNewProduct();
        helper = new DbAdapter(this);
    }

    private void addNewProduct() {
        Button btn_products = (Button) findViewById(R.id.btn_addNewProduct);
        btn_products.setOnClickListener(view -> {
            int priceInt = Integer.parseInt(price.getText().toString());
            helper.insertProduct(name.getText().toString(), priceInt);
            finish();
        });
    }
}