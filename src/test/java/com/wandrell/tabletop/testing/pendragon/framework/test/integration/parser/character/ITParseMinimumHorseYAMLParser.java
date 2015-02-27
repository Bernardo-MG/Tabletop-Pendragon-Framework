package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.character;

import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.character.Horse;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.character.HorseYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseMinimumHorseYAMLParser {

    private Horse horse;

    public ITParseMinimumHorseYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Horse> parser;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        parser = new HorseYAMLParser(modelService);

        horse = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.HORSE_MINIMUM));
    }

    @Test
    public final void testAttributes() {
        Assert.assertEquals(horse.getConstitution(), (Integer) 11);
        Assert.assertEquals(horse.getDexterity(), (Integer) 22);
        Assert.assertEquals(horse.getSize(), (Integer) 33);
        Assert.assertEquals(horse.getStrength(), (Integer) 44);
    }

    @Test
    public final void testDerivedAttributes() {
        Assert.assertEquals(horse.getDamage(), (Integer) 55);
        Assert.assertEquals(horse.getMovementRate(), (Integer) 66);
    }

    @Test
    public final void testFlags() {
        Assert.assertEquals(horse.isArmored(), (Boolean) false);
        Assert.assertEquals(horse.isCombatHorse(), (Boolean) false);
        Assert.assertEquals(horse.isHuntingHorse(), (Boolean) false);
    }

    @Test
    public final void testNaturalArmor() {
        Assert.assertEquals(horse.getNaturalArmor(), (Integer) 77);
    }

    @Test
    public final void testRace() {
        Assert.assertEquals(horse.getHorseType(), "test_horse");
    }

}
