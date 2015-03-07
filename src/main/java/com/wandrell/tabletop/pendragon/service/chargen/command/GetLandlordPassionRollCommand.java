package com.wandrell.tabletop.pendragon.service.chargen.command;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.dice.StringDiceParser;
import com.wandrell.util.ResourceUtils;

public final class GetLandlordPassionRollCommand implements ReturnCommand<Dice> {

    private final String descriptor;
    private final String name;

    public GetLandlordPassionRollCommand(final String name,
            final String descriptor) {
        super();

        this.name = name;
        this.descriptor = descriptor;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Dice execute() throws Exception {
        final Yaml yaml;
        final StringDiceParser parser;
        final Iterator<Map<String, Object>> itr;
        Dice result;
        Collection<Map<String, Object>> values;
        Map<String, Object> value;

        yaml = new Yaml();

        values = (Collection<Map<String, Object>>) yaml
                .load(ResourceUtils
                        .getClassPathReader("config/stat/pendragon_passion_landlord.yml"));

        parser = new StringDiceParser();

        result = null;
        itr = values.iterator();
        while ((itr.hasNext()) && (result == null)) {
            value = itr.next();

            if ((value.get("name").equals(name))
                    && (value.get("descriptor").equals(descriptor))) {
                result = parser.parse(value.get("roll").toString());
            }
        }

        return result;
    }

}
