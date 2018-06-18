package pk.dietmonitor.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pk.dietmonitor.R;
import pk.dietmonitor.database.model.Food;

/**
 * Klasa pomocnicza do zarzadzania RecyclerView
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Food> foodList = new ArrayList<>();
    private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItem);
    }

    public RecyclerViewAdapter(Context context, List<Food> foodList, ListItemClickListener listener) {
        if(foodList != null) {
            this.context = context;
            this.foodList = foodList;
            this.mOnClickListener = listener;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.food_in_list_view;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(foodList.get(position));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

        TextView foodName, foodPortion, foodEnergy;

        public ViewHolder(View itemView) {
            super(itemView);

            foodName = (TextView) itemView.findViewById(R.id.food_name);
            foodPortion = (TextView) itemView.findViewById(R.id.food_portion);
            foodEnergy = (TextView) itemView.findViewById(R.id.food_energy);

            itemView.setOnClickListener(this);
        }

        public void bindData(Food food) {
            foodName.setText(food.getName());
            foodPortion.setText(String.valueOf(food.getPortion()).concat("g"));
            foodEnergy.setText(String.valueOf(food.getEnergy()).concat("kcal"));
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
