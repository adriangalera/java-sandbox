package com.gal.combineoptional;

public class Java8ItemFinderTest extends ItemFinderTest {

    @Override
    public void setItemFinder() {
        super.itemFinder = new Java8ItemFinder();
    }
}