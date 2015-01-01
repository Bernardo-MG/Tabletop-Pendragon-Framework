package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.religion;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.interval.Interval;
import com.wandrell.tabletop.business.model.pendragon.chargen.FamilyCharacteristicTable;
import com.wandrell.tabletop.business.model.pendragon.chargen.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.business.model.skill.NameAndDescriptor;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.chargen.FamilyCharacteristicTableDocumentInputProcessor;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.FileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;
import com.wandrell.util.parser.xml.input.JDOMStAXInputParser;

public final class ITReadFamilyCharacteristicTableDocumentInputProcessor {

    private FamilyCharacteristicTable table;

    public ITReadFamilyCharacteristicTableDocumentInputProcessor() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<FamilyCharacteristicTable> parser;
        final JDOMDocumentInputProcessor<FamilyCharacteristicTable> processor;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        processor = new FamilyCharacteristicTableDocumentInputProcessor(
                modelService);
        parser = new JDOMStAXInputParser<FamilyCharacteristicTable>(processor);

        table = parser.read(ResourceUtils
                .getClassPathInputStream(FileConf.FAMILY_CHARACTERISTIC));
    }

    @Test
    public final void testFamilyCharacteristic_First() {
        final FamilyCharacteristicTemplate familyChar;
        final Map<NameAndDescriptor, Integer> skills;
        final Iterator<Entry<NameAndDescriptor, Integer>> itr;
        Entry<NameAndDescriptor, Integer> entry;

        familyChar = table.getIntervals().values().iterator().next();

        Assert.assertEquals(familyChar.getFamilyCharacteristic(),
                "characteristic_1");

        Assert.assertEquals(familyChar.getAttributes().size(), 0);

        skills = familyChar.getSkills();
        Assert.assertEquals(skills.size(), 2);

        itr = skills.entrySet().iterator();

        entry = itr.next();
        Assert.assertEquals(entry.getKey().getName(), "skill_1");
        Assert.assertEquals(entry.getKey().getDescriptor(), "");
        Assert.assertEquals(entry.getValue(), (Integer) 3);

        entry = itr.next();
        Assert.assertEquals(entry.getKey().getName(), "skill_2");
        Assert.assertEquals(entry.getKey().getDescriptor(),
                "descriptor_skill_2");
        Assert.assertEquals(entry.getValue(), (Integer) 4);
    }

    @Test
    public final void testFamilyCharacteristic_Second() {
        final FamilyCharacteristicTemplate familyChar;
        final Map<NameAndDescriptor, Integer> skills;
        final Iterator<FamilyCharacteristicTemplate> itrValues;
        final Iterator<Entry<String, Integer>> itrAttributes;
        final Iterator<Entry<NameAndDescriptor, Integer>> itrSkills;
        Entry<String, Integer> entryAttribute;
        Entry<NameAndDescriptor, Integer> entrySkill;

        itrValues = table.getIntervals().values().iterator();
        itrValues.next();
        familyChar = itrValues.next();

        Assert.assertEquals(familyChar.getFamilyCharacteristic(),
                "characteristic_2");

        Assert.assertEquals(familyChar.getAttributes().size(), 1);

        itrAttributes = familyChar.getAttributes().entrySet().iterator();

        entryAttribute = itrAttributes.next();
        Assert.assertEquals(entryAttribute.getKey(), "attribute_1");
        Assert.assertEquals(entryAttribute.getValue(), (Integer) 7);

        skills = familyChar.getSkills();
        Assert.assertEquals(skills.size(), 1);

        itrSkills = skills.entrySet().iterator();

        entrySkill = itrSkills.next();
        Assert.assertEquals(entrySkill.getKey().getName(), "skill_1");
        Assert.assertEquals(entrySkill.getKey().getDescriptor(), "");
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
