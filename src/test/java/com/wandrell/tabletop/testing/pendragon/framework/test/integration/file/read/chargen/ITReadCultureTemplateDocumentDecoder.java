package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.chargen;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.dice.Dice;
import com.wandrell.tabletop.business.model.pendragon.chargen.AdditionalBelongingsTable;
import com.wandrell.tabletop.business.model.pendragon.chargen.CultureTemplate;
import com.wandrell.tabletop.business.model.pendragon.chargen.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.business.model.skill.DefaultNameAndDescriptor;
import com.wandrell.tabletop.business.model.skill.NameAndDescriptor;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.chargen.CultureDocumentDecoder;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.util.parser.xml.input.StAXInputParser;
import com.wandrell.util.repository.Repository;

public final class ITReadCultureTemplateDocumentDecoder {

    private CultureTemplate culture;

    public ITReadCultureTemplateDocumentDecoder() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<CultureTemplate> parser;
        final JDOMDocumentDecoder<CultureTemplate> decoder;
        final TestServiceFactory factory;
        final ModelService modelService;
        final Repository<AdditionalBelongingsTable> belongingsRepository;
        final Repository<FamilyCharacteristicTemplate> characteristicRepository;

        factory = TestServiceFactory.getInstance();

        modelService = factory.getModelService();

        belongingsRepository = factory.getAdditionalBelongingsTableRepository();
        characteristicRepository = factory
                .getFamilyCharacteristicTemplateRepository();

        decoder = new CultureDocumentDecoder(modelService,
                characteristicRepository, belongingsRepository);
        parser = new StAXInputParser<CultureTemplate>(decoder);

