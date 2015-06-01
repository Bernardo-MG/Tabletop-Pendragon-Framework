package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.character.Gender;
import com.wandrell.tabletop.dice.notation.DiceExpression;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetAttributePointsCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetAttributeRollCommand;

public class FemaleCharGenAttributesService implements CharGenAttributesService {

    private Map<String, DiceExpression> attributeRolls;
    private final CommandExecutor       comExec;
    private Integer                     points;

    public FemaleCharGenAttributesService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final DiceExpression getAppearanceRoll() {
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
    public final DiceExpression getConstitutionRoll() {
        return getAttributesRolls().get("constitution");
    }

    @Override
    public final DiceExpression getDexterityRoll() {
        return getAttributesRolls().get("dexterity");
    }

    @Override
    public final DiceExpression getSizeRoll() {
        return getAttributesRolls().get("size");
    }

    @Override
    public final DiceExpression getStrengthRoll() {
        return getAttributesRolls().get("strength");
    }

    private final Map<String, DiceExpression> getAttributesRolls() {
        if (attributeRolls == null) {
            attributeRolls = getCommandExecutor().execute(
                    new GetAttributeRollCommand(Gender.FEMALE));
        }

        return attributeRolls;
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
