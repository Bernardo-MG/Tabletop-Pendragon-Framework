package com.wandrell.tabletop.pendragon.service.ruleset.command;

import com.wandrell.pattern.command.ResultCommand;

public final class GetHitPointsCommand implements ResultCommand<Integer> {

    private final Integer constitution;
    private Integer       hitpoints;
    private final Integer size;

    public GetHitPointsCommand(final Integer constitution, final Integer size) {
        super();

        this.constitution = constitution;
        this.size = size;
    }

    @Override
    public final void execute() {
        hitpoints = constitution + size;
    }

    @Override
    public final Integer getResult() {
        return hitpoints;
    }

}
