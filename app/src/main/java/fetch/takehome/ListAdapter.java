package fetch.takehome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ItemModel> localList;

    public ListAdapter(List<ItemModel> itemList) {
        localList = itemList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameField;
        private final TextView idField;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            nameField = (TextView) view.findViewById(R.id.itemName);
            idField = (TextView) view.findViewById(R.id.itemIds);
        }

        public TextView getNameField() {
            return nameField;
        }

        public TextView getIdField() {return idField; }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getNameField().setText(localList.get(position).getName());
        viewHolder.getIdField().setText("listId: " + localList.get(position).getListId());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localList.size();
    }
}
