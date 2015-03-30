package com.wandrell.tabletop.testing.pendragon.test.integration.parser.character;

import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.character.Horse;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.character.HorseYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseHorseYAMLParser {

    private Horse horse;

    public ITParseHorseYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Horse> parser;
        final ModelConstructorService modelService;

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new HorseYAMLParser(modelService);

        horse = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.HORSE));
    }

    @Test
    public final void testAttributes() {
        Assert.assertEquals(horse.getAttributes().getConstitution(),
                (Integer) 11);
        Assert.assertEquals(horse.getAttributes().getDexterity(), (Integer) 22);
        Assert.assertEquals(horse.getAttributes().getSize(), (Integer) 33);
        Assert.assertEquals(horse.getAttributes().getStrength(), (Integer) 44);
    }

    @Test
    public final void testDerivedAttributes() {
        Assert.assertEquals(horse.getDerivedAttributes().getDamage(),
                (Integer) 55);
        Assert.assertEquals(horse.getDerivedAttributes().getMoveRate(),
                (Integer) 66);
    }

    @Test
    public final void testFlags() {
        Assert.assertEquals(horse.isArmored(), (Boolean) true);
        Assert.assertEquals(horse.isCombatHorse(), (Boolean) false);
        Assert.assertEquals(horse.isHuntingHorse(), (Boolean) true);
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
