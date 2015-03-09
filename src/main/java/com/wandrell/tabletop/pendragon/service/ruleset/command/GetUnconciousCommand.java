package com.wandrell.tabletop.pendragon.service.ruleset.command;

import com.wandrell.pattern.command.ReturnCommand;

public final class GetUnconciousCommand implements ReturnCommand<Integer> {

    private final Integer hitpoints;

    public GetUnconciousCommand(final Integer hitpoints) {
        super();

        this.hitpoints = hitpoints;
    }

    @Override
    public final Integer execute() {
        final Float hp;

        hp = new Float(hitpoints);

        // TODO: Better use the real hitpoints value
        return Math.round(hp / 4);
    }

}
