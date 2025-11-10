package com.healthycoderapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @Test
    void should_ReturnTrue_when_DietRecommended() {
        // given
        double height = 1.65;
        double weight = 81.2;
        // when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);
        // then
        assertTrue(recommended);
    }

    @Test
    void should_ReturnFalse_when_DietNotRecommended() {
        // given
        double height = 1.92;
        double weight = 81.2;
        // when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);
        // then
        assertFalse(recommended);
    }

    @Test
    void should_ThrowArithmeticException_when_HeightZero() {
        // given
        double height = 0.0;
        double weight = 81.2;
        // when
        Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
        // then
        assertThrows(ArithmeticException.class, executable);
    }

    @Test
    void should_ReturnCoderWithWorstBMI_when_CodeListNotEmpty() {
        // given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80,60.0));
        coders.add(new Coder(1.82,98.0));
        coders.add(new Coder(1.82,64.7));

        // when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        // then
        // the problem here is that if the 1st assertion fails, the 2nd will never be executed
        assertEquals(1.82, coderWorstBMI.getHeight());
        assertEquals(98.0, coderWorstBMI.getWeight());
        // So, we wrap both assertions in assertAll:
        assertAll(
                () -> assertEquals(1.83, coderWorstBMI.getHeight()),
                () -> assertEquals(98.4, coderWorstBMI.getWeight())
        );
    }

    @Test
    void should_ReturnNull_when_CodeLisEmpty() {
        // given
        List<Coder> coders = new ArrayList<>();

        // when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        // then
        // the problem here is that if the 1st assertion fails, the 2nd will never be executed
        assertNull(coderWorstBMI);
    }

    @Test
    void should_ReturnCorrectBMIScoreArray_when_CodeListNotEmpty() {
        // given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));
        double[] expected = {18.52, 29.59, 19.53};

        // when
        double []bmiScores = BMICalculator.getBMIScores(coders);

        // then
        assertArrayEquals(expected, bmiScores);
    }
}