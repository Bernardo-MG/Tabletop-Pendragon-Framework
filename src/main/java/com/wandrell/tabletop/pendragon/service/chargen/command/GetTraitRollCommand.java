package com.wandrell.tabletop.pendragon.service.chargen.command;

import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.dice.StringDiceParser;
import com.wandrell.tabletop.pendragon.conf.FileConfig;
import com.wandrell.util.ResourceUtils;

public final class GetTraitRollCommand implements ReturnCommand<Dice> {

    private Dice roll;

    public GetTraitRollCommand() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void execute() throws Exception {
        final StringDiceParser parser;
        final Yaml yaml;
        Map<String, Object> values;

        parser = new StringDiceParser();

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(ResourceUtils
                .getClassPathReader(FileConfig.RULESET_CHARGEN_CONFIG));
        values = (Map<String, Object>) values.get("trait");

        roll = parser.parse((String) values.get("homelandPassion"));
    }

    @Override
    public final Dice getResult() {
        return roll;
    }

}
