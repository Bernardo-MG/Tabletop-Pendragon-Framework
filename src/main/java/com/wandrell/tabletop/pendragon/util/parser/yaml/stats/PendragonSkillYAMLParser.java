package com.wandrell.tabletop.pendragon.util.parser.yaml.stats;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.character.stats.PendragonSkillBox;
import com.wandrell.tabletop.pendragon.service.model.StatConstructorService;

public final class PendragonSkillYAMLParser implements
        Parser<Reader, Collection<PendragonSkillBox>> {

    private final StatConstructorService statService;

    public PendragonSkillYAMLParser(final StatConstructorService service) {
        super();

        statService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Collection<PendragonSkillBox> parse(final Reader reader) {
        final Yaml yaml;
        final Collection<Map<String, Object>> values;
        final Collection<PendragonSkillBox> stats;
        Boolean combat;
        Boolean knightly;
        Boolean knowledge;
        Boolean courtly;
        String descriptor;
        Integer value;
        PendragonSkillBox stat;

        yaml = new Yaml();

        values = (Collection<Map<String, Object>>) yaml.load(reader);

        stats = new LinkedList<PendragonSkillBox>();
        if (values != null) {
            for (final Map<String, Object> data : values) {
                if (data.containsKey("descriptor")) {
                    descriptor = (String) data.get("descriptor");

                    if (descriptor == null) {
                        descriptor = "";
                    }
                } else {
                    descriptor = "";
                }

                if (data.containsKey("value")) {
                    value = (Integer) data.get("value");

                    if (value == null) {
                        value = 0;
                    }
                } else {
                    value = 0;
                }

                if (data.containsKey("combat")) {
                    combat = (Boolean) data.get("combat");
                } else {
                    combat = false;
                }

                if (data.containsKey("knightly")) {
                    knightly = (Boolean) data.get("knightly");
                } else {
                    knightly = false;
                }

                if (data.containsKey("knowledge")) {
                    knowledge = (Boolean) data.get("knowledge");
                } else {
                    knowledge = false;
                }

                if (data.containsKey("courtly")) {
                    courtly = (Boolean) data.get("courtly");
                } else {
                    courtly = false;
                }

                stat = getStatConstructorService().getSkill(
                        (String) data.get("name"), descriptor, value, combat,
                        knightly, knowledge, courtly);

                // if ((descriptor.isEmpty()) &&
                // (data.containsKey("described"))) {
                // stat.setDescribed((Boolean) data.get("described"));
                // }

                stats.add(stat);
            }
        }

        return stats;
    }

    private final StatConstructorService getStatConstructorService() {
        return statService;
    }

}
