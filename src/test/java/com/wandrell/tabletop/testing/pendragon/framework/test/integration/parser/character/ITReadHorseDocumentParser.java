package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.character;

import java.io.Reader;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.XMLFileParser;
import com.wandrell.tabletop.business.model.pendragon.character.Horse;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.character.HorseDocumentParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadHorseDocumentParser {

    private Horse horse;

    public ITReadHorseDocumentParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Horse> parserDoc;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        parserDoc = new HorseDocumentParser(modelService);
        parserFile = new XMLFileParser();

        horse = parserDoc.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.HORSE)));
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
