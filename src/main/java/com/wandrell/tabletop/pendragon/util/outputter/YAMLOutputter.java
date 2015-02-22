package com.wandrell.tabletop.pendragon.util.outputter;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.outputter.Outputter;

public abstract class YAMLOutputter<V> implements Outputter<V> {

    private final Yaml yaml;

    {
        final DumperOptions options;

        options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        yaml = new Yaml(options);
    }

    public YAMLOutputter() {
        super();
    }

    @Override
    public final void send(final V value, final OutputStream stream) {
        send(value, new BufferedWriter(new OutputStreamWriter(stream)));
    }

    @Override
    public final void send(final V value, final Writer writer) {
        getYaml().dump(buildMap(value), writer);
    }

    private final Yaml getYaml() {
        return yaml;
    }

    protected abstract Map<String, Object> buildMap(final V value);

}
