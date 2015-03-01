package com.wandrell.tabletop.pendragon.service.ruleset.command;

import com.wandrell.pattern.command.ReturnCommand;

public final class GetWeightCommand implements ReturnCommand<Integer> {

    private final Integer size;

    public GetWeightCommand(final Integer size) {
        super();

        this.size = size;
    }

    @Override
    public final Integer execute() {
        return getWeightRecursive(size);
    }

    private final Integer getWeightRecursive(final Integer size) {
        final Integer result;
        final Integer previous;

        if (size <= 10) {
            result = size * 10;
        } else {
            previous = getWeightRecursive(size - 1);
            result = previous + ((Long) Math.round(previous * 0.1)).intValue();
        }

        return result;
    }

}
