package com.wandrell.tabletop.testing.pendragon.test.integration.parser.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.pendragon.model.chargen.background.FamilyCharacteristicTable;
import com.wandrell.tabletop.pendragon.model.chargen.background.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.FamilyCharacteristicTableYAMLParser;
import com.wandrell.tabletop.stats.valuebox.SkillBox;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseFamilyCharacteristicTableYAMLParser {

    private FamilyCharacteristicTable table;

    public ITParseFamilyCharacteristicTableYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, FamilyCharacteristicTable> parser;
        final ModelConstructorService modelService;

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new FamilyCharacteristicTableYAMLParser(modelService);

        table = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.FAMILY_CHARACTERISTIC));
    }

    @Test
    public final void testFamilyCharacteristic_First() {
        final FamilyCharacteristicTemplate familyChar;
        final Collection<SkillBox> skills;
        final Iterator<SkillBox> itr;
        SkillBox entry;

        familyChar = table.getIntervals().values().iterator().next();

        Assert.assertEquals(familyChar.getName(), "characteristic_1");

        Assert.assertEquals(familyChar.getAttributes().getAppearance(),
                (Integer) 0);
        Assert.assertEquals(familyChar.getAttributes().getConstitution(),
                (Integer) 0);
        Assert.assertEquals(familyChar.getAttributes().getDexterity(),
                (Integer) 0);
        Assert.assertEquals(familyChar.getAttributes().getSize(), (Integer) 0);
        Assert.assertEquals(familyChar.getAttributes().getStrength(),
                (Integer) 0);

        skills = familyChar.getSkills();
        Assert.assertEquals(skills.size(), 2);

        itr = skills.iterator();

        entry = itr.next();
        Assert.assertEquals(entry.getName(), "skill_1");
        Assert.assertEquals(entry.getDescriptor(), "");
        Assert.assertEquals(entry.getValue(), (Integer) 3);

        entry = itr.next();
        Assert.assertEquals(entry.getName(), "skill_2");
        Assert.assertEquals(entry.getDescriptor(), "descriptor_skill_2");
        Assert.assertEquals(entry.getValue(), (Integer) 4);
    }

    @Test
    public final void testFamilyCharacteristic_Second() {
        final FamilyCharacteristicTemplate familyChar;
        final Collection<SkillBox> skills;
        final Iterator<FamilyCharacteristicTemplate> itrValues;
        final Iterator<SkillBox> itrSkills;
        SkillBox entrySkill;

        itrValues = table.getIntervals().values().iterator();
        itrValues.next();
        familyChar = itrValues.next();

        Assert.assertEquals(familyChar.getName(), "characteristic_2");

        Assert.assertEquals(familyChar.getAttributes().getAppearance(),
                (Integer) 7);
        Assert.assertEquals(familyChar.getAttributes().getConstitution(),
                (Integer) 0);
        Assert.assertEquals(familyChar.getAttributes().getDexterity(),
                (Integer) 0);
        Assert.assertEquals(familyChar.getAttributes().getSize(), (Integer) 0);
        Assert.assertEquals(familyChar.getAttributes().getStrength(),
                (Integer) 0);

        skills = familyChar.getSkills();
        Assert.assertEquals(skills.size(), 1);

        itrSkills = skills.iterator();

        entrySkill = itrSkills.next();
        Assert.assertEquals(entrySkill.getName(), "skill_1");
        Assert.assertEquals(entrySkill.getDescriptor(), "");
        Assert.assertEquals(entrySkill.getValue(), (Integer) 3);
    }

    @Test
    public final void testIntervals() {
        final Iterator<Interval> itr;
        Interval interval;

        Assert.assertEquals(table.getIntervals().size(), 2);

        itr = table.getIntervals().keySet().iterator();

        interval = itr.next();
        Assert.assertEquals(interval.getLowerLimit(), (Integer) 1);
        Assert.assertEquals(interval.getUpperLimit(), (Integer) 4);

        interval = itr.next();
        Assert.assertEquals(interval.getLowerLimit(), (Integer) 5);
        Assert.assertEquals(interval.getUpperLimit(), (Integer) 20);
    }

    @Test
    public final void testName() {
        Assert.assertEquals(table.getName(), "test_family_characteristic");
    }

}
