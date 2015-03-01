package com.wandrell.tabletop.pendragon.service.ruleset.command;

import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.conf.FileConfig;
import com.wandrell.util.ResourceUtils;

public final class GetReligiousTraitTresholdCommand implements
        ReturnCommand<Integer> {

    public GetReligiousTraitTresholdCommand() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Integer execute() {
        final Yaml yaml;
        Map<String, Object> values;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(ResourceUtils
                .getClassPathReader(FileConfig.RULESET_CONFIG));

        values = (Map<String, Object>) values.get("traitBonus");
        values = (Map<String, Object>) values.get("religious");

        return (Integer) values.get("treshold");
    }

}
