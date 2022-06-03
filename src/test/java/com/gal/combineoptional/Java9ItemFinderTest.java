package com.gal.combineoptional;

public class Java9ItemFinderTest extends ItemFinderTest {

    @Override
    public void setItemFinder() {
        super.itemFinder = new Java9ItemFinder();
    }
}