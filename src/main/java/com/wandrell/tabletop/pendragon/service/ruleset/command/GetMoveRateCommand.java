package com.wandrell.tabletop.pendragon.service.ruleset.command;

import com.wandrell.pattern.command.ReturnCommand;

public final class GetMoveRateCommand implements ReturnCommand<Integer> {

    private final Integer dexterity;
    private final Integer strength;

    public GetMoveRateCommand(final Integer dexterity, final Integer strength) {
        super();

        this.strength = strength;
        this.dexterity = dexterity;
    }

    @Override
    public final Integer execute() {
        final Float str;
        final Float dex;

        str = new Float(strength);
        dex = new Float(dexterity);

        return Math.round((str + dex) / 10);
    }

}
