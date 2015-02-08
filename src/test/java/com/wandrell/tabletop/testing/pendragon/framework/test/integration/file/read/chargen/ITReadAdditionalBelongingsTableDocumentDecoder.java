package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.chargen;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.dice.Dice;
import com.wandrell.tabletop.business.model.interval.Interval;
import com.wandrell.tabletop.business.model.pendragon.character.Horse;
import com.wandrell.tabletop.business.model.pendragon.chargen.AdditionalBelongings;
import com.wandrell.tabletop.business.model.pendragon.chargen.AdditionalBelongingsTable;
import com.wandrell.tabletop.business.model.pendragon.inventory.Item;
import com.wandrell.tabletop.business.model.pendragon.inventory.Shield;
import com.wandrell.tabletop.business.model.pendragon.inventory.Weapon;
import com.wandrell.tabletop.business.model.pendragon.manor.Pet;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.chargen.AdditionalBelongingsTableDocumentDecoder;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.util.parser.xml.input.StAXInputParser;
import com.wandrell.util.repository.Repository;

public final class ITReadAdditionalBelongingsTableDocumentDecoder {

    private AdditionalBelongingsTable table;

    public ITReadAdditionalBelongingsTableDocumentDecoder() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<AdditionalBelongingsTable> parser;
        final JDOMDocumentDecoder<AdditionalBelongingsTable> decoder;
        final TestServiceFactory factory;
        final ModelService modelService;
        final Repository<Horse> horseRepository;
        final Repository<Item> itemRepository;
        final Repository<Pet> petRepository;
        final Repository<Shield> shieldRepository;
        final Repository<Weapon> weaponRepository;

        factory = TestServiceFactory.getInstance();

        modelService = factory.getModelService();

        horseRepository = factory.getHorseRepository();
        itemRepository = factory.getItemRepository();
        petRepository = factory.getPetRepository();
        shieldRepository = factory.getShieldRepository();
        weaponRepository = factory.getWeaponRepository();

        decoder = new AdditionalBelongingsTableDocumentDecoder(modelService,
                horseRepository, itemRepository, petRepository,
                shieldRepository, weaponRepository);
        parser = new StAXInputParser<AdditionalBelongingsTable>(decoder);

