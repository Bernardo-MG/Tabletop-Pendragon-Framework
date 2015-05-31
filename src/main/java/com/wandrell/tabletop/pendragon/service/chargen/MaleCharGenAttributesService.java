package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.character.Gender;
import com.wandrell.tabletop.dice.notation.DiceFormula;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetAttributePointsCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetAttributeRollCommand;

public class MaleCharGenAttributesService implements CharGenAttributesService {

    private Map<String, DiceFormula> attributeRolls;
    private final CommandExecutor    comExec;
    private Integer                  points;

    public MaleCharGenAttributesService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final DiceFormula getAppearanceRoll() {
        return getAttributesRolls().get("appearance");
    }

    @Override
    public final Integer getAttributesPoints() {
        if (points == null) {
            points = getCommandExecutor().execute(
                    new GetAttributePointsCommand());
        }

        return points;
    }

    @Override
    public final DiceFormula getConstitutionRoll() {
        return getAttributesRolls().get("constitution");
    }

    @Override
    public final DiceFormula getDexterityRoll() {
        return getAttributesRolls().get("dexterity");
    }

    @Override
    public final DiceFormula getSizeRoll() {
        return getAttributesRolls().get("size");
    }

    @Override
    public final DiceFormula getStrengthRoll() {
        return getAttributesRolls().get("strength");
    }

    private final Map<String, DiceFormula> getAttributesRolls() {
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
