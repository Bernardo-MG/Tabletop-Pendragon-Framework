package com.wandrell.tabletop.testing.pendragon.test.integration.parser.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.pendragon.model.chargen.background.CultureTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.background.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.inventory.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.CultureYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.tabletop.valuebox.SkillBox;
import com.wandrell.util.ResourceUtils;

public final class ITParseMinimumCultureTemplateYAMLParser {

    private CultureTemplate culture;

    public ITParseMinimumCultureTemplateYAMLParser() {
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
                .getClassPathReader(TestModelFileConf.CULTURE_MINIMUM));
    }

    @Test
    public final void testAttributesBonus_Female() {
        final Collection<SkillBox> attributes;

        attributes = culture.getFemaleTemplate().getAttributes();

        Assert.assertTrue(attributes.isEmpty());
    }

    @Test
    public final void testAttributesBonus_Male() {
        final Collection<SkillBox> attributes;

        attributes = culture.getMaleTemplate().getAttributes();

        Assert.assertTrue(attributes.isEmpty());
    }

    @Test
    public final void testAttributesRandom_Female() {
        final Map<String, Dice> attributes;

        attributes = culture.getFemaleTemplate().getAttributesRandom();

        Assert.assertTrue(attributes.isEmpty());
    }

    @Test
    public final void testAttributesRandom_Male() {
        final Map<String, Dice> attributes;

        attributes = culture.getMaleTemplate().getAttributesRandom();

        Assert.assertTrue(attributes.isEmpty());
    }

    @Test
    public final void testDirectedTraitsBonus_Female() {
        final Collection<SkillBox> dirTraits;

        dirTraits = culture.getFemaleTemplate().getDirectedTraits();

        Assert.assertTrue(dirTraits.isEmpty());
    }

    @Test
    public final void testDirectedTraitsBonus_Male() {
        final Collection<SkillBox> dirTraits;

        dirTraits = culture.getMaleTemplate().getDirectedTraits();

        Assert.assertTrue(dirTraits.isEmpty());
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
        final Collection<SkillBox> passions;

        passions = culture.getFemaleTemplate().getPassions();

        Assert.assertTrue(passions.isEmpty());
    }

    @Test
    public final void testPassionsBonus_Male() {
        final Collection<SkillBox> passions;

        passions = culture.getMaleTemplate().getPassions();

        Assert.assertTrue(passions.isEmpty());
    }

    @Test
    public final void testPassionsRandom_Female() {
        final Map<SkillBox, Dice> passions;

        passions = culture.getFemaleTemplate().getPassionsRandom();

        Assert.assertTrue(passions.isEmpty());
    }

    @Test
    public final void testPassionsRandom_Male() {
        final Map<SkillBox, Dice> passions;

        passions = culture.getMaleTemplate().getPassionsRandom();

        Assert.assertTrue(passions.isEmpty());
    }

    @Test
    public final void testSkillsBonus_Female() {
        final Collection<SkillBox> skills;

        skills = culture.getFemaleTemplate().getSkills();

        Assert.assertTrue(skills.isEmpty());
    }

    @Test
    public final void testSkillsBonus_Male() {
        final Collection<SkillBox> skills;

        skills = culture.getMaleTemplate().getSkills();

        Assert.assertTrue(skills.isEmpty());
    }

    @Test
    public final void testSpecialtySkills_Female() {
        final Collection<SkillBox> skills;

        skills = culture.getFemaleTemplate().getSpecialtySkills();

        Assert.assertTrue(skills.isEmpty());
    }

    @Test
    public final void testSpecialtySkills_Male() {
        final Collection<SkillBox> skills;

        skills = culture.getMaleTemplate().getSpecialtySkills();

        Assert.assertTrue(skills.isEmpty());
    }

}
