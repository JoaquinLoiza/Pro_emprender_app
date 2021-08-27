package proemprender.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import proemprender.Product;
import proemprender.dialogs.NewProductDialog;
import proemprender.model.DbAdapter;

public class ProductActivity extends AppCompatActivity implements NewProductDialog.ProductDialogListener{

    Product p;
    TextView title;
    TextView price;
    TextView cost;
    DbAdapter helper;
    Bundle obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        title = findViewById(R.id.title_product);
        price = findViewById(R.id.price);
        cost = findViewById(R.id.cost);
        obj = getIntent().getExtras();
        helper = new DbAdapter(this);
        editProduct();
        deleteProduct();
        addComponent();
        setInfo();
    }

    private void setInfo() {
        if (obj != null) {
            p = (Product) obj.getSerializable("product");
            title.setText(p.getName().toUpperCase());
            price.setText("$" + p.getPrice());
        }
    }

    private void deleteProduct(){
        FloatingActionButton delete_product = findViewById(R.id.btn_delete_product);
        delete_product.setOnClickListener(view -> {
            helper.deleteProduct(p.getId());
            this.finish();
        });
    }

    private void editProduct(){
        FloatingActionButton edit_product = findViewById(R.id.btn_edit_product);
        edit_product.setOnClickListener(view -> {
            openDialog();
        });
    }

    private void addComponent(){
        FloatingActionButton add_component = findViewById(R.id.btn_add_component);
        add_component.setOnClickListener(view -> {
            System.out.println("Agregar nuevo componente");
        });
    }

    public void openDialog() {
        NewProductDialog Dialog = new NewProductDialog("Editar Producto", null, null);
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
            helper.editProduct(p.getId(), name, priceInt);
            this.title.setText(name);
            this.price.setText("$" + price);
        }
    }
}