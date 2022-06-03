package com.gal.combineoptional;

import java.util.List;
import java.util.Optional;

public abstract class ItemFinder {

    protected abstract String findItem(List<String> items);

    protected Optional<String> findItemsStartingWithA(List<String> items) {
        return findItemsStartingWith("A", items);
    }

    protected Optional<String> findItemsStartingWithB(List<String> items) {
        return findItemsStartingWith("B", items);
    }

    private Optional<String> findItemsStartingWith(String letter, List<String> items) {
        return items.stream()
            .filter(item -> item.startsWith(letter))
            .findFirst();
    }
}
