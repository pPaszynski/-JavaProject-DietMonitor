package pk.dietmonitor.database.model;

import org.junit.Test;
import java.sql.Timestamp;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class FoodConsumedTest {

    @Test
    public void testFoodConsumed(){

        FoodConsumed orangePotato = new FoodConsumed();
        Logger l = Logger.getLogger(Food.class.getName());
        double delta = 0.01;
        Timestamp time = new Timestamp(1270889220);

        //Check setter
        orangePotato.setId(666);
        orangePotato.setMass(777.0f);
        orangePotato.setTime(time);
        orangePotato.setFoodNameFK("key");


        l.info("Id:    ".concat(String.valueOf(orangePotato.getId())));
        l.info("Mass: ".concat(String.valueOf(orangePotato.getMass())));
        l.info("Time:  ".concat(String.valueOf(orangePotato.getTime())));
        l.info("foodNameFK:   ".concat(String.valueOf(orangePotato.getFoodNameFK())));

        // assertEquals([String message,] dataType expected, dataType actual [, {float, double} delta]),
        // where 'dataType' = {int, float, ...}
        // |expected - actual| > delta -> AssertionError
        assertEquals("Check Id", 666, orangePotato.getId());
        assertEquals("Check Mass", 777.0f, orangePotato.getMass(), delta);
        assertEquals("Check Time", time, orangePotato.getTime());
        assertEquals("Check carbs", "key", orangePotato.getFoodNameFK());

    }

}
