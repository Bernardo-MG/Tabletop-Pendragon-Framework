package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.character.Gender;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetAttributePointsCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetAttributeRollCommand;

public class MaleCharGenAttributesService implements CharGenAttributesService {

    private Map<String, Dice>     attributeRolls;
    private Integer               attributes;
    private final CommandExecutor comExec;

    public MaleCharGenAttributesService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Dice getAppearanceRoll() {
        return getAttributesRolls().get("appearance");
    }

    @Override
    public final Integer getAttributesPoints() {
        if (attributes == null) {
            attributes = getCommandExecutor().execute(
                    new GetAttributePointsCommand());
        }

        return attributes;
    }

    @Override
    public final Dice getConstitutionRoll() {
        return getAttributesRolls().get("constitution");
    }

    @Override
    public final Dice getDexterityRoll() {
        return getAttributesRolls().get("dexterity");
    }

    @Override
    public final Dice getSizeRoll() {
        return getAttributesRolls().get("size");
    }

    @Override
    public final Dice getStrengthRoll() {
        return getAttributesRolls().get("strength");
    }

    private final Map<String, Dice> getAttributesRolls() {
        if (attributeRolls == null) {
            attributeRolls = getCommandExecutor().execute(
                    new GetAttributeRollCommand(Gender.MALE));
        }

        return attributeRolls;
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
