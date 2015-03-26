package com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.character.stats.TraitsHolder;
import com.wandrell.tabletop.pendragon.model.chargen.region.RegionTemplate;

public final class RegionTemplateMapParser implements
        Parser<RegionTemplate, Map<String, Object>> {

    public RegionTemplateMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final RegionTemplate region) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();

        data.put("name", region.getName());
        data.put("bonus", getBonus(region));

        return data;
    }

    private final Map<String, Object> getBonus(final RegionTemplate region) {
        final Map<String, Object> bonus;
        Collection<Map<String, Object>> values;
        bonus = new LinkedHashMap<String, Object>();

        // Traits
        values = getTraitsValues(region.getTraits());
        bonus.put("traits", values);

        return bonus;
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
