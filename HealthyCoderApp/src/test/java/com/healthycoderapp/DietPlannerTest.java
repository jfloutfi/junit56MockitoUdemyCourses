package com.healthycoderapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private  DietPlanner dietPlanner;

    @BeforeEach
    void setUp() {
        dietPlanner = new DietPlanner(20, 30, 50);
    }

    @Test
    void should_ReturnCorrectDietPlan_when_CorrectCoder() {
        Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
        DietPlan expected = new DietPlan(2202, 110, 73, 275);

        DietPlan actual = dietPlanner.calculateDiet(coder);

        // assertEquals(); does not work, it will compare the obj references
        assertAll(
                () -> assertEquals(expected.getCalories(), actual.getCalories()),
                () -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate()),
                () -> assertEquals(expected.getProtein(), actual.getProtein()),
                () -> assertEquals(expected.getFat(), actual.getFat())
        );
    }
}