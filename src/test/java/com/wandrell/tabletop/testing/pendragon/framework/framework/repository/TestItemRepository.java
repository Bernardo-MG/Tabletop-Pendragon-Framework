package com.wandrell.tabletop.testing.pendragon.framework.framework.repository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.wandrell.tabletop.business.model.pendragon.inventory.Item;
import com.wandrell.util.repository.Repository;

public final class TestItemRepository implements Repository<Item> {

    private final Collection<Item> objects = new LinkedList<>();

    public TestItemRepository() {
        super();
    }

    @Override
    public final void add(final Item object) {
        objects.add(object);
    }

    @Override
    public final Collection<Item>
            getCollection(final Predicate<Item> predicate) {
        return objects.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public final void remove(final Item object) {}

    @Override
    public final void update(final Item object) {}

}
