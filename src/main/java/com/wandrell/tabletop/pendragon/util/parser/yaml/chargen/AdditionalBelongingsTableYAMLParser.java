package com.wandrell.tabletop.pendragon.util.parser.yaml.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.google.common.base.Predicate;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.tabletop.dice.notation.DiceFormula;
import com.wandrell.tabletop.dice.parser.DiceFormulaParser;
import com.wandrell.tabletop.interval.DefaultInterval;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.pendragon.model.character.Horse;
import com.wandrell.tabletop.pendragon.model.chargen.inventory.AdditionalBelongings;
import com.wandrell.tabletop.pendragon.model.chargen.inventory.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.model.inventory.Item;
import com.wandrell.tabletop.pendragon.model.inventory.armor.Shield;
import com.wandrell.tabletop.pendragon.model.inventory.weapon.Weapon;
import com.wandrell.tabletop.pendragon.model.manor.Pet;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;

public class AdditionalBelongingsTableYAMLParser implements
        Parser<Reader, AdditionalBelongingsTable> {

    private final FilteredRepository<Horse, Predicate<Horse>>   horseRepository;
    private final FilteredRepository<Item, Predicate<Item>>     itemRepository;
    private final ModelConstructorService                       modelService;
    private final FilteredRepository<Pet, Predicate<Pet>>       petRepository;
    private final FilteredRepository<Shield, Predicate<Shield>> shieldRepository;
    private final FilteredRepository<Weapon, Predicate<Weapon>> weaponRepository;

    public AdditionalBelongingsTableYAMLParser(
            final ModelConstructorService service,
            final FilteredRepository<Horse, Predicate<Horse>> horseRepository,
            final FilteredRepository<Item, Predicate<Item>> itemRepository,
            final FilteredRepository<Pet, Predicate<Pet>> petRepository,
            final FilteredRepository<Shield, Predicate<Shield>> shieldRepository,
            final FilteredRepository<Weapon, Predicate<Weapon>> weaponRepository) {
        super();

        modelService = service;

        this.horseRepository = horseRepository;
        this.itemRepository = itemRepository;
        this.petRepository = petRepository;
        this.shieldRepository = shieldRepository;
        this.weaponRepository = weaponRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final AdditionalBelongingsTable parse(final Reader reader)
            throws Exception {
        final Yaml yaml;
        final Map<String, Object> values;
        final Map<Interval, AdditionalBelongings> intervalsMap;
        final List<Integer> limits;
        final String name;
        final Collection<Map<String, Object>> belongings;
        Interval interval;
        Integer pos;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        name = (String) values.get("name");

        belongings = (Collection<Map<String, Object>>) values
                .get("belongings_table");

        limits = new LinkedList<Integer>();
        if (belongings != null) {
            for (final Map<String, Object> row : belongings) {
                limits.add((Integer) row.get("lower_limit"));
            }
        }

        pos = 0;
        intervalsMap = new LinkedHashMap<Interval, AdditionalBelongings>();
        if (belongings != null) {
            for (final Map<String, Object> row : belongings) {
                if (pos < (limits.size() - 1)) {
                    interval = new DefaultInterval(limits.get(pos),
                            limits.get(pos + 1) - 1);
                } else {
                    interval = new DefaultInterval(limits.get(pos), 20);
                }
                pos++;

                intervalsMap.put(interval,
                        readAdditionalBelongings((Map<String, Object>) row
                                .get("belongings")));
            }
        }

        return getModelService().getAdditionalBelongingsTable(name,
                intervalsMap);
    }

    private final FilteredRepository<Horse, Predicate<Horse>>
            getHorseRepository() {
        return horseRepository;
    }

    private final FilteredRepository<Item, Predicate<Item>> getItemRepository() {
        return itemRepository;
    }

    private final ModelConstructorService getModelService() {
        return modelService;
    }

    private final FilteredRepository<Pet, Predicate<Pet>> getPetRepository() {
        return petRepository;
    }

    private final FilteredRepository<Shield, Predicate<Shield>>
            getShieldRepository() {
        return shieldRepository;
    }

    private final FilteredRepository<Weapon, Predicate<Weapon>>
            getWeaponRepository() {
        return weaponRepository;
    }

    @SuppressWarnings("unchecked")
    private final AdditionalBelongings readAdditionalBelongings(
            final Map<String, Object> belongings) throws Exception {
        final Map<String, Object> mapMoney;
        final Map<String, Object> mapReroll;
        final Collection<String> horseNames;
        final Collection<String> itemNames;
        final Collection<String> petNames;
        final Collection<String> shieldNames;
        final Collection<String> weaponsNames;
        final String moneyName;
        final Integer libra;
        final Integer denarii;
        final String rerollTable;
        final Collection<DiceFormula> dice;
        final Collection<Horse> horses;
        final Collection<Item> items;
        final Collection<Pet> pets;
        final Collection<Shield> shields;
        final Collection<Weapon> weapons;
        final Boolean choose;
        final Parser<String, DiceFormula> diceParser;

        diceParser = new DiceFormulaParser();

        choose = (Boolean) belongings.get("has_to_choose");

        mapMoney = (Map<String, Object>) belongings.get("money");
        if (mapMoney == null) {
            moneyName = "";
            libra = 0;
            denarii = 0;
        } else {
            moneyName = (String) mapMoney.get("name");
            libra = (Integer) mapMoney.get("libra");
            denarii = (Integer) mapMoney.get("denarii");
        }

        mapReroll = (Map<String, Object>) belongings.get("reroll");
        dice = new LinkedList<DiceFormula>();
        if (mapReroll == null) {
            rerollTable = "";
        } else {
            rerollTable = (String) mapReroll.get("reroll_table");
            for (final String reroll : (Collection<String>) mapReroll
                    .get("rerolls")) {
                dice.add(diceParser.parse(reroll));
            }
        }

        horseNames = (Collection<String>) belongings.get("horses");
        horses = new LinkedList<Horse>();
        if (horseNames != null) {
            for (final String horse : horseNames) {
                horses.addAll(getHorseRepository().getCollection(
                        new Predicate<Horse>() {

                            @Override
                            public final boolean apply(final Horse entity) {
                                return entity.getHorseType().equals(horse);
                            }

                        }));
            }
        }

        itemNames = (Collection<String>) belongings.get("items");
        items = new LinkedList<Item>();
        if (itemNames != null) {
            for (final String item : itemNames) {
                items.addAll(getItemRepository().getCollection(
                        new Predicate<Item>() {

                            @Override
                            public final boolean apply(final Item entity) {
                                return entity.getName().equals(item);
                            }

                        }));
            }
        }

        petNames = (Collection<String>) belongings.get("pets");
        pets = new LinkedList<Pet>();
        if (petNames != null) {
            for (final String pet : petNames) {
                pets.addAll(getPetRepository().getCollection(
                        new Predicate<Pet>() {

                            @Override
                            public final boolean apply(final Pet entity) {
                                return entity.getName().equals(pet);
                            }

                        }));
            }
        }

        shieldNames = (Collection<String>) belongings.get("shields");
        shields = new LinkedList<Shield>();
        if (shieldNames != null) {
            for (final String shield : shieldNames) {
                shields.addAll(getShieldRepository().getCollection(
                        new Predicate<Shield>() {

                            @Override
                            public final boolean apply(final Shield entity) {
                                return entity.getName().equals(shield);
                            }

                        }));
            }
        }

        weaponsNames = (Collection<String>) belongings.get("weapons");
        weapons = new LinkedList<Weapon>();
        if (weaponsNames != null) {
            for (final String weapon : weaponsNames) {
                weapons.addAll(getWeaponRepository().getCollection(
                        new Predicate<Weapon>() {

                            @Override
                            public final boolean apply(final Weapon entity) {
                                return entity.getName().equals(weapon);
                            }

                        }));
            }
        }

        return getModelService().getAdditionaBelongings(choose, moneyName,
                getModelService().getMoney(libra, denarii), rerollTable, dice,
                horses, items, pets, shields, weapons);
    }
}
