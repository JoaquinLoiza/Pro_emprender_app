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

public class ListAdapterProducts extends ArrayAdapter<Product> {

  private List<Product> products;
  private int resourceLayout;
  private Context mContext;

    public ListAdapterProducts(@NonNull Context mContext, int resource, List<Product> objects) {
        super(mContext, resource, objects);
        this.products = objects;
        this.mContext = mContext;
        this.resourceLayout = resource;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(mContext).inflate(resourceLayout, null);
        }
        Product p = products.get(position);
        TextView name = view.findViewById(R.id.product_name);
        TextView price = view.findViewById(R.id.product_price);
        name.setText(p.getName().toUpperCase());
        price.setText('$'+ p.getPrice().toString());
        return view;
    }
}
