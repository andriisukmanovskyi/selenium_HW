package com.epam.lab;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GetTitleTest {

    @Test
    public void testGetTitle() {
        TestTitle testTitle = new TestTitle();
        Assert.assertEquals(testTitle.getTitle("Apple"), "Apple - Пошук Google");
    }
}