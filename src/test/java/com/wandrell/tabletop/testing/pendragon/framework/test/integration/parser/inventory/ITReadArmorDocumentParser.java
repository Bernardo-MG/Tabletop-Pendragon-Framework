package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.inventory;

import java.io.Reader;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.XMLFileParser;
import com.wandrell.tabletop.business.model.pendragon.inventory.Armor;
import com.wandrell.tabletop.business.model.pendragon.inventory.ArmorType;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.inventory.ArmorDocumentParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadArmorDocumentParser {

    private Armor armor;

    public ITReadArmorDocumentParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Armor> parserDoc;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        parserDoc = new ArmorDocumentParser(modelService);
        parserFile = new XMLFileParser();

        armor = parserDoc.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.ARMOR)));
    }

    @Test
    public final void testArmorValue() {
        Assert.assertEquals(armor.getArmorValue(), (Integer) 11);
    }

    @Test
    public final void testDexterityModifier() {
        Assert.assertEquals(armor.getDexterityModifier(), (Integer) (0 - 22));
    }

    @Test
    public final void testHeavyLoad() {
        Assert.assertTrue(armor.isHeavyLoad());
    }

    @Test
    public final void testName() {
        Assert.assertEquals(armor.getName(), "test_armor");
    }

    @Test
    public final void testType() {
        Assert.assertEquals(armor.getArmorType(), ArmorType.LEATHER);
    }

}
