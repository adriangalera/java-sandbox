package com.gal.combineoptional;

import java.util.List;

public class Java8ItemFinder extends ItemFinder {

    @Override
    public String findItem(List<String> items) {
        return findFirstItemStartingWithA(items)
            .orElseGet(() -> findFirstItemStartingWithB(items)
                .orElseThrow(() -> new RuntimeException("no item found")));
    }
}
