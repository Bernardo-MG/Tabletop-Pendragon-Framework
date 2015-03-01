package com.wandrell.tabletop.pendragon.service.ruleset.command;

import com.wandrell.pattern.command.ReturnCommand;

public final class GetHealingRateCommand implements ReturnCommand<Integer> {

    private final Integer constitution;
    private final Integer strength;

    public GetHealingRateCommand(final Integer constitution,
            final Integer strength) {
        super();

        this.constitution = constitution;
        this.strength = strength;
    }

    @Override
    public final Integer execute() {
        final Float str;
        final Float con;

        str = new Float(strength);
        con = new Float(constitution);

        return Math.round((str + con) / 10);
    }

}
