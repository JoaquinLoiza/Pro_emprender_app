package proemprender.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import proemprender.Product;

public class ProductActivity extends AppCompatActivity {

    TextView title;
    Product p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        title = findViewById(R.id.title_product);
        Bundle obj = getIntent().getExtras();
        if (obj != null) {
            p = (Product) obj.getSerializable("product");
            System.out.println(p.getName());
            title.setText(p.getName());
        }
    }
}