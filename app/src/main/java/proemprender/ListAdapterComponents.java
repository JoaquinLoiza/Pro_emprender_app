package proemprender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import proemprender.activities.R;

public class ListAdapterComponents extends ArrayAdapter<Component> {

    private List<Component> componets;
    private int resourceLayout;
    private Context mContext;

    public ListAdapterComponents(@NonNull Context mContext, int resource, List<Component> objects) {
        super(mContext, resource, objects);
        this.componets = objects;
        this.mContext = mContext;
        this.resourceLayout = resource;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(mContext).inflate(resourceLayout, null);
        }
        Component c = componets.get(position);
        TextView name = view.findViewById(R.id.componet_name);
        TextView price = view.findViewById(R.id.component_price);
        TextView cant = view.findViewById(R.id.component_cant);
        name.setText(c.getName().toUpperCase());
        price.setText('$'+ c.getPrice().toString());
        cant.setText(c.getCant().toString() + " g/u");
        return view;
    }
}
