package com.wandrell.tabletop.pendragon.util.parser.yaml.stats;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.valuebox.SkillBox;

public final class StatsYAMLParser<V extends SkillBox> implements
        Parser<Reader, Collection<V>> {

    private final StatBuilder<V> builder;

    public interface StatBuilder<V extends SkillBox> {

        public V getStat(final String name, final String descriptor,
                final Integer value);

    }

    public StatsYAMLParser(final StatBuilder<V> builder) {
        super();

        this.builder = builder;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Collection<V> parse(final Reader reader) {
        final Yaml yaml;
        final Collection<Map<String, Object>> values;
        final Collection<V> stats;
        String descriptor;
        Integer value;
        V stat;

        yaml = new Yaml();

        values = (Collection<Map<String, Object>>) yaml.load(reader);

        stats = new LinkedList<V>();
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

                stat = getStatBuilder().getStat((String) data.get("name"),
                        descriptor, value);

                if ((descriptor.isEmpty()) && (data.containsKey("described"))) {
                    stat.setDescribed((Boolean) data.get("described"));
                }

                stats.add(stat);
            }
        }

        return stats;
    }

    private final StatBuilder<V> getStatBuilder() {
        return builder;
    }

}
