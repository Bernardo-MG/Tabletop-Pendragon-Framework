package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.chargen;

import java.io.Reader;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.XMLFileParser;
import com.wandrell.tabletop.business.model.pendragon.chargen.ReligionTemplate;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.chargen.ReligionTemplateDocumentParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadReligionTemplateDocumentParser {

    private ReligionTemplate religion;

    public ITReadReligionTemplateDocumentParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, ReligionTemplate> parserDoc;

        parserDoc = new ReligionTemplateDocumentParser(TestServiceFactory
                .getInstance().getModelService());
        parserFile = new XMLFileParser();

        religion = parserDoc.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.RELIGION)));
    }

    @Test
    public final void testArmorBonus() {
        Assert.assertEquals(religion.getArmorBonus(), (Integer) 5);
    }

    @Test
    public final void testDamageBonus() {
        Assert.assertEquals(religion.getDamageBonus(), (Integer) 6);
    }

    @Test
    public final void testDamageDiceBonus() {
        Assert.assertEquals(religion.getDamageDiceBonus(), (Integer) 7);
    }

    @Test
    public final void testDerivedAttributesBonus() {
        Assert.assertEquals((Integer) religion.getDerivedAttributeBonus()
                .size(), (Integer) 2);

        Assert.assertTrue(religion.getDerivedAttributeBonus().containsKey(
                "damage_bonus"));
        Assert.assertEquals(
                religion.getDerivedAttributeBonus().get("damage_bonus"),
                (Integer) 3);

        Assert.assertTrue(religion.getDerivedAttributeBonus().containsKey(
                "hitpoints"));
        Assert.assertEquals(religion.getDerivedAttributeBonus()
                .get("hitpoints"), (Integer) 2);
    }

    @Test
    public final void testReligion() {
        Assert.assertEquals(religion.getReligion(), "test_religion");
    }

    @Test
    public final void testTraits() {
        Assert.assertEquals(religion.getReligiousTraits().size(), 5);

        Assert.assertTrue(religion.getReligiousTraits().contains("temperate"));
        Assert.assertTrue(religion.getReligiousTraits().contains("merciful"));
        Assert.assertTrue(religion.getReligiousTraits().contains("just"));
        Assert.assertTrue(religion.getReligiousTraits().contains("honest"));
        Assert.assertTrue(religion.getReligiousTraits().contains("chaste"));
    }

}
