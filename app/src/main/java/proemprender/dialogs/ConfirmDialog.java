package proemprender.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;


public class ConfirmDialog extends AppCompatDialogFragment {

    private ProductDialogConfirm listener;
    private String title;
    private String message;

    public ConfirmDialog(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(this.title)
                .setMessage(this.message)
                .setNegativeButton("cancelar", (dialogInterface, i) -> {
                })
                .setPositiveButton("Confirmar", (dialogInterface, i) -> {
                    listener.confirmDialog();
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ProductDialogConfirm) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface ProductDialogConfirm {
        void confirmDialog();
    }
}
