package com.wandrell.tabletop.testing.pendragon.test.integration.parser.chargen;

import java.io.Reader;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.pendragon.model.chargen.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.model.chargen.CultureTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.CultureYAMLParser;
import com.wandrell.tabletop.skill.DefaultSkillName;
import com.wandrell.tabletop.skill.SkillName;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseCultureTemplateYAMLParser {

    private CultureTemplate culture;

    public ITParseCultureTemplateYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, CultureTemplate> parser;
        final TestServiceFactory factory;
        final ModelConstructorService modelService;
        final Repository<AdditionalBelongingsTable> belongingsRepository;
        final Repository<FamilyCharacteristicTemplate> characteristicRepository;

        factory = TestServiceFactory.getInstance();

        modelService = factory.getModelConstructorService();

        belongingsRepository = factory.getAdditionalBelongingsTableRepository();
        characteristicRepository = factory
                .getFamilyCharacteristicTemplateRepository();

        parser = new CultureYAMLParser(modelService, characteristicRepository,
                belongingsRepository);

        culture = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.CULTURE));
    }

    @Test
    public final void testAttributesBonus_Female() {
        final Map<String, Integer> attributes;

        attributes = culture.getFemaleTemplate().getAttributes();

        Assert.assertEquals(attributes.size(), 1);

        Assert.assertEquals(attributes.get("dexterity"), (Integer) 3);
    }

    @Test
    public final void testAttributesBonus_Male() {
        final Map<String, Integer> attributes;

        attributes = culture.getMaleTemplate().getAttributes();

        Assert.assertEquals(attributes.size(), 2);

        Assert.assertEquals(attributes.get("appearance"), (Integer) 1);
        Assert.assertEquals(attributes.get("constitution"), (Integer) 2);
    }

    @Test
    public final void testAttributesRandom_Female() {
        final Map<String, Dice> attributes;

        attributes = culture.getFemaleTemplate().getAttributesRandom();

        Assert.assertEquals(attributes.size(), 1);

        Assert.assertEquals(attributes.get("dexterity").getTextValue(), "10d2");
    }

    @Test
    public final void testAttributesRandom_Male() {
        final Map<String, Dice> attributes;

        attributes = culture.getMaleTemplate().getAttributesRandom();

        Assert.assertEquals(attributes.size(), 2);

        Assert.assertEquals(attributes.get("appearance").getTextValue(), "1d6");
        Assert.assertEquals(attributes.get("constitution").getTextValue(),
                "2d6");
    }

    @Test
    public final void testDirectedTraitsBonus_Female() {
        final Map<SkillName, Integer> attributes;
        SkillName key;

        attributes = culture.getFemaleTemplate().getDirectedTraits();

        Assert.assertEquals(attributes.size(), 1);

        key = new DefaultSkillName("directed_3", "");
        Assert.assertEquals(attributes.get(key), (Integer) 31);
    }

    @Test
    public final void testDirectedTraitsBonus_Male() {
        final Map<SkillName, Integer> attributes;
        SkillName key;

        attributes = culture.getMaleTemplate().getDirectedTraits();

        Assert.assertEquals(attributes.size(), 2);

        key = new DefaultSkillName("directed_1", "");
        Assert.assertEquals(attributes.get(key), (Integer) 30);

        key = new DefaultSkillName("directed_2", "descriptor_2");
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
        final Map<SkillName, Integer> attributes;
        SkillName key;

        attributes = culture.getFemaleTemplate().getPassions();

        Assert.assertEquals(attributes.size(), 1);

        key = new DefaultSkillName("passion_3", "");
        Assert.assertEquals(attributes.get(key), (Integer) 18);
    }

    @Test
    public final void testPassionsBonus_Male() {
        final Map<SkillName, Integer> attributes;
        SkillName key;

        attributes = culture.getMaleTemplate().getPassions();

        Assert.assertEquals(attributes.size(), 2);

        key = new DefaultSkillName("passion_1", "");
        Assert.assertEquals(attributes.get(key), (Integer) 17);

        key = new DefaultSkillName("passion_2", "descriptor_2");
        Assert.assertEquals(attributes.get(key), (Integer) 24);
    }

    @Test
    public final void testPassionsRandom_Female() {
        final Map<SkillName, Dice> attributes;
        SkillName key;

        attributes = culture.getFemaleTemplate().getPassionsRandom();

        Assert.assertEquals(attributes.size(), 1);

        key = new DefaultSkillName("passion_3", "");
        Assert.assertEquals(attributes.get(key).getTextValue(), "6d6");
    }

    @Test
    public final void testPassionsRandom_Male() {
        final Map<SkillName, Dice> attributes;
        SkillName key;

        attributes = culture.getMaleTemplate().getPassionsRandom();

        Assert.assertEquals(attributes.size(), 3);

        key = new DefaultSkillName("passion_1", "");
        Assert.assertEquals(attributes.get(key).getTextValue(), "3d6");

        key = new DefaultSkillName("passion_2", "descriptor_2");
        Assert.assertEquals(attributes.get(key).getTextValue(), "0d1");

        key = new DefaultSkillName("passion_4", "");
        Assert.assertEquals(attributes.get(key).getTextValue(), "0d1+5");
    }

    @Test
    public final void testSkillsBonus_Female() {
        final Map<SkillName, Integer> attributes;
        SkillName key;

        attributes = culture.getFemaleTemplate().getSkills();

        Assert.assertEquals(attributes.size(), 1);

        key = new DefaultSkillName("skill_3", "");
        Assert.assertEquals(attributes.get(key), (Integer) 12);
    }

    @Test
    public final void testSkillsBonus_Male() {
        final Map<SkillName, Integer> attributes;
        SkillName key;

        attributes = culture.getMaleTemplate().getSkills();

        Assert.assertEquals(attributes.size(), 2);

        key = new DefaultSkillName("skill_1", "");
        Assert.assertEquals(attributes.get(key), (Integer) 10);

        key = new DefaultSkillName("skill_2", "descriptor_2");
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

        Assert.assertEquals(attributes.get("cowardly"), (Integer) 22);
    }

    @Test
    public final void testTraitsBonus_Male() {
        final Map<String, Integer> attributes;

        attributes = culture.getMaleTemplate().getTraits();

        Assert.assertEquals(attributes.size(), 2);

        Assert.assertEquals(attributes.get("arbitrary"), (Integer) 6);
        Assert.assertEquals(attributes.get("chaste"), (Integer) 12);
    }

}
