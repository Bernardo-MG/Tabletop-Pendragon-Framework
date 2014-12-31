package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.religion;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.pendragon.chargen.ReligionBonus;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.chargen.ReligionBonusDocumentInputProcessor;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.FileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;
import com.wandrell.util.parser.xml.input.JDOMStAXInputParser;

public final class ITReadReligionBonusDocumentInputProcessor {

    private ReligionBonus religion;

    public ITReadReligionBonusDocumentInputProcessor() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<ReligionBonus> parser;
        final JDOMDocumentInputProcessor<ReligionBonus> processor;

        processor = new ReligionBonusDocumentInputProcessor(TestServiceFactory
                .getInstance().getModelService());
        parser = new JDOMStAXInputParser<ReligionBonus>(processor);

        religion = parser.read(ResourceUtils
                .getClassPathInputStream(FileConf.RELIGION));
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
