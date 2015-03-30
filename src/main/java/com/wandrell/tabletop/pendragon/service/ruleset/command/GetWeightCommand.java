package com.wandrell.tabletop.pendragon.service.ruleset.command;

import com.wandrell.pattern.command.ReturnCommand;

public final class GetWeightCommand implements ReturnCommand<Integer> {

    private final Integer size;
    private Integer       weight;

    public GetWeightCommand(final Integer size) {
        super();

        this.size = size;
    }

    @Override
    public final void execute() {
        weight = getWeightRecursive(size);
    }

    @Override
    public final Integer getResult() {
        return weight;
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
