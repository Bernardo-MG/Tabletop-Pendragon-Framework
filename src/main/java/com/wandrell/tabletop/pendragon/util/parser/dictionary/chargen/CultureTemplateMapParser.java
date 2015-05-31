package com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.character.stats.RandomSkill;
import com.wandrell.tabletop.pendragon.model.character.stats.TraitsHolder;
import com.wandrell.tabletop.pendragon.model.chargen.background.CultureCharacterTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.background.CultureTemplate;
import com.wandrell.tabletop.stats.valuebox.SkillBox;

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

        // Family characteristics
        familyChar.put("male", value.getMaleFamilyCharacteristic().getName());
        familyChar.put("female", value.getFemaleFamilyCharacteristic()
                .getName());

        // Initial luck
        initialLuck.put("male", value.getMaleInitialLuckTable().getName());
        initialLuck.put("female", value.getFemaleInitialLuckTable().getName());

        // Templates
        template.put("male", buildTemplateMap(value.getMaleTemplate()));
        template.put("female", buildTemplateMap(value.getFemaleTemplate()));

        // Culture data
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

        // Attributes
        valuesCol = new LinkedList<>();
        if (template.getAttributes().getAppearance() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "appearance");
            value.put("value", template.getAttributes().getAppearance());

            valuesCol.add(value);
        }
        if (template.getAttributes().getConstitution() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "constitution");
            value.put("value", template.getAttributes().getConstitution());

            valuesCol.add(value);
        }
        if (template.getAttributes().getDexterity() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "dexterity");
            value.put("value", template.getAttributes().getDexterity());

            valuesCol.add(value);
        }
        if (template.getAttributes().getSize() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "size");
            value.put("value", template.getAttributes().getSize());

            valuesCol.add(value);
        }
        if (template.getAttributes().getStrength() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "strength");
            value.put("value", template.getAttributes().getStrength());

            valuesCol.add(value);
        }
        values.put("attributes_bonus", valuesCol);

        // Random attributes
        valuesCol = new LinkedList<>();
        if (!template.getAttributesRandom().getAppearance().getPrintableText()
                .equals("0")) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "appearance");
            value.put("value", template.getAttributesRandom().getAppearance()
                    .getPrintableText());

            valuesCol.add(value);
        }
        if (!template.getAttributesRandom().getConstitution()
                .getPrintableText().equals("0")) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "constitution");
            value.put("value", template.getAttributesRandom().getConstitution()
                    .getPrintableText());

            valuesCol.add(value);
        }
        if (!template.getAttributesRandom().getDexterity().getPrintableText()
                .equals("0")) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "dexterity");
            value.put("value", template.getAttributesRandom().getDexterity()
                    .getPrintableText());

            valuesCol.add(value);
        }
        if (!template.getAttributesRandom().getSize().getPrintableText()
                .equals("0")) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "size");
            value.put("value", template.getAttributesRandom().getSize()
                    .getPrintableText());

            valuesCol.add(value);
        }
        if (!template.getAttributesRandom().getStrength().getPrintableText()
                .equals("0")) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "strength");
            value.put("value", template.getAttributesRandom().getStrength()
                    .getPrintableText());

            valuesCol.add(value);
        }
        values.put("attributes_random", valuesCol);

        // Traits
        valuesCol = getTraitsValues(template.getTraits());
        values.put("traits_bonus", valuesCol);

        // Skills
        valuesCol = new LinkedList<>();
        if (!template.getSkills().isEmpty()) {
            for (final SkillBox box : template.getSkills()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", box.getName());
                value.put("descriptor", box.getDescriptor());
                value.put("value", box.getValue());

                valuesCol.add(value);
            }
            values.put("skills_bonus", valuesCol);
        }

        // Specialty Skills
        valuesCol = new LinkedList<>();
        if (!template.getSpecialtySkills().isEmpty()) {
            for (final SkillBox box : template.getSpecialtySkills()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", box.getName());
                value.put("value", box.getValue());

                valuesCol.add(value);
            }
            values.put("specialty_skills", valuesCol);
        }

        // Passions
        valuesCol = new LinkedList<>();
        if (!template.getPassions().isEmpty()) {
            for (final SkillBox box : template.getPassions()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", box.getName());
                value.put("descriptor", box.getDescriptor());
                value.put("value", box.getValue());

                valuesCol.add(value);
            }
            values.put("passions_bonus", valuesCol);
        }

        // Random passions
        valuesCol = new LinkedList<>();
        if (!template.getPassionsRandom().isEmpty()) {
            for (final RandomSkill passion : template.getPassionsRandom()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", passion.getName());
                value.put("descriptor", passion.getDescriptor());
                value.put("value", passion.getValue().getPrintableText());

                valuesCol.add(value);
            }
            values.put("passions_random", valuesCol);
        }

        // Directed Traits
        valuesCol = new LinkedList<>();
        if (!template.getDirectedTraits().isEmpty()) {
            for (final SkillBox box : template.getDirectedTraits()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", box.getName());
                value.put("descriptor", box.getDescriptor());
                value.put("value", box.getValue());

                valuesCol.add(value);
            }
            values.put("directed_traits_bonus", valuesCol);
        }

        return values;
    }

    private final Collection<Map<String, Object>> getTraitsValues(
            final TraitsHolder traits) {
        final Collection<Map<String, Object>> valuesCol;
        Map<String, Object> value;

        valuesCol = new LinkedList<>();
        if (traits.getArbitrary() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "arbitrary");
            value.put("value", traits.getArbitrary());

            valuesCol.add(value);
        }
        if (traits.getChaste() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "chaste");
            value.put("value", traits.getChaste());

            valuesCol.add(value);
        }
        if (traits.getCowardly() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "cowardly");
            value.put("value", traits.getCowardly());

            valuesCol.add(value);
        }
        if (traits.getCruel() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "cruel");
            value.put("value", traits.getCruel());

            valuesCol.add(value);
        }
        if (traits.getDeceitful() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "deceitful");
            value.put("value", traits.getDeceitful());

            valuesCol.add(value);
        }
        if (traits.getEnergetic() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "energetic");
            value.put("value", traits.getEnergetic());

            valuesCol.add(value);
        }
        if (traits.getForgiving() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "forgiving");
            value.put("value", traits.getForgiving());

            valuesCol.add(value);
        }
        if (traits.getGenerous() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "generous");
            value.put("value", traits.getGenerous());

            valuesCol.add(value);
        }
        if (traits.getHonest() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "honest");
            value.put("value", traits.getHonest());

            valuesCol.add(value);
        }
        if (traits.getIndulgent() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "indulgent");
            value.put("value", traits.getIndulgent());

            valuesCol.add(value);
        }
        if (traits.getJust() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "just");
            value.put("value", traits.getJust());

            valuesCol.add(value);
        }
        if (traits.getLazy() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "lazy");
            value.put("value", traits.getLazy());

            valuesCol.add(value);
        }
        if (traits.getLustful() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "lustful");
            value.put("value", traits.getLustful());

            valuesCol.add(value);
        }
        if (traits.getMerciful() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "merciful");
            value.put("value", traits.getMerciful());

            valuesCol.add(value);
        }
        if (traits.getModest() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "modest");
            value.put("value", traits.getModest());

            valuesCol.add(value);
        }

        return valuesCol;
    }

}
