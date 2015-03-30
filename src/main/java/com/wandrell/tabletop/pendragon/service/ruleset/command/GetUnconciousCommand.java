package com.wandrell.tabletop.pendragon.service.ruleset.command;

import com.wandrell.pattern.command.ReturnCommand;

public final class GetUnconciousCommand implements ReturnCommand<Integer> {

    private final Integer hitpoints;
    private Integer       unconcious;

    public GetUnconciousCommand(final Integer hitpoints) {
        super();

        this.hitpoints = hitpoints;
    }

    @Override
    public final void execute() {
        final Float hp;

        hp = new Float(hitpoints);

        unconcious = Math.round(hp / 4);
    }

    @Override
    public final Integer getResult() {
        return unconcious;
    }

}
