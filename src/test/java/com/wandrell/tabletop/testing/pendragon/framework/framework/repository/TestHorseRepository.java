package com.wandrell.tabletop.testing.pendragon.framework.framework.repository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.wandrell.tabletop.business.model.pendragon.character.HorseCharacter;
import com.wandrell.util.repository.Repository;

public final class TestHorseRepository implements Repository<HorseCharacter> {

    private final Collection<HorseCharacter> objects = new LinkedList<>();

    public TestHorseRepository() {
        super();
    }

    @Override
    public final void add(final HorseCharacter object) {
        objects.add(object);
    }

    @Override
    public final Collection<HorseCharacter> getCollection(
            final Predicate<HorseCharacter> predicate) {
        return objects.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public final void remove(final HorseCharacter object) {}

    @Override
    public final void update(final HorseCharacter object) {}

}
