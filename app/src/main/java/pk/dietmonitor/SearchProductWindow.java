package pk.dietmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import pk.dietmonitor.database.DBHelper;
import pk.dietmonitor.database.model.Food;
import pk.dietmonitor.recyclerview.RecyclerViewAdapter;

public class SearchProductWindow extends AppCompatActivity
    implements RecyclerViewAdapter.ListItemClickListener {

    MaterialSearchBar materialSearchBar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter adapter;

    DBHelper dbHelper;

    List<String> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        Intent intent = getIntent();

        this.setTitle("Find product");

        materialSearchBar = (MaterialSearchBar) findViewById(R.id.mt_search_bar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        dbHelper = DBHelper.getInstance(this);

        suggestList = dbHelper.getAllFoodNames();
        materialSearchBar.setLastSuggestions(suggestList);
        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();
                for(String search: suggestList) {
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase())) {
                        suggest.add(search);
                    }
                }

                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled) { resetSearch(); }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) { startSearch(text.toString()); }

            @Override
            public void onButtonClicked(int buttonCode) { }
        });

        adapter = new RecyclerViewAdapter(this, dbHelper.getAllFood(), this);
        recyclerView.setAdapter(adapter);
    }

    private void resetSearch() {
        adapter = new RecyclerViewAdapter(this, dbHelper.getAllFood(), this);
        recyclerView.setAdapter(adapter);
    }

    private void startSearch(String text) {
        adapter = new RecyclerViewAdapter(this, dbHelper.getFoodByName(text), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this, FoodDetailsWindow.class);

        Food clickedFood = adapter.getFoodList().get(clickedItemIndex);

        intent.putExtra("name", clickedFood.getName());
        intent.putExtra("portion_value", String.valueOf(clickedFood.getPortion()));
        intent.putExtra("energy_value", String.valueOf(clickedFood.getEnergy()));
        intent.putExtra("carbs_value", String.valueOf(clickedFood.getCarbs()));
        intent.putExtra("protein_value", String.valueOf(clickedFood.getProtein()));
        intent.putExtra("fat_value", String.valueOf(clickedFood.getFat()));

        startActivity(intent);
    }
}
