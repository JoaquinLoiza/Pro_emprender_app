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

public class ComponentDialog extends AppCompatDialogFragment {

    private EditText editTextName;
    private EditText editTextPrice;
    private EditText editTextCant;
    private ComponentDialogListener listener;
    private final String title;
    private final String name;
    private final String price;
    private final String cant;

    public ComponentDialog(String title, String name, String price, String cant) {
        this.title = title;
        this.name = name;
        this.price = price;
        this.cant = cant;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_component, null);

        builder.setView(view)
                .setTitle(title)
                .setNegativeButton("Cancelar", (dialogInterface, i) -> {

                })
                .setPositiveButton("Confirmar", (dialogInterface, i) -> {
                    String name = editTextName.getText().toString();
                    String price = editTextPrice.getText().toString();
                    String cant = editTextCant.getText().toString();
                    listener.confirmInputValues(name, price, cant);
                });

        editTextName = view.findViewById(R.id.input_name_component);
        editTextPrice = view.findViewById(R.id.input_price_component);
        editTextCant = view.findViewById(R.id.input_cant_component);

        if (name != null && price != null && cant != null) {
            editTextName.setText(name);
            editTextPrice.setText(price);
            editTextCant.setText(cant);
        }

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ComponentDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface ComponentDialogListener {
        void confirmInputValues(String componentName, String componentPrice, String componentCant);
    }
}
