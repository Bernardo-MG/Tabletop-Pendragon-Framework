package com.wandrell.tabletop.pendragon.util.outputter.character;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.outputter.Outputter;
import com.wandrell.tabletop.pendragon.model.character.Horse;

public class HorseYAMLOutputter implements Outputter<Horse> {

    private final Yaml yaml;

    {
        final DumperOptions options;

        options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        yaml = new Yaml(options);
    }

    public HorseYAMLOutputter() {
        super();
    }

    @Override
    public final void send(final Horse value, final OutputStream stream)
            throws Exception {
        send(value, new BufferedWriter(new OutputStreamWriter(stream)));
    }

    @Override
    public final void send(final Horse value, final Writer writer)
            throws Exception {
        getYaml().dump(buildMap(value), writer);
    }

    private final Map<String, Object> buildMap(final Horse value) {
        final Map<String, Object> data;
        final Map<String, Integer> attributes;
        final Map<String, Integer> derived;
        final Map<String, Boolean> flags;

        data = new LinkedHashMap<String, Object>();
        attributes = new LinkedHashMap<String, Integer>();
        derived = new LinkedHashMap<String, Integer>();
        flags = new LinkedHashMap<String, Boolean>();

        attributes.put("constitution", value.getConstitution());
        attributes.put("dexterity", value.getDexterity());
        attributes.put("size", value.getSize());
        attributes.put("strength", value.getStrength());

        derived.put("damage", 55);
        derived.put("movement_rate", 66);

        flags.put("armored", value.isArmored());
        flags.put("combat", value.isCombatHorse());
        flags.put("hunting", value.isHuntingHorse());

        data.put("type", value.getHorseType());
        data.put("natural_armor", value.getNaturalArmor());
        data.put("attributes", attributes);
        data.put("derived_attributes", derived);
        data.put("flags", flags);

        return data;
    }

    private final Yaml getYaml() {
        return yaml;
    }

}
