package proemprender.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import proemprender.Product;
import proemprender.dialogs.ComponentDialog;
import proemprender.dialogs.ConfirmDialog;
import proemprender.dialogs.ProductDialog;
import proemprender.model.DbAdapter;

public class ProductActivity extends AppCompatActivity implements ProductDialog.ProductDialogListener,
        ConfirmDialog.ProductDialogConfirm, ComponentDialog.ComponentDialogListener {

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
            openDialogConfirDeleteProduct();
        });
    }

    private void editProduct(){
        FloatingActionButton edit_product = findViewById(R.id.btn_edit_product);
        edit_product.setOnClickListener(view -> {
            openDialogEditProduct();
        });
    }

    private void addComponent(){
        FloatingActionButton add_component = findViewById(R.id.btn_add_component);
        add_component.setOnClickListener(view -> {
            openDialogAddComponent();
        });
    }

    public void openDialogEditProduct() {
        ProductDialog Dialog = new ProductDialog("Editar Producto", p.getName(), p.getPrice().toString());
        Dialog.show(getSupportFragmentManager(), "dialog");
    }

    //Edita el producto en la base de datos
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
            this.title.setText(name.toUpperCase());
            this.price.setText("$" + price);
            p.setName(name);
            p.setPrice(priceInt);
        }
    }

    public void openDialogConfirDeleteProduct() {
        ConfirmDialog dialog = new ConfirmDialog("¡Alerta!","Seguro que quiere eliminar el producto " + p.getName());
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void confirmDialog() {
        helper.deleteProduct(p.getId());
        this.finish();
    }


    public void openDialogAddComponent() {
        ComponentDialog dialog = new ComponentDialog("Nuevo componente", null, null, null);
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    // Agrega un componente al producto
    @Override
    public void confirmInputValues(String componentName, String componentPrice, String componentCant) {
        if (componentName.isEmpty()) {
            Toast.makeText(this, "El campo nombre está vacio", Toast.LENGTH_LONG).show();
        }
        else if (componentPrice.isEmpty()){
            Toast.makeText(this, "El campo precio está vacio", Toast.LENGTH_LONG).show();
        }
        else if (componentCant.isEmpty()){
            Toast.makeText(this, "El campo cantidad está vacio", Toast.LENGTH_LONG).show();
        }
        else {
            int priceInt = Integer.parseInt(componentPrice);
            int cantInt = Integer.parseInt(componentCant);
            helper.addComponent(componentName, priceInt, cantInt, p.getId());
        }
    }
}