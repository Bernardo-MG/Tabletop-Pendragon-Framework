package com.wandrell.tabletop.pendragon.service.chargen.command;

import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.conf.FileConfig;
import com.wandrell.util.ResourceUtils;

public final class GetExtraSkillValueCommand implements ReturnCommand<Integer> {

    private Integer value;

    public GetExtraSkillValueCommand() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void execute() {
        final Yaml yaml;
        Map<String, Object> values;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(ResourceUtils
                .getClassPathReader(FileConfig.RULESET_CHARGEN_CONFIG));
        values = (Map<String, Object>) values.get("individualDiff");
        values = (Map<String, Object>) values.get("skill");
        values = (Map<String, Object>) values.get("extra");
        value = (Integer) values.get("value");
    }

    @Override
    public final Integer getResult() {
        return value;
    }

}
