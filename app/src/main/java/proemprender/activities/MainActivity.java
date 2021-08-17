package proemprender.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextActivityProducts();
    }

    private void nextActivityProducts(){
        Button btn_products = findViewById(R.id.btn_products);
        btn_products.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, ProductsActivity.class)));
    }
}