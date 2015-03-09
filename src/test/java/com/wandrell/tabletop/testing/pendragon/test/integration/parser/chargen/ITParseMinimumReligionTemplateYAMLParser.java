package com.wandrell.tabletop.testing.pendragon.test.integration.parser.chargen;

import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.ReligionTemplate;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.ReligionTemplateYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseMinimumReligionTemplateYAMLParser {

    private ReligionTemplate religion;

    public ITParseMinimumReligionTemplateYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, ReligionTemplate> parser;

        parser = new ReligionTemplateYAMLParser(TestServiceFactory
                .getInstance().getModelConstructorService());

        religion = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.RELIGION_MINIMUM));
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
        Assert.assertTrue(religion.getDerivedAttributeBonus().isEmpty());
    }

    @Test
    public final void testReligion() {
        Assert.assertEquals(religion.getName(), "test_religion");
    }

    @Test
    public final void testTraits() {
        Assert.assertTrue(religion.getReligiousTraits().isEmpty());
    }

}
