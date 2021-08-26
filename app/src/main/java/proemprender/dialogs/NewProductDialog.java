package proemprender.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import proemprender.activities.R;


public class NewProductDialog extends AppCompatDialogFragment {
    private EditText editTextProductName;
    private EditText editTextProductPrice;
    private ProductDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_product, null);

        builder.setView(view)
                .setTitle("Nuevo Producto")
                .setNegativeButton("cancelar", (dialogInterface, i) -> {

                })
                .setPositiveButton("Agregar", (dialogInterface, i) -> {
                    String name = editTextProductName.getText().toString();
                    String price = editTextProductPrice.getText().toString();
                    listener.setInputValues(name, price);
                });

        editTextProductName = view.findViewById(R.id.input_name);
        editTextProductPrice = view.findViewById(R.id.input_price);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ProductDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface ProductDialogListener {
        void setInputValues(String productName, String productPrice);
    }
}
