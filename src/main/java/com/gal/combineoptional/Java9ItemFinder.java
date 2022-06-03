package com.gal.combineoptional;

import java.util.List;

public class Java9ItemFinder extends ItemFinder {

    @Override
    public String findItem(List<String> items) {
        return findItemsStartingWithA(items)
            .or(() -> findItemsStartingWithB(items))
            .orElseThrow(() -> new RuntimeException("no item found"));
    }
}
