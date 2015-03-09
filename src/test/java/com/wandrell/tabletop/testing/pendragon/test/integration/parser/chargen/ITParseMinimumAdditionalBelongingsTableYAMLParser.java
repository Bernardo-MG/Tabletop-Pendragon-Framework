package com.wandrell.tabletop.testing.pendragon.test.integration.parser.chargen;

import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.pendragon.model.character.Horse;
import com.wandrell.tabletop.pendragon.model.chargen.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.model.inventory.Item;
import com.wandrell.tabletop.pendragon.model.inventory.Shield;
import com.wandrell.tabletop.pendragon.model.inventory.Weapon;
import com.wandrell.tabletop.pendragon.model.manor.Pet;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.AdditionalBelongingsTableYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseMinimumAdditionalBelongingsTableYAMLParser {

    private AdditionalBelongingsTable table;

    public ITParseMinimumAdditionalBelongingsTableYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, AdditionalBelongingsTable> parser;
        final TestServiceFactory factory;
        final ModelConstructorService modelService;
        final Repository<Horse> horseRepository;
        final Repository<Item> itemRepository;
        final Repository<Pet> petRepository;
        final Repository<Shield> shieldRepository;
        final Repository<Weapon> weaponRepository;

        factory = TestServiceFactory.getInstance();

        modelService = factory.getModelConstructorService();

        horseRepository = factory.getHorseRepository();
        itemRepository = factory.getItemRepository();
        petRepository = factory.getPetRepository();
        shieldRepository = factory.getShieldRepository();
        weaponRepository = factory.getWeaponRepository();

        parser = new AdditionalBelongingsTableYAMLParser(modelService,
                horseRepository, itemRepository, petRepository,
                shieldRepository, weaponRepository);

        table = parser
                .parse(ResourceUtils
                        .getClassPathReader(TestModelFileConf.ADDITIONAL_BELONGINGS_MINIMUM));
    }

    @Test
    public final void testIntervals() {
        Assert.assertTrue(table.getIntervals().isEmpty());
    }

    @Test
    public final void testName() {
        Assert.assertEquals(table.getName(), "test_additional_belongings");
    }

}
