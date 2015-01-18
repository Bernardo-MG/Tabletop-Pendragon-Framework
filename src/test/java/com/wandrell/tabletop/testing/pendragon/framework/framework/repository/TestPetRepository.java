package com.wandrell.tabletop.testing.pendragon.framework.framework.repository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.wandrell.data.repository.Repository;
import com.wandrell.tabletop.business.model.pendragon.manor.Pet;

public final class TestPetRepository implements Repository<Pet> {

    private final Collection<Pet> objects = new LinkedList<>();

    public TestPetRepository() {
        super();
    }

    @Override
    public final void add(final Pet object) {
        objects.add(object);
    }

    @Override
    public final Collection<Pet> getCollection(final Predicate<Pet> predicate) {
        return objects.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public final void remove(final Pet object) {}

    @Override
    public final void update(final Pet object) {}

}
