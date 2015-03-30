package com.wandrell.tabletop.pendragon.service.ruleset.command;

import com.wandrell.pattern.command.ReturnCommand;

public final class GetHealingRateCommand implements ReturnCommand<Integer> {

    private final Integer constitution;
    private Integer       healing;
    private final Integer strength;

    public GetHealingRateCommand(final Integer constitution,
            final Integer strength) {
        super();

        this.constitution = constitution;
        this.strength = strength;
    }

    @Override
    public final void execute() {
        final Float str;
        final Float con;

        str = new Float(strength);
        con = new Float(constitution);

        healing = Math.round((str + con) / 10);
    }

    @Override
    public final Integer getResult() {
        return healing;
    }

}
