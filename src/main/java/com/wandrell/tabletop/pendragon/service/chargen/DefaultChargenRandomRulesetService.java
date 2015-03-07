package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.character.Gender;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetAttributeRollCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetCommonPassionRollCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetHomelandPassionRollCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetLandlordPassionRollCommand;

public final class DefaultChargenRandomRulesetService implements
        ChargenRandomRulesetService {

    private Map<String, Dice>     attributeRollFemale;
    private Map<String, Dice>     attributeRollMale;
    private final CommandExecutor comExec;
    private Dice                  common;
    private Dice                  homeland;

    public DefaultChargenRandomRulesetService(final CommandExecutor executor) {
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
    public final Dice getCommonPassionRoll() {
        if (common == null) {
            common = getCommandExecutor().execute(
                    new GetCommonPassionRollCommand());
        }

        return common;
    }

    @Override
    public final Dice getHomelandPassionRoll() {
        if (homeland == null) {
            homeland = getCommandExecutor().execute(
                    new GetHomelandPassionRollCommand());
        }

        return homeland;
    }

    @Override
    public final Dice getLandlordPassionRoll(final String passion,
            final String descriptor) {
        return getCommandExecutor().execute(
                new GetLandlordPassionRollCommand(passion, descriptor));
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
