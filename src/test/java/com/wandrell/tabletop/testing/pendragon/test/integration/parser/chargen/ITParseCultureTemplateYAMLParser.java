package com.wandrell.tabletop.testing.pendragon.test.integration.parser.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.tabletop.pendragon.model.character.stats.AttributesHolder;
import com.wandrell.tabletop.pendragon.model.character.stats.AttributesRandom;
import com.wandrell.tabletop.pendragon.model.character.stats.RandomSkill;
import com.wandrell.tabletop.pendragon.model.character.stats.TraitsHolder;
import com.wandrell.tabletop.pendragon.model.chargen.background.CultureTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.background.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.inventory.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.CultureYAMLParser;
import com.wandrell.tabletop.stats.valuebox.SkillBox;
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
        final FilteredRepository<AdditionalBelongingsTable, Predicate<AdditionalBelongingsTable>> belongingsRepository;
        final FilteredRepository<FamilyCharacteristicTemplate, Predicate<FamilyCharacteristicTemplate>> characteristicRepository;

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
        final AttributesHolder attributes;

        attributes = culture.getFemaleTemplate().getAttributes();

        Assert.assertEquals(attributes.getAppearance(), (Integer) 0);
        Assert.assertEquals(attributes.getConstitution(), (Integer) 0);
        Assert.assertEquals(attributes.getDexterity(), (Integer) 3);
        Assert.assertEquals(attributes.getSize(), (Integer) 0);
        Assert.assertEquals(attributes.getStrength(), (Integer) 0);
    }

    @Test
    public final void testAttributesBonus_Male() {
        final AttributesHolder attributes;

        attributes = culture.getMaleTemplate().getAttributes();

        Assert.assertEquals(attributes.getAppearance(), (Integer) 1);
        Assert.assertEquals(attributes.getConstitution(), (Integer) 2);
        Assert.assertEquals(attributes.getDexterity(), (Integer) 0);
        Assert.assertEquals(attributes.getSize(), (Integer) 0);
        Assert.assertEquals(attributes.getStrength(), (Integer) 0);
    }

    @Test
    public final void testAttributesRandom_Female() {
        final AttributesRandom attributes;

        attributes = culture.getFemaleTemplate().getAttributesRandom();

        Assert.assertEquals(attributes.getDexterity().getTextValue(), "10d2");
    }

    @Test
    public final void testAttributesRandom_Male() {
        final AttributesRandom attributes;

        attributes = culture.getMaleTemplate().getAttributesRandom();

        Assert.assertEquals(attributes.getAppearance().getTextValue(), "1d6");
        Assert.assertEquals(attributes.getConstitution().getTextValue(), "2d6");
    }

    @Test
    public final void testDirectedTraitsBonus_Female() {
        final Collection<SkillBox> attributes;
        final Iterator<SkillBox> itr;
        SkillBox box;

        attributes = culture.getFemaleTemplate().getDirectedTraits();

        Assert.assertEquals(attributes.size(), 1);

        itr = attributes.iterator();
        box = itr.next();
        Assert.assertEquals(box.getName(), "directed_3");
        Assert.assertEquals(box.getDescriptor(), "");
        Assert.assertEquals(box.getValue(), (Integer) 31);
    }

    @Test
    public final void testDirectedTraitsBonus_Male() {
        final Collection<SkillBox> attributes;
        final Iterator<SkillBox> itr;
        SkillBox box;

        attributes = culture.getMaleTemplate().getDirectedTraits();

        Assert.assertEquals(attributes.size(), 2);

        itr = attributes.iterator();
        box = itr.next();
        Assert.assertEquals(box.getName(), "directed_1");
        Assert.assertEquals(box.getDescriptor(), "");
        Assert.assertEquals(box.getValue(), (Integer) 30);

        box = itr.next();
        Assert.assertEquals(box.getName(), "directed_2");
        Assert.assertEquals(box.getDescriptor(), "descriptor_2");
        Assert.assertEquals(box.getValue(), (Integer) 1);
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
        final Collection<SkillBox> attributes;
        final Iterator<SkillBox> itr;
        SkillBox box;

        attributes = culture.getFemaleTemplate().getPassions();

        Assert.assertEquals(attributes.size(), 1);

        itr = attributes.iterator();
        box = itr.next();
        Assert.assertEquals(box.getName(), "passion_3");
        Assert.assertEquals(box.getDescriptor(), "");
        Assert.assertEquals(box.getValue(), (Integer) 18);
    }

    @Test
    public final void testPassionsBonus_Male() {
        final Collection<SkillBox> attributes;
        final Iterator<SkillBox> itr;
        SkillBox box;

        attributes = culture.getMaleTemplate().getPassions();

        Assert.assertEquals(attributes.size(), 2);

        itr = attributes.iterator();
        box = itr.next();
        Assert.assertEquals(box.getName(), "passion_1");
        Assert.assertEquals(box.getDescriptor(), "");
        Assert.assertEquals(box.getValue(), (Integer) 17);

        box = itr.next();
        Assert.assertEquals(box.getName(), "passion_2");
        Assert.assertEquals(box.getDescriptor(), "descriptor_2");
        Assert.assertEquals(box.getValue(), (Integer) 24);
    }

    @Test
    public final void testPassionsRandom_Female() {
        final Collection<RandomSkill> passions;
        final Iterator<RandomSkill> itrPassions;
        RandomSkill passion;

        passions = culture.getFemaleTemplate().getPassionsRandom();

        Assert.assertEquals(passions.size(), 1);

        itrPassions = passions.iterator();

        passion = itrPassions.next();
        Assert.assertEquals(passion.getName(), "passion_3");
        Assert.assertEquals(passion.getDescriptor(), "");
        Assert.assertEquals(passion.getValue().getTextValue(), "6d6");
    }

    @Test
    public final void testPassionsRandom_Male() {
        final Collection<RandomSkill> passions;
        final Iterator<RandomSkill> itrPassions;
        RandomSkill passion;

        passions = culture.getMaleTemplate().getPassionsRandom();

        Assert.assertEquals(passions.size(), 3);

        itrPassions = passions.iterator();

        passion = itrPassions.next();
        Assert.assertEquals(passion.getName(), "passion_1");
        Assert.assertEquals(passion.getDescriptor(), "");
        Assert.assertEquals(passion.getValue().getTextValue(), "3d6");

        passion = itrPassions.next();
        Assert.assertEquals(passion.getName(), "passion_2");
        Assert.assertEquals(passion.getDescriptor(), "descriptor_2");
        Assert.assertEquals(passion.getValue().getTextValue(), "0d1");

        passion = itrPassions.next();
        Assert.assertEquals(passion.getName(), "passion_4");
        Assert.assertEquals(passion.getDescriptor(), "");
        Assert.assertEquals(passion.getValue().getTextValue(), "0d1+5");
    }

    @Test
    public final void testSkillsBonus_Female() {
        final Collection<SkillBox> attributes;
        final Iterator<SkillBox> itr;
        SkillBox box;

        attributes = culture.getFemaleTemplate().getSkills();

        Assert.assertEquals(attributes.size(), 1);

        itr = attributes.iterator();
        box = itr.next();
        Assert.assertEquals(box.getName(), "skill_3");
        Assert.assertEquals(box.getDescriptor(), "");
        Assert.assertEquals(box.getValue(), (Integer) 12);
    }

    @Test
    public final void testSkillsBonus_Male() {
        final Collection<SkillBox> attributes;
        final Iterator<SkillBox> itr;
        SkillBox box;

        attributes = culture.getMaleTemplate().getSkills();

        Assert.assertEquals(attributes.size(), 2);

        itr = attributes.iterator();
        box = itr.next();
        Assert.assertEquals(box.getName(), "skill_1");
        Assert.assertEquals(box.getDescriptor(), "");
        Assert.assertEquals(box.getValue(), (Integer) 10);

        box = itr.next();
        Assert.assertEquals(box.getName(), "skill_2");
        Assert.assertEquals(box.getDescriptor(), "descriptor_2");
        Assert.assertEquals(box.getValue(), (Integer) 11);
    }

    @Test
    public final void testSpecialtySkills_Female() {
        final Collection<SkillBox> attributes;
        final Iterator<SkillBox> itr;
        SkillBox box;

        attributes = culture.getFemaleTemplate().getSpecialtySkills();

        Assert.assertEquals(attributes.size(), 1);

        itr = attributes.iterator();
        box = itr.next();
        Assert.assertEquals(box.getName(), "specialty_3");
        Assert.assertEquals(box.getValue(), (Integer) 17);
    }

    @Test
    public final void testSpecialtySkills_Male() {
        final Collection<SkillBox> attributes;
        final Iterator<SkillBox> itr;
        SkillBox box;

        attributes = culture.getMaleTemplate().getSpecialtySkills();

        Assert.assertEquals(attributes.size(), 2);

        itr = attributes.iterator();
        box = itr.next();
        Assert.assertEquals(box.getName(), "specialty_1");
        Assert.assertEquals(box.getValue(), (Integer) 15);
        box = itr.next();
        Assert.assertEquals(box.getName(), "specialty_2");
        Assert.assertEquals(box.getValue(), (Integer) 16);
    }

    @Test
    public final void testTraitsBonus_Female() {
        final TraitsHolder traits;

        traits = culture.getFemaleTemplate().getTraits();

        Assert.assertEquals(traits.getCowardly(), (Integer) 22);
    }

    @Test
    public final void testTraitsBonus_Male() {
        final TraitsHolder traits;

        traits = culture.getMaleTemplate().getTraits();

        Assert.assertEquals(traits.getArbitrary(), (Integer) 6);

        Assert.assertEquals(traits.getChaste(), (Integer) 12);
    }

}
