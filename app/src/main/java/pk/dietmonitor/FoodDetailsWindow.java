package pk.dietmonitor;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FoodDetailsWindow extends AppCompatActivity {

    TextView energy_value, carbs_value, protein_value, fat_value, check_text;
    EditText mass_value;
    Button food_item_add;
    String product_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_details_view);

        energy_value = (TextView) findViewById(R.id.food_item_energy_value);
        carbs_value = (TextView) findViewById(R.id.food_item_carbs_value);
        protein_value = (TextView) findViewById(R.id.food_item_protein_value);
        fat_value = (TextView) findViewById(R.id.food_item_fat_value);
        check_text = (TextView) findViewById(R.id.textView3);
        mass_value = (EditText) findViewById(R.id.food_item_mass_value);

        Intent intent = getIntent();
        getIncomingIntent();

        if(getIntent().hasExtra("name")) {
            this.setTitle(getIntent().getStringExtra("name"));
        }
    }

    public void getIncomingIntent() {
        if(getIntent().hasExtra("energy_value") &&
                getIntent().hasExtra("carbs_value") &&
                getIntent().hasExtra("protein_value") &&
                getIntent().hasExtra("fat_value")) {

            String energy = getIntent().getStringExtra("energy_value");
            String carbs = getIntent().getStringExtra("carbs_value");
            String protein = getIntent().getStringExtra("protein_value");
            String fat = getIntent().getStringExtra("fat_value");

            setText(energy, carbs, protein, fat);
        }
    }

    public void setText(String energy, String carbs, String protein, String fat) {
        energy_value.setText(energy.concat("kcal"));
        carbs_value.setText(carbs.concat("g"));
        protein_value.setText(protein.concat("g"));
        fat_value.setText(fat.concat("g"));

    }

    public void addChosenProduct(View view) {

        float a = 0;
        try{
            a = Float.valueOf(mass_value.getText().toString());
        }catch(Exception e){
            e.getMessage();
        }
        String product_name = getIntent().getStringExtra("name");

        FoodModel chosen_food = new FoodModel(a);
        fillFoodModel(chosen_food, product_name);
        check_text.setText(String.valueOf(chosen_food.getPortion()));
    }

    public void fillFoodModel(FoodModel food, String name){

        food.setName(getIntent().getStringExtra("name"));
        food.setPortion(Float.valueOf(getIntent().getStringExtra("portion_value")));
        food.setEnergy(Float.valueOf(getIntent().getStringExtra("energy_value")));
        food.setCarbs(Float.valueOf(getIntent().getStringExtra("carbs_value")));
        food.setProtein(Float.valueOf(getIntent().getStringExtra("protein_value")));
        food.setFat(Float.valueOf(getIntent().getStringExtra("fat_value")));
    }
}

