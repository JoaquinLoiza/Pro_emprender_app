package proemprender.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import proemprender.Product;

public class ProductActivity extends AppCompatActivity {

    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        /*
        Bundle obj = getIntent().getExtras();
        Product p = null;

        if (obj != null) {
            p = (Product) obj.getSerializable("product");
            title.setText(p.getName());
        }
        */
    }
}