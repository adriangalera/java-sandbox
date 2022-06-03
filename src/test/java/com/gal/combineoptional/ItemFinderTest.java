package com.gal.combineoptional;

import static org.junit.Assert.assertEquals;

import com.sun.tools.javac.util.List;
import org.junit.Before;
import org.junit.Test;

public abstract class ItemFinderTest {

    protected ItemFinder itemFinder;

    public abstract void setItemFinder();

    @Before
    public void setUp() throws Exception {
        setItemFinder();
    }

    @Test
    public void shouldFindItemsStartingByA() {
        final List<String> list = List.of("1", "B", "C", "A");
        assertEquals("A", itemFinder.findItem(list));
    }

    @Test
    public void shouldFindItemsStartingByB() {
        final List<String> list = List.of("1", "C", "B");
        assertEquals("B", itemFinder.findItem(list));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowWhenItemsDoNotStartByAorB() {
        final List<String> list = List.of("1", "2", "3");
        itemFinder.findItem(list);
    }

}