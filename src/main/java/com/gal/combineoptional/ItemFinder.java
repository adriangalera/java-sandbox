package com.gal.combineoptional;

import java.util.List;
import java.util.Optional;

public abstract class ItemFinder {

    protected abstract String findItem(List<String> items);

    protected Optional<String> findFirstItemStartingWithA(List<String> items) {
        return findFirstItemStartingWith("A", items);
    }

    protected Optional<String> findFirstItemStartingWithB(List<String> items) {
        return findFirstItemStartingWith("B", items);
    }

    private Optional<String> findFirstItemStartingWith(String letter, List<String> items) {
        return items.stream()
            .filter(item -> item.startsWith(letter))
            .findFirst();
    }
}
