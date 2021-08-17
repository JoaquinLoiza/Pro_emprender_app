package proemprender.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import proemprender.model.DbAdapter;

public class NewProductActivity extends AppCompatActivity {
    DbAdapter helper;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        name = findViewById(R.id.input_nameProduct);
        addNewProduct();
        helper = new DbAdapter(this);
    }

    private void addNewProduct() {
        Button btn_products = (Button) findViewById(R.id.btn_addNewProduct);
        btn_products.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                helper.insertProduct(name.getText().toString());
                startActivity(new Intent(NewProductActivity.this, ProductsActivity.class));
            }
        });
    }


}