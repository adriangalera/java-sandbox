package com.gal.combineoptional;

import java.util.List;

public class Java9ItemFinder extends ItemFinder {

    @Override
    public String findItem(List<String> items) {
        return findFirstItemStartingWithA(items)
            .or(() -> findFirstItemStartingWithB(items))
            .orElseThrow(() -> new RuntimeException("no item found"));
    }
}
