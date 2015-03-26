package com.wandrell.tabletop.testing.pendragon.test.integration.parser.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;
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
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.tabletop.valuebox.DefaultSkillBox;
import com.wandrell.tabletop.valuebox.SkillBox;
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
        final Collection<SkillBox> attributes;
        final Iterator<SkillBox> itr;
        SkillBox box;

        attributes = culture.getFemaleTemplate().getAttributes();

        Assert.assertEquals(attributes.size(), 1);

        itr = attributes.iterator();
        box = itr.next();
        Assert.assertEquals(box.getName(), "dexterity");
        Assert.assertEquals(box.getValue(), (Integer) 3);
    }

    @Test
    public final void testAttributesBonus_Male() {
        final Collection<SkillBox> attributes;
        final Iterator<SkillBox> itr;
        SkillBox box;

        attributes = culture.getMaleTemplate().getAttributes();

        Assert.assertEquals(attributes.size(), 2);

        itr = attributes.iterator();
        box = itr.next();
        Assert.assertEquals(box.getName(), "appearance");
        Assert.assertEquals(box.getValue(), (Integer) 1);
        box = itr.next();
        Assert.assertEquals(box.getName(), "constitution");
        Assert.assertEquals(box.getValue(), (Integer) 2);
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
        final Map<SkillBox, Dice> attributes;
        SkillBox key;

        attributes = culture.getFemaleTemplate().getPassionsRandom();

        Assert.assertEquals(attributes.size(), 1);

        key = new DefaultSkillBox("passion_3", "", 0);
        Assert.assertEquals(attributes.get(key).getTextValue(), "6d6");
    }

    @Test
    public final void testPassionsRandom_Male() {
        final Map<SkillBox, Dice> attributes;
        SkillBox key;

        attributes = culture.getMaleTemplate().getPassionsRandom();

        Assert.assertEquals(attributes.size(), 3);

        key = new DefaultSkillBox("passion_1", "", 0);
        Assert.assertEquals(attributes.get(key).getTextValue(), "3d6");

        key = new DefaultSkillBox("passion_2", "descriptor_2", 0);
        Assert.assertEquals(attributes.get(key).getTextValue(), "0d1");

        key = new DefaultSkillBox("passion_4", "", 0);
        Assert.assertEquals(attributes.get(key).getTextValue(), "0d1+5");
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
        final Collection<SkillBox> attributes;
        final Iterator<SkillBox> itr;
        SkillBox box;

        attributes = culture.getFemaleTemplate().getTraits();

        Assert.assertEquals(attributes.size(), 1);

        itr = attributes.iterator();
        box = itr.next();
        Assert.assertEquals(box.getName(), "cowardly");
        Assert.assertEquals(box.getValue(), (Integer) 22);
    }

    @Test
    public final void testTraitsBonus_Male() {
        final Collection<SkillBox> attributes;
        final Iterator<SkillBox> itr;
        SkillBox box;

        attributes = culture.getMaleTemplate().getTraits();

        Assert.assertEquals(attributes.size(), 2);

        itr = attributes.iterator();
        box = itr.next();
        Assert.assertEquals(box.getName(), "arbitrary");
        Assert.assertEquals(box.getValue(), (Integer) 6);

        box = itr.next();
        Assert.assertEquals(box.getName(), "chaste");
        Assert.assertEquals(box.getValue(), (Integer) 12);
    }

}