        table = parser
                .read(ResourceUtils
                        .getClassPathInputStream(TestModelFileConf.ADDITIONAL_BELONGINGS));
    }

    @Test
    public final void testAdditionalBelongings_Fifth() {
        final AdditionalBelongings belongings;
        final Iterator<AdditionalBelongings> itrValues;
        final Iterator<Shield> itrShield;

        itrValues = table.getIntervals().values().iterator();
        itrValues.next();
        itrValues.next();
        itrValues.next();
        itrValues.next();
        belongings = itrValues.next();

        Assert.assertEquals(belongings.hasToChoose(), (Boolean) false);

        Assert.assertEquals(belongings.getMoneyName(), "");
        Assert.assertEquals(belongings.getMoney().getLibra(), (Integer) 0);
        Assert.assertEquals(belongings.getMoney().getDenarii(), (Integer) 0);

        Assert.assertEquals(belongings.getRerollTable(), "");
        Assert.assertEquals(belongings.getRerolls().size(), 0);

        Assert.assertEquals(belongings.getHorses().size(), 0);
        Assert.assertEquals(belongings.getItems().size(), 0);
        Assert.assertEquals(belongings.getPets().size(), 0);

        Assert.assertEquals(belongings.getShields().size(), 2);

        itrShield = belongings.getShields().iterator();
        Assert.assertEquals(itrShield.next().getName(), "shield_1");
        Assert.assertEquals(itrShield.next().getName(), "shield_2");

        Assert.assertEquals(belongings.getWeapons().size(), 0);
    }

    @Test
    public final void testAdditionalBelongings_First() {
        final AdditionalBelongings belongings;
        final Iterator<AdditionalBelongings> itrValues;

        itrValues = table.getIntervals().values().iterator();
        belongings = itrValues.next();

        Assert.assertEquals(belongings.hasToChoose(), (Boolean) false);

        Assert.assertEquals(belongings.getMoneyName(), "test_money");
        Assert.assertEquals(belongings.getMoney().getLibra(), (Integer) 11);
        Assert.assertEquals(belongings.getMoney().getDenarii(), (Integer) 22);

        Assert.assertEquals(belongings.getRerollTable(), "");
        Assert.assertEquals(belongings.getRerolls().size(), 0);

        Assert.assertEquals(belongings.getHorses().size(), 0);
        Assert.assertEquals(belongings.getItems().size(), 0);
        Assert.assertEquals(belongings.getPets().size(), 0);
        Assert.assertEquals(belongings.getShields().size(), 0);
        Assert.assertEquals(belongings.getWeapons().size(), 0);
    }

    @Test
    public final void testAdditionalBelongings_Fourth() {
        final AdditionalBelongings belongings;
        final Iterator<AdditionalBelongings> itrValues;
        final Iterator<Item> itrItem;

        itrValues = table.getIntervals().values().iterator();
        itrValues.next();
        itrValues.next();
        itrValues.next();
        belongings = itrValues.next();

        Assert.assertEquals(belongings.hasToChoose(), (Boolean) false);

        Assert.assertEquals(belongings.getMoneyName(), "");
        Assert.assertEquals(belongings.getMoney().getLibra(), (Integer) 0);
        Assert.assertEquals(belongings.getMoney().getDenarii(), (Integer) 0);

        Assert.assertEquals(belongings.getRerollTable(), "");
        Assert.assertEquals(belongings.getRerolls().size(), 0);

        Assert.assertEquals(belongings.getHorses().size(), 0);

        Assert.assertEquals(belongings.getItems().size(), 2);

        itrItem = belongings.getItems().iterator();
        Assert.assertEquals(itrItem.next().getName(), "item_1");
        Assert.assertEquals(itrItem.next().getName(), "item_2");

        Assert.assertEquals(belongings.getPets().size(), 0);
        Assert.assertEquals(belongings.getShields().size(), 0);
        Assert.assertEquals(belongings.getWeapons().size(), 0);
    }

    @Test
    public final void testAdditionalBelongings_Second() {
        final AdditionalBelongings belongings;
        final Iterator<AdditionalBelongings> itrValues;
        final Iterator<Dice> itrDice;

        itrValues = table.getIntervals().values().iterator();
        itrValues.next();
        belongings = itrValues.next();

        Assert.assertEquals(belongings.hasToChoose(), (Boolean) false);

        Assert.assertEquals(belongings.getMoneyName(), "");
        Assert.assertEquals(belongings.getMoney().getLibra(), (Integer) 0);
        Assert.assertEquals(belongings.getMoney().getDenarii(), (Integer) 0);

        Assert.assertEquals(belongings.getRerollTable(), "table_1");
        Assert.assertEquals(belongings.getRerolls().size(), 2);

        itrDice = belongings.getRerolls().iterator();
        Assert.assertEquals(itrDice.next().getTextValue(), "1d6");
        Assert.assertEquals(itrDice.next().getTextValue(), "1d20+1");

        Assert.assertEquals(belongings.getHorses().size(), 0);
        Assert.assertEquals(belongings.getItems().size(), 0);
        Assert.assertEquals(belongings.getPets().size(), 0);
        Assert.assertEquals(belongings.getShields().size(), 0);
        Assert.assertEquals(belongings.getWeapons().size(), 0);
    }

    @Test
    public final void testAdditionalBelongings_Seventh() {
        final AdditionalBelongings belongings;
        final Iterator<AdditionalBelongings> itrValues;
        final Iterator<Pet> itrPet;

        itrValues = table.getIntervals().values().iterator();
        itrValues.next();
        itrValues.next();
        itrValues.next();
        itrValues.next();
        itrValues.next();
        itrValues.next();
        belongings = itrValues.next();

        Assert.assertEquals(belongings.hasToChoose(), (Boolean) false);

        Assert.assertEquals(belongings.getMoneyName(), "");
        Assert.assertEquals(belongings.getMoney().getLibra(), (Integer) 0);
        Assert.assertEquals(belongings.getMoney().getDenarii(), (Integer) 0);

        Assert.assertEquals(belongings.getRerollTable(), "");
        Assert.assertEquals(belongings.getRerolls().size(), 0);

        Assert.assertEquals(belongings.getHorses().size(), 0);
        Assert.assertEquals(belongings.getItems().size(), 0);

        Assert.assertEquals(belongings.getPets().size(), 2);

        itrPet = belongings.getPets().iterator();
        Assert.assertEquals(itrPet.next().getName(), "pet_1");
        Assert.assertEquals(itrPet.next().getName(), "pet_2");

        Assert.assertEquals(belongings.getShields().size(), 0);
        Assert.assertEquals(belongings.getWeapons().size(), 0);
    }

    @Test
    public final void testAdditionalBelongings_Sixth() {
        final AdditionalBelongings belongings;
        final Iterator<AdditionalBelongings> itrValues;
        final Iterator<Weapon> itrWeapon;

        itrValues = table.getIntervals().values().iterator();
        itrValues.next();
        itrValues.next();
        itrValues.next();
        itrValues.next();
        itrValues.next();
        belongings = itrValues.next();

        Assert.assertEquals(belongings.hasToChoose(), (Boolean) false);

        Assert.assertEquals(belongings.getMoneyName(), "");
        Assert.assertEquals(belongings.getMoney().getLibra(), (Integer) 0);
        Assert.assertEquals(belongings.getMoney().getDenarii(), (Integer) 0);

        Assert.assertEquals(belongings.getRerollTable(), "");
        Assert.assertEquals(belongings.getRerolls().size(), 0);

        Assert.assertEquals(belongings.getHorses().size(), 0);
        Assert.assertEquals(belongings.getItems().size(), 0);
        Assert.assertEquals(belongings.getPets().size(), 0);
        Assert.assertEquals(belongings.getShields().size(), 0);

        Assert.assertEquals(belongings.getWeapons().size(), 2);

        itrWeapon = belongings.getWeapons().iterator();
        Assert.assertEquals(itrWeapon.next().getName(), "weapon_1");
        Assert.assertEquals(itrWeapon.next().getName(), "weapon_2");
    }

    @Test
    public final void testAdditionalBelongings_Third() {
        final AdditionalBelongings belongings;
        final Iterator<AdditionalBelongings> itrValues;
        final Iterator<Horse> itrHorse;

        itrValues = table.getIntervals().values().iterator();
        itrValues.next();
        itrValues.next();
        belongings = itrValues.next();

        Assert.assertEquals(belongings.hasToChoose(), (Boolean) true);

        Assert.assertEquals(belongings.getMoneyName(), "");
        Assert.assertEquals(belongings.getMoney().getLibra(), (Integer) 0);
        Assert.assertEquals(belongings.getMoney().getDenarii(), (Integer) 0);

        Assert.assertEquals(belongings.getRerollTable(), "");
        Assert.assertEquals(belongings.getRerolls().size(), 0);

        Assert.assertEquals(belongings.getHorses().size(), 2);

        itrHorse = belongings.getHorses().iterator();
        Assert.assertEquals(itrHorse.next().getHorseType(), "horse_1");
        Assert.assertEquals(itrHorse.next().getHorseType(), "horse_2");

        Assert.assertEquals(belongings.getItems().size(), 0);
        Assert.assertEquals(belongings.getPets().size(), 0);
        Assert.assertEquals(belongings.getShields().size(), 0);
        Assert.assertEquals(belongings.getWeapons().size(), 0);
    }

    @Test
    public final void testIntervals() {
        final Iterator<Interval> itr;
        Interval interval;

        Assert.assertEquals(table.getIntervals().size(), 7);

        itr = table.getIntervals().keySet().iterator();

        interval = itr.next();
        Assert.assertEquals(interval.getLowerLimit(), (Integer) 1);
        Assert.assertEquals(interval.getUpperLimit(), (Integer) 4);

        interval = itr.next();
        Assert.assertEquals(interval.getLowerLimit(), (Integer) 5);
        Assert.assertEquals(interval.getUpperLimit(), (Integer) 6);

        interval = itr.next();
        Assert.assertEquals(interval.getLowerLimit(), (Integer) 7);
        Assert.assertEquals(interval.getUpperLimit(), (Integer) 9);

        interval = itr.next();
        Assert.assertEquals(interval.getLowerLimit(), (Integer) 10);
        Assert.assertEquals(interval.getUpperLimit(), (Integer) 11);

        interval = itr.next();
        Assert.assertEquals(interval.getLowerLimit(), (Integer) 12);
        Assert.assertEquals(interval.getUpperLimit(), (Integer) 12);

        interval = itr.next();
        Assert.assertEquals(interval.getLowerLimit(), (Integer) 13);
        Assert.assertEquals(interval.getUpperLimit(), (Integer) 14);

        interval = itr.next();
        Assert.assertEquals(interval.getLowerLimit(), (Integer) 15);
        Assert.assertEquals(interval.getUpperLimit(), (Integer) 20);
    }

    @Test
    public final void testName() {
        Assert.assertEquals(table.getName(), "test_additional_belongings");
    }

}
