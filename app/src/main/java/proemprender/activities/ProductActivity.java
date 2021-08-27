package proemprender.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import proemprender.Component;
import proemprender.ListAdapterComponents;
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
    List<Component> componentList;
    ListView componentsListView;
    ListAdapterComponents adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        componentsListView = findViewById(R.id.dinamic_list_component);
        title = findViewById(R.id.title_product);
        price = findViewById(R.id.price);
        cost = findViewById(R.id.cost);
        obj = getIntent().getExtras();
        helper = new DbAdapter(this);
        editProduct();
        deleteProduct();
        addComponent();
        setInfo();
        componentList = new ArrayList<>();
        refreshComponents();
    }

    protected void refreshComponents() {
        loadComponents();
        for (Component c:componentList) {
            System.out.println(c.getName());
        }
        adapter = new ListAdapterComponents(this, R.layout.card_component, componentList);
        componentsListView.setAdapter(adapter);
    }

    //Trae todos los productos de la base de datos y crea los objetos "Product" de cada uno
    private void loadComponents() {
        adapter = null;
        componentList.clear();
        Cursor cursor = helper.getComponents(p.getId());
        while (cursor.moveToNext()){
            Component component = new Component(cursor.getInt(0), cursor.getString(2), cursor.getInt(3), cursor.getInt(4));
            componentList.add(component);
        }
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
            refreshComponents();
        }
    }
}