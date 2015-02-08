package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.dice.Dice;
import com.wandrell.tabletop.business.model.interval.DefaultInterval;
import com.wandrell.tabletop.business.model.interval.Interval;
import com.wandrell.tabletop.business.model.pendragon.character.Horse;
import com.wandrell.tabletop.business.model.pendragon.chargen.AdditionalBelongings;
import com.wandrell.tabletop.business.model.pendragon.chargen.AdditionalBelongingsTable;
import com.wandrell.tabletop.business.model.pendragon.inventory.Item;
import com.wandrell.tabletop.business.model.pendragon.inventory.Shield;
import com.wandrell.tabletop.business.model.pendragon.inventory.Weapon;
import com.wandrell.tabletop.business.model.pendragon.manor.Pet;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.DiceUtils;
import com.wandrell.util.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.util.repository.Repository;

public class AdditionalBelongingsTableDocumentDecoder implements
        JDOMDocumentDecoder<AdditionalBelongingsTable> {

    private final Repository<Horse>  horseRepository;
    private final Repository<Item>   itemRepository;
    private final ModelService       modelService;
    private final Repository<Pet>    petRepository;
    private final Repository<Shield> shieldRepository;
    private final Repository<Weapon> weaponRepository;

    public AdditionalBelongingsTableDocumentDecoder(final ModelService service,
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

    @Override
    public final AdditionalBelongingsTable decode(final Document doc) {
        final Map<Interval, AdditionalBelongings> intervalsMap;
        final Element root;
        final Element belongingsNode;
        final List<Integer> limits;
        final String name;
        Interval interval;
        Integer pos;

        root = doc.getRootElement();

        // Name
        name = root.getChildText(ModelXMLConf.NAME);

        belongingsNode = root.getChild(ModelXMLConf.BELONGINGS_TABLE);

        limits = new LinkedList<>();
        for (final Element belongings : belongingsNode.getChildren()) {
            limits.add(Integer.parseInt(belongings
                    .getChildText(ModelXMLConf.LOWER_LIMIT)));
        }

        pos = 0;
        intervalsMap = new LinkedHashMap<>();
        for (final Element belongings : belongingsNode.getChildren()) {
            if (pos < (limits.size() - 1)) {
                interval = new DefaultInterval(limits.get(pos),
                        limits.get(pos + 1) - 1);
            } else {
                interval = new DefaultInterval(limits.get(pos), 20);
            }
            pos++;

            intervalsMap.put(interval, readAdditionalBelongings(belongings
                    .getChild(ModelXMLConf.BELONGINGS)));
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

    private final AdditionalBelongings readAdditionalBelongings(
            final Element node) {
        final Element moneyNode;
        final Element rerollNode;
        final Element horseNode;
        final Element itemNode;
        final Element petNode;
        final Element shieldNode;
        final Element weaponsNode;
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

        choose = Boolean.valueOf(node.getChildText(ModelXMLConf.HAS_TO_CHOOSE));

        moneyNode = node.getChild(ModelXMLConf.MONEY);
        if (moneyNode == null) {
            moneyName = "";
            libra = 0;
            denarii = 0;
        } else {
            moneyName = moneyNode.getChildText(ModelXMLConf.NAME);
            libra = Integer
                    .parseInt(moneyNode.getChildText(ModelXMLConf.LIBRA));
            denarii = Integer.parseInt(moneyNode
                    .getChildText(ModelXMLConf.DENARII));
        }

        rerollNode = node.getChild(ModelXMLConf.REROLL);
        dice = new LinkedList<>();
        if (rerollNode == null) {
            rerollTable = "";
        } else {
            rerollTable = rerollNode.getChildText(ModelXMLConf.REROLL_TABLE);
            for (final Element reroll : rerollNode.getChild(
                    ModelXMLConf.REROLLS).getChildren()) {
                dice.add(DiceUtils.parseDice(reroll.getText()));
            }
        }

        horseNode = node.getChild(ModelXMLConf.HORSES);
        horses = new LinkedList<>();
        if (horseNode != null) {
            for (final Element horse : horseNode.getChildren()) {
                horses.addAll(getHorseRepository().getCollection(
                        h -> h.getHorseType().equals(horse.getText())));
            }
        }

        itemNode = node.getChild(ModelXMLConf.ITEMS);
        items = new LinkedList<>();
        if (itemNode != null) {
            for (final Element item : itemNode.getChildren()) {
                items.addAll(getItemRepository().getCollection(
                        i -> i.getName().equals(item.getText())));
            }
        }

        petNode = node.getChild(ModelXMLConf.PETS);
        pets = new LinkedList<>();
        if (petNode != null) {
            for (final Element pet : petNode.getChildren()) {
                pets.addAll(getPetRepository().getCollection(
                        p -> p.getName().equals(pet.getText())));
            }
        }

        shieldNode = node.getChild(ModelXMLConf.SHIELDS);
        shields = new LinkedList<>();
        if (shieldNode != null) {
            for (final Element shield : shieldNode.getChildren()) {
                shields.addAll(getShieldRepository().getCollection(
                        s -> s.getName().equals(shield.getText())));
            }
        }

        weaponsNode = node.getChild(ModelXMLConf.WEAPONS);
        weapons = new LinkedList<>();
        if (weaponsNode != null) {
            for (final Element weapon : weaponsNode.getChildren()) {
                weapons.addAll(getWeaponRepository().getCollection(
                        w -> w.getName().equals(weapon.getText())));
            }
        }

        return getModelService().getAdditionaBelongings(choose, moneyName,
                libra, denarii, rerollTable, dice, horses, items, pets,
                shields, weapons);
    }
}
