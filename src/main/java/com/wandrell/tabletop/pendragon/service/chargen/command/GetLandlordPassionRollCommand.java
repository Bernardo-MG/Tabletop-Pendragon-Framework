package com.wandrell.tabletop.pendragon.service.chargen.command;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.command.ResultCommand;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.dice.StringDiceParser;
import com.wandrell.util.ResourceUtils;

public final class GetLandlordPassionRollCommand implements ResultCommand<Dice> {

    private final String descriptor;
    private final String name;
    private Dice         roll;

    public GetLandlordPassionRollCommand(final String name,
            final String descriptor) {
        super();

        this.name = name;
        this.descriptor = descriptor;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void execute() throws Exception {
        final Yaml yaml;
        final StringDiceParser parser;
        final Iterator<Map<String, Object>> itr;
        Collection<Map<String, Object>> values;
        Map<String, Object> value;

        yaml = new Yaml();

        values = (Collection<Map<String, Object>>) yaml
                .load(ResourceUtils
                        .getClassPathReader("config/stat/pendragon_passion_landlord.yml"));

        parser = new StringDiceParser();

        roll = null;
        itr = values.iterator();
        while ((itr.hasNext()) && (roll == null)) {
            value = itr.next();

            if ((value.get("name").equals(name))
                    && (value.get("descriptor").equals(descriptor))) {
                roll = parser.parse(value.get("roll").toString());
            }
        }

        if (roll == null) {
            roll = parser.parse("0");
        }
    }

    @Override
    public final Dice getResult() {
        return roll;
    }

}
