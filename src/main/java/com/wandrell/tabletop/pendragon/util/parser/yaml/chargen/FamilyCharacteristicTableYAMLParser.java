package com.wandrell.tabletop.pendragon.util.parser.yaml.chargen;

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
import com.wandrell.tabletop.pendragon.model.character.stats.AttributesHolder;
import com.wandrell.tabletop.pendragon.model.character.stats.DefaultAttributesHolder;
import com.wandrell.tabletop.pendragon.model.chargen.background.FamilyCharacteristicTable;
import com.wandrell.tabletop.pendragon.model.chargen.background.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.stats.valuebox.DefaultSkillBox;
import com.wandrell.tabletop.stats.valuebox.SkillBox;

public class FamilyCharacteristicTableYAMLParser implements
        Parser<Reader, FamilyCharacteristicTable> {

    private final ModelConstructorService modelService;

    public FamilyCharacteristicTableYAMLParser(
            final ModelConstructorService service) {
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
        if ((values.containsKey("intervals")) && (intervals != null)
                && (!intervals.isEmpty())) {
            for (final Map<String, Object> intervalNode : intervals) {
                limits.add((Integer) intervalNode.get("lower_limit"));
            }
        }

        pos = 0;
        intervalsMap = new LinkedHashMap<Interval, FamilyCharacteristicTemplate>();
        if ((values.containsKey("intervals")) && (intervals != null)
                && (!intervals.isEmpty())) {
            for (final Map<String, Object> intervalNode : intervals) {
                if (pos < (limits.size() - 1)) {
                    interval = new DefaultInterval(limits.get(pos),
                            limits.get(pos + 1) - 1);
                } else {
                    interval = new DefaultInterval(limits.get(pos), 20);
                }
                pos++;

                intervalsMap
                        .put(interval,
                                readFamilyCharacteristic((Map<String, Object>) intervalNode
                                        .get("family_characteristic")));
            }
        }

        return getModelService().getFamilyCharacteristicTable(name,
                intervalsMap);
    }

    private final ModelConstructorService getModelService() {
        return modelService;
    }

    private final void loadAttributesHolder(
            final Collection<Map<String, Object>> attributes,
            final AttributesHolder holder) {
        for (final Map<String, Object> attribute : attributes) {
            if (attribute.get("name").toString().equals("appearance")) {
                holder.setAppearance((Integer) attribute.get("value"));
            } else if (attribute.get("name").toString().equals("constitution")) {
                holder.setConstitution((Integer) attribute.get("value"));
            } else if (attribute.get("name").toString().equals("dexterity")) {
                holder.setDexterity((Integer) attribute.get("value"));
            } else if (attribute.get("name").toString().equals("size")) {
                holder.setSize((Integer) attribute.get("value"));
            } else if (attribute.get("name").toString().equals("strength")) {
                holder.setStrength((Integer) attribute.get("value"));
            }
        }
    }

    @SuppressWarnings("unchecked")
    private final FamilyCharacteristicTemplate readFamilyCharacteristic(
            final Map<String, Object> template) {
        final String name;
        final AttributesHolder attributes;
        final Collection<SkillBox> skills;
        final Map<String, Collection<Map<String, Object>>> bonus;
        SkillBox skillData;
        String descriptor;
        Integer value;

        name = (String) template.get("name");

        attributes = new DefaultAttributesHolder();
        skills = new LinkedList<SkillBox>();

        if (template.containsKey("bonus")) {
            bonus = (Map<String, Collection<Map<String, Object>>>) template
                    .get("bonus");

            if (bonus.containsKey("attributes")) {
                loadAttributesHolder(bonus.get("attributes"), attributes);
            }

            if (bonus.containsKey("skills")) {
                for (final Map<String, Object> skill : bonus.get("skills")) {
                    value = (Integer) skill.get("value");

                    descriptor = (String) skill.get("descriptor");

                    if (descriptor == null) {
                        descriptor = "";
                    }

                    skillData = new DefaultSkillBox((String) skill.get("name"),
                            descriptor, value);

                    skills.add(skillData);
                }
            }
        }

        return getModelService().getFamilyCharacteristicTemplate(name,
                attributes, skills);
    }

}
