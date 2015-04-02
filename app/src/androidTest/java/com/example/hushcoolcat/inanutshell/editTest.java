package com.example.hushcoolcat.inanutshell;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.Assert;

public class editTest extends ApplicationTestCase<Application> {
    public editTest() {
        super(Application.class);
    }

    edit edit = new edit();

    public void testNameEmpty() {
        boolean isEmpty = edit.saveFile("", "Apples", "Cut", "Yummy!");
        Assert.assertEquals(false, isEmpty);
    }

    public void testIngredientsEmpty() {
        boolean isEmpty = edit.saveFile("Cutting Apples", "", "Cut", "Yummy!");
        Assert.assertEquals(false, isEmpty);
    }

    public void testDirectionsEmpty() {
        boolean isEmpty = edit.saveFile("Cutting Apples", "Apples", "", "Yummy!");
        Assert.assertEquals(false, isEmpty);
    }

    convert convert = new convert();

    public void testInputEmpty() {
        double actual = convert.convertValues("pinch", "pinch", "");
        double expected = 0;
        Assert.assertEquals(expected, actual);
    }

    public void testConvertTBSPtoTSP() {
        double actual = convert.convertValues("tbsp", "tsp", "5");
        double expected = 15;
        Assert.assertEquals(expected, actual);
    }

    public void testConvertTBSPtoPINCH() {
        double actual = convert.convertValues("tbsp", "pinch", "4");
        double expected = 192;
        Assert.assertEquals(expected, actual);
    }

    public void testConvertTSPtoTBSP() {
        double actual = convert.convertValues("tsp", "tbsp", "5");
        double expected = 1.6666666666666665;
        Assert.assertEquals(expected, actual);
    }

    public void testConvertCUPtoDASH() {
        double actual = convert.convertValues("cup", "dash", "1");
        double expected = 384;
        Assert.assertEquals(expected, actual);
    }

    public void testMLtoL() {
        double actual = convert.convertValues("millilitre", "litre", "2");
        double expected = 0.002;
        Assert.assertEquals(expected, actual);
    }

    public void testOZtoPINT() {
        double actual = convert.convertValues("ounce", "pint", "2");
        double expected = 0.125;
        Assert.assertEquals(expected, actual);
    }

    public void testOZtoQuart() {
        double actual = convert.convertValues("ounce", "quart", "2");
        double expected = 0.0625;
        Assert.assertEquals(expected, actual);
    }
}