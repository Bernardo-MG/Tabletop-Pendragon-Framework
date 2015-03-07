package com.wandrell.tabletop.pendragon.service.ruleset.command;

import com.wandrell.pattern.command.ReturnCommand;

public final class GetUnconciousCommand implements ReturnCommand<Integer> {

    private final Integer constitution;
    private final Integer size;

    public GetUnconciousCommand(final Integer constitution, final Integer size) {
        super();

        this.constitution = constitution;
        this.size = size;
    }

    @Override
    public final Integer execute() {
        final Float con;
        final Float siz;

        con = new Float(constitution);
        siz = new Float(size);

        // TODO: Better use the real hitpoints value
        return Math.round((con + siz) / 4);
    }

}
