package pk.dietmonitor.database.model;

import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class FoodTest {

    @Test
    public void testFood() {
        Food orangeTomato = new Food("Orange Tomato", 111, 18, 3, 1, 0);
        Logger l = Logger.getLogger(Food.class.getName());
        double delta = 0.01;

        l.info("Name:    ".concat(orangeTomato.getName()));
        l.info("Portion: ".concat(String.valueOf(orangeTomato.getPortion())));
        l.info("Energy:  ".concat(String.valueOf(orangeTomato.getEnergy())));
        l.info("Carbs:   ".concat(String.valueOf(orangeTomato.getCarbs())));
        l.info("Protein: ".concat(String.valueOf(orangeTomato.getProtein())));
        l.info("Fat:     ".concat(String.valueOf(orangeTomato.getFat())));

        // assertEquals([String message,] dataType expected, dataType actual [, {float, double} delta]),
        // where 'dataType' = {int, float, ...}
        // |expected - actual| > delta -> AssertionError
        assertEquals("Check name", "Orange Tomato", orangeTomato.getName());
        assertEquals("Check portion", 111, orangeTomato.getPortion(), delta);
        assertEquals("Check energy", 18, orangeTomato.getEnergy(), delta);
        assertEquals("Check carbs", 3, orangeTomato.getCarbs(), delta);
        assertEquals("Check protein", 1, orangeTomato.getProtein(), delta);
        assertEquals("Check fat", 0, orangeTomato.getFat(), delta);
    }
}