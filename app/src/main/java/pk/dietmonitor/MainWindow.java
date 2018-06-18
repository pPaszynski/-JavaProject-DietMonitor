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

    Button button_profile, button_target, button_history, button_find_produkt;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        dbHelper = DBHelper.getInstance(this);
        List<Food> foodList = new ArrayList<>();
        foodList.add(new Food("Strawberries", 100, 100, 100, 100, 100));
        for(Food food : foodList) {
            dbHelper.insertFood(food);
        }


        button_profile = (Button) findViewById(R.id.button_profile);
        button_target = (Button) findViewById(R.id.button_target);
        button_history = (Button) findViewById(R.id.button_history);
        button_find_produkt = (Button) findViewById(R.id.button_find_produkt);
    }

    public void openSearchProductWindow(View view) {
        Intent intent = new Intent(this, SearchProductWindow.class);

        startActivity(intent);
    }

    public void openTargetWindow(View view) {
        Intent intent = new Intent(this, TargetWindow.class);

        startActivity(intent);
    }

    public void openProfileWindow(View view) {
        Intent intent = new Intent(this, ProfileWindow.class);

        startActivity(intent);
    }
}
