package com.wandrell.tabletop.pendragon.service.chargen.command;

import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.command.ResultCommand;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.dice.notation.DiceFormula;
import com.wandrell.tabletop.dice.parser.DiceFormulaParser;
import com.wandrell.tabletop.pendragon.conf.FileConfig;
import com.wandrell.util.ResourceUtils;

public final class GetHomelandPassionRollCommand implements
        ResultCommand<DiceFormula> {

    private DiceFormula formula;

    public GetHomelandPassionRollCommand() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void execute() throws Exception {
        final Parser<String, DiceFormula> parser;
        final Yaml yaml;
        Map<String, Object> values;

        parser = new DiceFormulaParser();

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(ResourceUtils
                .getClassPathReader(FileConfig.RULESET_CHARGEN_CONFIG));
        values = (Map<String, Object>) values.get("rolls");

        formula = parser.parse((String) values.get("homelandPassion"));
    }

    @Override
    public final DiceFormula getResult() {
        return formula;
    }

}
