package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.dice.notation.DiceFormula;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetFamousTraitValueCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetFemaleValorousModifierCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetTraitIndividualDifferencesCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetTraitRandomPointsCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetTraitRollCommand;

public final class DefaultCharGenPersonalityTraitsService implements
        CharGenPersonalityTraitsService {

    private final CommandExecutor comExec;
    private Integer               diffPoints;
    private Integer               famousTrait;
    private DiceFormula           femaleValorousMod;
    private Integer               randomPoints;
    private DiceFormula           randomRoll;

    public DefaultCharGenPersonalityTraitsService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Integer getFamousTraitValue() {
        if (famousTrait == null) {
            famousTrait = getCommandExecutor().execute(
                    new GetFamousTraitValueCommand());
        }

        return famousTrait;
    }

    @Override
    public final DiceFormula getFemaleValorousReduction() {
        // TODO: This should adapt to the dice sign
        // This means, instead of giving a dice to substract, the dice should
        // return a negative value
        if (femaleValorousMod == null) {
            femaleValorousMod = getCommandExecutor().execute(
                    new GetFemaleValorousModifierCommand());
        }

        return femaleValorousMod;
    }

    @Override
    public final Integer getIndividualDifferencesPoints() {
        if (diffPoints == null) {
            diffPoints = getCommandExecutor().execute(
                    new GetTraitIndividualDifferencesCommand());
        }

        return diffPoints;
    }

    @Override
    public final Integer getRandomPoints() {
        if (randomPoints == null) {
            randomPoints = getCommandExecutor().execute(
                    new GetTraitRandomPointsCommand());
        }

        return randomPoints;
    }

    @Override
    public final DiceFormula getRandomRoll() {
        if (randomRoll == null) {
            randomRoll = getCommandExecutor()
                    .execute(new GetTraitRollCommand());
        }

        return randomRoll;
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
