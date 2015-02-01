package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.character;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.pendragon.character.HorseCharacter;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.character.HorseDocumentDecoder;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.util.parser.xml.input.StAXInputParser;

public final class ITReadHorseDocumentDecoder {

    private HorseCharacter horse;

    public ITReadHorseDocumentDecoder() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<HorseCharacter> parser;
        final JDOMDocumentDecoder<HorseCharacter> decoder;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        decoder = new HorseDocumentDecoder(modelService);
        parser = new StAXInputParser<HorseCharacter>(decoder);

        horse = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.HORSE));
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
