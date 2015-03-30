package com.wandrell.tabletop.pendragon.service.chargen.command;

import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.character.Gender;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.dice.StringDiceParser;
import com.wandrell.tabletop.pendragon.conf.FileConfig;
import com.wandrell.util.ResourceUtils;

public final class GetAttributeRollCommand implements
        ReturnCommand<Map<String, Dice>> {

    private final Gender      gender;
    private Map<String, Dice> rolls;

    public GetAttributeRollCommand(final Gender gender) {
        super();

        this.gender = gender;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void execute() throws Exception {
        final StringDiceParser parser;
        final Yaml yaml;
        Map<String, Object> values;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(ResourceUtils
                .getClassPathReader(FileConfig.RULESET_CHARGEN_CONFIG));
        values = (Map<String, Object>) values.get("attributeRolls");

        if (gender == Gender.MALE) {
            values = (Map<String, Object>) values.get("male");
        } else {
            values = (Map<String, Object>) values.get("female");
        }

        parser = new StringDiceParser();
        rolls = new LinkedHashMap<>();

        rolls.put("appearance",
                parser.parse(values.get("appearance").toString()));
        rolls.put("constitution",
                parser.parse(values.get("constitution").toString()));
        rolls.put("dexterity", parser.parse(values.get("dexterity").toString()));
        rolls.put("size", parser.parse(values.get("size").toString()));
        rolls.put("strength", parser.parse(values.get("strength").toString()));
    }

    @Override
    public final Map<String, Dice> getResult() {
        return rolls;
    }

}
