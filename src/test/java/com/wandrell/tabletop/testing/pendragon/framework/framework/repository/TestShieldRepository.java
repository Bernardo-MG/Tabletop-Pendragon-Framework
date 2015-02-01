package com.wandrell.tabletop.testing.pendragon.framework.framework.repository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.wandrell.tabletop.business.model.pendragon.inventory.Shield;
import com.wandrell.util.repository.Repository;

public final class TestShieldRepository implements Repository<Shield> {

    private final Collection<Shield> objects = new LinkedList<>();

    public TestShieldRepository() {
        super();
    }

    @Override
    public final void add(final Shield object) {
        objects.add(object);
    }

    @Override
    public final Collection<Shield> getCollection(
            final Predicate<Shield> predicate) {
        return objects.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public final void remove(final Shield object) {}

    @Override
    public final void update(final Shield object) {}

}
