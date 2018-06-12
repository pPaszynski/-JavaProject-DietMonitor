package pk.dietmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FoodDetails extends AppCompatActivity {

    TextView energy_value, carbs_value, protein_value, fat_value;
    Button food_item_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_details_view);

        energy_value = (TextView) findViewById(R.id.food_item_energy_value);
        carbs_value = (TextView) findViewById(R.id.food_item_carbs_value);
        protein_value = (TextView) findViewById(R.id.food_item_protein_value);
        fat_value = (TextView) findViewById(R.id.food_item_fat_value);

        Intent intent = getIntent();
        getIncomingIntent();

        if(getIntent().hasExtra("name")) {
            this.setTitle(getIntent().getStringExtra("name"));
        }
    }

    public void getIncomingIntent() {
        if(getIntent().hasExtra("energy_value") && getIntent().hasExtra("carbs_value") &&
                getIntent().hasExtra("protein_value") && getIntent().hasExtra("fat_value")) {

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
}
