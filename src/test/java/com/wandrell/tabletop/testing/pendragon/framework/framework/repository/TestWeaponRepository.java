package com.wandrell.tabletop.testing.pendragon.framework.framework.repository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.wandrell.data.repository.Repository;
import com.wandrell.tabletop.business.model.pendragon.inventory.Weapon;

public final class TestWeaponRepository implements Repository<Weapon> {

    private final Collection<Weapon> objects = new LinkedList<>();

    public TestWeaponRepository() {
        super();
    }

    @Override
    public final void add(final Weapon object) {
        objects.add(object);
    }

    @Override
    public final Collection<Weapon> getCollection(
            final Predicate<Weapon> predicate) {
        return objects.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public final void remove(final Weapon object) {}

    @Override
    public final void update(final Weapon object) {}

}
