package com.wandrell.tabletop.pendragon.service.ruleset.command;

import com.wandrell.pattern.command.ReturnCommand;

public final class GetDamageCommand implements ReturnCommand<Integer> {

    private Integer       damage;
    private final Integer size;
    private final Integer strength;

    public GetDamageCommand(final Integer size, final Integer strength) {
        super();

        this.size = size;
        this.strength = strength;
    }

    @Override
    public final void execute() {
        final Float str;
        final Float siz;

        str = new Float(strength);
        siz = new Float(size);

        damage = Math.round((str + siz) / 6);
    }

    @Override
    public final Integer getResult() {
        return damage;
    }

}
