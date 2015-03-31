package com.wandrell.tabletop.pendragon.service.ruleset.command;

import java.util.Collection;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.command.ResultCommand;
import com.wandrell.tabletop.pendragon.conf.FileConfig;
import com.wandrell.util.ResourceUtils;

public final class GetChivaldryTraitsCommand implements
        ResultCommand<Collection<String>> {

    private Collection<String> traits;

    public GetChivaldryTraitsCommand() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void execute() {
        final Yaml yaml;
        Map<String, Object> values;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(ResourceUtils
                .getClassPathReader(FileConfig.RULESET_CONFIG));

        values = (Map<String, Object>) values.get("traitBonus");
        values = (Map<String, Object>) values.get("chivaldry");

        traits = (Collection<String>) values.get("traits");
    }

    @Override
    public final Collection<String> getResult() {
        return traits;
    }

}
