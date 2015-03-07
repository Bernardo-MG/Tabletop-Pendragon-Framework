package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.character.Gender;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetAttributePointsCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetAttributeRollCommand;

public class DefaultCharGenAttributesRulesetService implements
        CharGenAttributesRulesetService {

    private Map<String, Dice>     attributeRollFemale;
    private Map<String, Dice>     attributeRollMale;
    private Integer               attributes;
    private final CommandExecutor comExec;

    public DefaultCharGenAttributesRulesetService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Dice getAttributeRoll(final String attribute,
            final Gender gender) {
        final Dice roll;

        if ((gender == Gender.FEMALE) && (attributeRollFemale == null)) {
            attributeRollFemale = getCommandExecutor().execute(
                    new GetAttributeRollCommand(gender));
        } else if (attributeRollMale == null) {
            attributeRollMale = getCommandExecutor().execute(
                    new GetAttributeRollCommand(gender));
        }

        if (gender == Gender.MALE) {
            roll = attributeRollMale.get(attribute);
        } else {
            roll = attributeRollFemale.get(attribute);
        }

        return roll;
    }

    @Override
    public final Integer getAttributesPoints() {
        if (attributes == null) {
            attributes = getCommandExecutor().execute(
                    new GetAttributePointsCommand());
        }

        return attributes;
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
