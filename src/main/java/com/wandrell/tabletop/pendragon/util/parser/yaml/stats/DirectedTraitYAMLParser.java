package com.wandrell.tabletop.pendragon.util.parser.yaml.stats;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.valuebox.DefaultSkillBox;
import com.wandrell.tabletop.valuebox.SkillBox;

public final class DirectedTraitYAMLParser implements
        Parser<Reader, Collection<SkillBox>> {

    public DirectedTraitYAMLParser() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Collection<SkillBox> parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final Collection<SkillBox> traits;
        SkillBox trait;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        traits = new LinkedList<SkillBox>();
        for (final String name : (Collection<String>) values
                .get("directed_traits")) {
            trait = new DefaultSkillBox(name, 0, 0, Integer.MAX_VALUE);

            traits.add(trait);
        }

        return traits;
    }

}