        culture = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.CULTURE));
    }

    @Test
    public final void testAttributesBonus_Female() {
        final Map<String, Integer> attributes;

        attributes = culture.getFemaleTemplate().getAttributes();

        Assert.assertEquals(attributes.size(), 1);

        Assert.assertEquals(attributes.get("attribute_3"), (Integer) 3);
    }

    @Test
    public final void testAttributesBonus_Male() {
        final Map<String, Integer> attributes;

        attributes = culture.getMaleTemplate().getAttributes();

        Assert.assertEquals(attributes.size(), 2);

        Assert.assertEquals(attributes.get("attribute_1"), (Integer) 1);
        Assert.assertEquals(attributes.get("attribute_2"), (Integer) 2);
    }

    @Test
    public final void testAttributesRandom_Female() {
        final Map<String, Dice> attributes;

        attributes = culture.getFemaleTemplate().getAttributesRandom();

        Assert.assertEquals(attributes.size(), 1);

        Assert.assertEquals(attributes.get("attribute_3").getTextValue(),
                "10d2");
    }

    @Test
    public final void testAttributesRandom_Male() {
        final Map<String, Dice> attributes;

        attributes = culture.getMaleTemplate().getAttributesRandom();

        Assert.assertEquals(attributes.size(), 2);

        Assert.assertEquals(attributes.get("attribute_1").getTextValue(), "1d6");
        Assert.assertEquals(attributes.get("attribute_2").getTextValue(), "2d6");
    }

    @Test
    public final void testDirectedTraitsBonus_Female() {
        final Map<NameAndDescriptor, Integer> attributes;
        NameAndDescriptor key;

        attributes = culture.getFemaleTemplate().getDirectedTraits();

        Assert.assertEquals(attributes.size(), 1);

        key = new DefaultNameAndDescriptor("directed_3", "");
        Assert.assertEquals(attributes.get(key), (Integer) 31);
    }

    @Test
    public final void testDirectedTraitsBonus_Male() {
        final Map<NameAndDescriptor, Integer> attributes;
        NameAndDescriptor key;

        attributes = culture.getMaleTemplate().getDirectedTraits();

        Assert.assertEquals(attributes.size(), 2);

        key = new DefaultNameAndDescriptor("directed_1", "");
        Assert.assertEquals(attributes.get(key), (Integer) 30);

        key = new DefaultNameAndDescriptor("directed_2", "descriptor_2");
        Assert.assertEquals(attributes.get(key), (Integer) 1);
    }

    @Test
    public final void testFamilyCharacteristic() {
        Assert.assertEquals(culture.getMaleFamilyCharacteristic().getName(),
                "char_male");
        Assert.assertEquals(culture.getFemaleFamilyCharacteristic().getName(),
                "char_female");
    }

    @Test
    public final void testInitialLuck() {
        Assert.assertEquals(culture.getFemaleInitialLuckTable().getName(),
                "luck_female");
        Assert.assertEquals(culture.getMaleInitialLuckTable().getName(),
                "luck_male");
    }

    @Test
    public final void testName() {
        Assert.assertEquals(culture.getName(), "culture_1");
    }

    @Test
    public final void testPassionsBonus_Female() {
        final Map<NameAndDescriptor, Integer> attributes;
        NameAndDescriptor key;

        attributes = culture.getFemaleTemplate().getPassions();

        Assert.assertEquals(attributes.size(), 1);

        key = new DefaultNameAndDescriptor("passion_3", "");
        Assert.assertEquals(attributes.get(key), (Integer) 18);
    }

    @Test
    public final void testPassionsBonus_Male() {
        final Map<NameAndDescriptor, Integer> attributes;
        NameAndDescriptor key;

        attributes = culture.getMaleTemplate().getPassions();

        Assert.assertEquals(attributes.size(), 2);

        key = new DefaultNameAndDescriptor("passion_1", "");
        Assert.assertEquals(attributes.get(key), (Integer) 17);

        key = new DefaultNameAndDescriptor("passion_2", "descriptor_2");
        Assert.assertEquals(attributes.get(key), (Integer) 24);
    }

    @Test
    public final void testPassionsRandom_Female() {
        final Map<NameAndDescriptor, Dice> attributes;
        NameAndDescriptor key;

        attributes = culture.getFemaleTemplate().getPassionsRandom();

        Assert.assertEquals(attributes.size(), 1);

        key = new DefaultNameAndDescriptor("passion_3", "");
        Assert.assertEquals(attributes.get(key).getTextValue(), "6d6");
    }

    @Test
    public final void testPassionsRandom_Male() {
        final Map<NameAndDescriptor, Dice> attributes;
        NameAndDescriptor key;

        attributes = culture.getMaleTemplate().getPassionsRandom();

        Assert.assertEquals(attributes.size(), 3);

        key = new DefaultNameAndDescriptor("passion_1", "");
        Assert.assertEquals(attributes.get(key).getTextValue(), "3d6");

        key = new DefaultNameAndDescriptor("passion_2", "descriptor_2");
        Assert.assertEquals(attributes.get(key).getTextValue(), "0d1");

        key = new DefaultNameAndDescriptor("passion_4", "");
        Assert.assertEquals(attributes.get(key).getTextValue(), "0d1+5");
    }

    @Test
    public final void testSkillsBonus_Female() {
        final Map<NameAndDescriptor, Integer> attributes;
        NameAndDescriptor key;

        attributes = culture.getFemaleTemplate().getSkills();

        Assert.assertEquals(attributes.size(), 1);

        key = new DefaultNameAndDescriptor("skill_3", "");
        Assert.assertEquals(attributes.get(key), (Integer) 12);
    }

    @Test
    public final void testSkillsBonus_Male() {
        final Map<NameAndDescriptor, Integer> attributes;
        NameAndDescriptor key;

        attributes = culture.getMaleTemplate().getSkills();

        Assert.assertEquals(attributes.size(), 2);

        key = new DefaultNameAndDescriptor("skill_1", "");
        Assert.assertEquals(attributes.get(key), (Integer) 10);

        key = new DefaultNameAndDescriptor("skill_2", "descriptor_2");
        Assert.assertEquals(attributes.get(key), (Integer) 11);
    }

    @Test
    public final void testSpecialtySkills_Female() {
        final Map<String, Integer> attributes;

        attributes = culture.getFemaleTemplate().getSpecialtySkills();

        Assert.assertEquals(attributes.size(), 1);

        Assert.assertEquals(attributes.get("specialty_3"), (Integer) 17);
    }

    @Test
    public final void testSpecialtySkills_Male() {
        final Map<String, Integer> attributes;

        attributes = culture.getMaleTemplate().getSpecialtySkills();

        Assert.assertEquals(attributes.size(), 2);

        Assert.assertEquals(attributes.get("specialty_1"), (Integer) 15);
        Assert.assertEquals(attributes.get("specialty_2"), (Integer) 16);
    }

    @Test
    public final void testTraitsBonus_Female() {
        final Map<String, Integer> attributes;

        attributes = culture.getFemaleTemplate().getTraits();

        Assert.assertEquals(attributes.size(), 1);

        Assert.assertEquals(attributes.get("trait_3"), (Integer) 22);
    }

    @Test
    public final void testTraitsBonus_Male() {
        final Map<String, Integer> attributes;

        attributes = culture.getMaleTemplate().getTraits();

        Assert.assertEquals(attributes.size(), 2);

        Assert.assertEquals(attributes.get("trait_1"), (Integer) 6);
        Assert.assertEquals(attributes.get("trait_2"), (Integer) 12);
    }

}
