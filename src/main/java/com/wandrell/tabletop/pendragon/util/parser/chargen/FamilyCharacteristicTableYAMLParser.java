package com.wandrell.tabletop.pendragon.util.parser.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.interval.DefaultInterval;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.pendragon.model.chargen.FamilyCharacteristicTable;
import com.wandrell.tabletop.pendragon.model.chargen.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.skill.DefaultNameAndDescriptor;
import com.wandrell.tabletop.skill.NameAndDescriptor;

public class FamilyCharacteristicTableYAMLParser implements
        Parser<Reader, FamilyCharacteristicTable> {

    private final ModelService modelService;

    public FamilyCharacteristicTableYAMLParser(final ModelService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final FamilyCharacteristicTable parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final Map<Interval, FamilyCharacteristicTemplate> intervalsMap;
        final Collection<Map<String, Object>> intervals;
        final String name;
        final List<Integer> limits;
        Interval interval;
        Integer pos;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        // Name
        name = (String) values.get("name");

        intervals = (Collection<Map<String, Object>>) values.get("intervals");
        limits = new LinkedList<Integer>();
        for (final Map<String, Object> intervalNode : intervals) {
            limits.add((Integer) intervalNode.get("lower_limit"));
        }

        pos = 0;
        intervalsMap = new LinkedHashMap<Interval, FamilyCharacteristicTemplate>();
        for (final Map<String, Object> intervalNode : intervals) {
            if (pos < (limits.size() - 1)) {
                interval = new DefaultInterval(limits.get(pos),
                        limits.get(pos + 1) - 1);
            } else {
                interval = new DefaultInterval(limits.get(pos), 20);
            }
            pos++;

            intervalsMap.put(interval,
                    readFamilyCharacteristic((Map<String, Object>) intervalNode
                            .get("family_characteristic")));
        }

        return getModelService().getFamilyCharacteristicTable(name,
                intervalsMap);
    }

    private final ModelService getModelService() {
        return modelService;
    }

    @SuppressWarnings("unchecked")
    private final FamilyCharacteristicTemplate readFamilyCharacteristic(
            final Map<String, Object> node) {
        final String name;
        final Map<String, Integer> attributes;
        final Map<NameAndDescriptor, Integer> skills;
        final Collection<Map<String, Object>> attributesMap;
        final Collection<Map<String, Object>> skillsMap;
        final Map<String, Collection<Map<String, Object>>> bonus;
        NameAndDescriptor skillData;
        String descriptor;
        Integer value;

        name = (String) node.get("name");

        bonus = (Map<String, Collection<Map<String, Object>>>) node
                .get("bonus");

        attributesMap = bonus.get("attributes");
        attributes = new LinkedHashMap<String, Integer>();
        if (attributesMap != null) {
            for (final Map<String, Object> attribute : attributesMap) {
                value = (Integer) attribute.get("value");

                attributes.put((String) attribute.get("name"), value);
            }
        }

        skillsMap = bonus.get("skills");
        skills = new LinkedHashMap<NameAndDescriptor, Integer>();
        if (skillsMap != null) {
            for (final Map<String, Object> skill : skillsMap) {
                value = (Integer) skill.get("value");

                descriptor = (String) skill.get("descriptor");

                if (descriptor == null) {
                    descriptor = "";
                }

                skillData = new DefaultNameAndDescriptor(
                        (String) skill.get("name"), descriptor);

                skills.put(skillData, value);
            }
        }

        return getModelService().getFamilyCharacteristicTemplate(name,
                attributes, skills);
    }

}
