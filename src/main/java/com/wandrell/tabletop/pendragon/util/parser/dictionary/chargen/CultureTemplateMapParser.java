package com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.pendragon.model.chargen.CultureCharacterTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.CultureTemplate;
import com.wandrell.tabletop.skill.NameAndDescriptor;

public final class CultureTemplateMapParser implements
        Parser<CultureTemplate, Map<String, Object>> {

    public CultureTemplateMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final CultureTemplate value) {
        final Map<String, Object> data;
        final Map<String, Object> familyChar;
        final Map<String, Object> initialLuck;
        final Map<String, Object> template;

        data = new LinkedHashMap<String, Object>();
        familyChar = new LinkedHashMap<String, Object>();
        initialLuck = new LinkedHashMap<String, Object>();
        template = new LinkedHashMap<String, Object>();

        familyChar.put("male", value.getMaleFamilyCharacteristic().getName());
        familyChar.put("female", value.getFemaleFamilyCharacteristic()
                .getName());

        initialLuck.put("male", value.getMaleInitialLuckTable().getName());
        initialLuck.put("female", value.getFemaleInitialLuckTable().getName());

        template.put("male", buildTemplateMap(value.getMaleTemplate()));
        template.put("female", buildTemplateMap(value.getFemaleTemplate()));

        data.put("name", value.getName());
        data.put("family_characteristic", familyChar);
        data.put("initial_luck", initialLuck);
        data.put("template", template);

        return data;
    }

    private final Map<String, Object> buildTemplateMap(
            final CultureCharacterTemplate template) {
        final Map<String, Object> values;
        Collection<Map<String, Object>> valuesCol;
        Map<String, Object> value;

        values = new LinkedHashMap<String, Object>();

        valuesCol = new LinkedList<>();
        if (!template.getAttributes().isEmpty()) {
            for (final Entry<String, Integer> entry : template.getAttributes()
                    .entrySet()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", entry.getKey());
                value.put("value", entry.getValue());

                valuesCol.add(value);
            }
        }
        if (!valuesCol.isEmpty()) {
            values.put("attributes_bonus", valuesCol);
        }

        valuesCol = new LinkedList<>();
        if (!template.getAttributesRandom().isEmpty()) {
            for (final Entry<String, Dice> entry : template
                    .getAttributesRandom().entrySet()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", entry.getKey());
                value.put("value", entry.getValue().getTextValue());

                valuesCol.add(value);
            }
        }
        if (!valuesCol.isEmpty()) {
            values.put("attributes_random", valuesCol);
        }

        valuesCol = new LinkedList<>();
        if (!template.getTraits().isEmpty()) {
            for (final Entry<String, Integer> entry : template.getTraits()
                    .entrySet()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", entry.getKey());
                value.put("value", entry.getValue());

                valuesCol.add(value);
            }
        }
        if (!valuesCol.isEmpty()) {
            values.put("traits_bonus", valuesCol);
        }

        valuesCol = new LinkedList<>();
        if (!template.getSkills().isEmpty()) {
            for (final Entry<NameAndDescriptor, Integer> entry : template
                    .getSkills().entrySet()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", entry.getKey().getName());
                value.put("descriptor", entry.getKey().getDescriptor());
                value.put("value", entry.getValue());

                valuesCol.add(value);
            }
        }
        if (!valuesCol.isEmpty()) {
            values.put("skills_bonus", valuesCol);
        }

        valuesCol = new LinkedList<>();
        if (!template.getSpecialtySkills().isEmpty()) {
            for (final Entry<String, Integer> entry : template
                    .getSpecialtySkills().entrySet()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", entry.getKey());
                value.put("value", entry.getValue());

                valuesCol.add(value);
            }
        }
        if (!valuesCol.isEmpty()) {
            values.put("specialty_skills", valuesCol);
        }

        valuesCol = new LinkedList<>();
        if (!template.getPassions().isEmpty()) {
            for (final Entry<NameAndDescriptor, Integer> entry : template
                    .getPassions().entrySet()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", entry.getKey().getName());
                value.put("descriptor", entry.getKey().getDescriptor());
                value.put("value", entry.getValue());

                valuesCol.add(value);
            }
        }
        if (!valuesCol.isEmpty()) {
            values.put("passions_bonus", valuesCol);
        }

        valuesCol = new LinkedList<>();
        if (!template.getPassionsRandom().isEmpty()) {
            for (final Entry<NameAndDescriptor, Dice> entry : template
                    .getPassionsRandom().entrySet()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", entry.getKey().getName());
                value.put("descriptor", entry.getKey().getDescriptor());
                value.put("value", entry.getValue().getTextValue());

                valuesCol.add(value);
            }
        }
        if (!valuesCol.isEmpty()) {
            values.put("passions_random", valuesCol);
        }

        valuesCol = new LinkedList<>();
        if (!template.getDirectedTraits().isEmpty()) {
            for (final Entry<NameAndDescriptor, Integer> entry : template
                    .getDirectedTraits().entrySet()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", entry.getKey().getName());
                value.put("descriptor", entry.getKey().getDescriptor());
                value.put("value", entry.getValue());

                valuesCol.add(value);
            }
        }
        if (!valuesCol.isEmpty()) {
            values.put("directed_traits_bonus", valuesCol);
        }

        return values;
    }

}
