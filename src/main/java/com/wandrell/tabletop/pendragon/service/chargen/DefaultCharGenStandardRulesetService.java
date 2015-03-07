package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetAttributePointsCommand;

public class DefaultCharGenStandardRulesetService implements
        CharGenStandardRulesetService {

    private Integer               attributes;
    private final CommandExecutor comExec;

    public DefaultCharGenStandardRulesetService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Integer getAttributesPoints() {
        if (attributes == null) {
            attributes = getCommandExecutor().execute(
                    new GetAttributePointsCommand());
        }

        return attributes;
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
