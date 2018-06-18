package pk.dietmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import pk.dietmonitor.database.DBHelper;
import pk.dietmonitor.database.model.Food;

public class MainWindow extends AppCompatActivity {

    Button button_profile, button_daily, button_history, button_find_produkt;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        dbHelper = DBHelper.getInstance(this);
        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food("Chicken Breast", 100, 100, 0, 21.5f, 1.3f));
        foodList.add(new Food("White Rice", 100, 365, 80, 7.1f, 0.7f));
        foodList.add(new Food("Brown Rice", 100, 370, 77.2f, 8, 2.9f));
        foodList.add(new Food("Pasta", 100, 131, 25, 5, 1.1f));
        foodList.add(new Food("Broccoli", 100, 33, 7, 2.8f, 0.4f));
        foodList.add(new Food("Potato", 100, 76, 17, 2,  0.1f));
        foodList.add(new Food("Chocolate", 100, 545, 61, 4.9f, 31));
        foodList.add(new Food("Chips", 100, 536, 53, 7, 35));
        foodList.add(new Food("Cream-cookie", 100, 274, 35, 4.3f, 13));
        foodList.add(new Food("Milk", 100, 42, 5, 3.4f, 1));
        foodList.add(new Food("Egg", 100, 155, 1.1f, 13, 11));
        foodList.add(new Food("Beer", 100, 43, 3.6f, 0.5f, 0));
        foodList.add(new Food("Vodka", 100, 231, 0, 0, 0));
        foodList.add(new Food("Vodka", 100, 231, 0, 0, 0));
        for(Food food : foodList) {
            dbHelper.insertFood(food);
        }


        button_profile = (Button) findViewById(R.id.button_profile);
        button_daily = (Button) findViewById(R.id.button_daily);
        button_history = (Button) findViewById(R.id.button_history);
        button_find_produkt = (Button) findViewById(R.id.button_find_product);
    }

    public void openSearchProductWindow(View view) {
        Intent intent = new Intent(this, SearchProductWindow.class);

        startActivity(intent);
    }

    public void openDailyWindow(View view) {
        Intent intent = new Intent(this, DailyWindow.class);

        startActivity(intent);
    }

    public void openProfileWindow(View view) {
        Intent intent = new Intent(this, ProfileWindow.class);

        startActivity(intent);
    }

    public void openHistoryWindow(View view) {
        Intent intent = new Intent(this, HistoryWindow.class);

        startActivity(intent);
    }
}
