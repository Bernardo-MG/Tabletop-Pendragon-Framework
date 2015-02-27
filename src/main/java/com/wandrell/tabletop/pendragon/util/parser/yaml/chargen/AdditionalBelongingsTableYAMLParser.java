package com.wandrell.tabletop.pendragon.util.parser.yaml.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.pattern.repository.Repository.Filter;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.dice.StringDiceParser;
import com.wandrell.tabletop.interval.DefaultInterval;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.pendragon.model.character.Horse;
import com.wandrell.tabletop.pendragon.model.chargen.AdditionalBelongings;
import com.wandrell.tabletop.pendragon.model.chargen.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.model.inventory.Item;
import com.wandrell.tabletop.pendragon.model.inventory.Shield;
import com.wandrell.tabletop.pendragon.model.inventory.Weapon;
import com.wandrell.tabletop.pendragon.model.manor.Pet;
import com.wandrell.tabletop.pendragon.service.ModelService;

public class AdditionalBelongingsTableYAMLParser implements
        Parser<Reader, AdditionalBelongingsTable> {

    private final Repository<Horse>  horseRepository;
    private final Repository<Item>   itemRepository;
    private final ModelService       modelService;
    private final Repository<Pet>    petRepository;
    private final Repository<Shield> shieldRepository;
    private final Repository<Weapon> weaponRepository;

    public AdditionalBelongingsTableYAMLParser(final ModelService service,
            final Repository<Horse> horseRepository,
            final Repository<Item> itemRepository,
            final Repository<Pet> petRepository,
            final Repository<Shield> shieldRepository,
            final Repository<Weapon> weaponRepository) {
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

    private final Repository<Horse> getHorseRepository() {
        return horseRepository;
    }

    private final Repository<Item> getItemRepository() {
        return itemRepository;
    }

    private final ModelService getModelService() {
        return modelService;
    }

    private final Repository<Pet> getPetRepository() {
        return petRepository;
    }

    private final Repository<Shield> getShieldRepository() {
        return shieldRepository;
    }

    private final Repository<Weapon> getWeaponRepository() {
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
        final Collection<Dice> dice;
        final Collection<Horse> horses;
        final Collection<Item> items;
        final Collection<Pet> pets;
        final Collection<Shield> shields;
        final Collection<Weapon> weapons;
        final Boolean choose;
        final Parser<String, Dice> diceParser;

        diceParser = new StringDiceParser();

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
        dice = new LinkedList<Dice>();
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
                        new Filter<Horse>() {

                            @Override
                            public final Boolean isValid(final Horse entity) {
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
                        new Filter<Item>() {

                            @Override
                            public final Boolean isValid(final Item entity) {
                                return entity.getName().equals(item);
                            }

                        }));
            }
        }

        petNames = (Collection<String>) belongings.get("pets");
        pets = new LinkedList<Pet>();
        if (petNames != null) {
            for (final String pet : petNames) {
                pets.addAll(getPetRepository().getCollection(new Filter<Pet>() {

                    @Override
                    public final Boolean isValid(final Pet entity) {
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
                        new Filter<Shield>() {

                            @Override
                            public final Boolean isValid(final Shield entity) {
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
                        new Filter<Weapon>() {

                            @Override
                            public final Boolean isValid(final Weapon entity) {
                                return entity.getName().equals(weapon);
                            }

                        }));
            }
        }

        return getModelService().getAdditionaBelongings(choose, moneyName,
                libra, denarii, rerollTable, dice, horses, items, pets,
                shields, weapons);
    }
}
