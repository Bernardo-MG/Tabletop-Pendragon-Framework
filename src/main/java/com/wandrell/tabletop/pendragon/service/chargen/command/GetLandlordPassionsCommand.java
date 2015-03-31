package com.wandrell.tabletop.pendragon.service.chargen.command;

import java.util.Collection;

import com.wandrell.pattern.command.ResultCommand;
import com.wandrell.tabletop.pendragon.service.model.StatConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.stats.StatsYAMLParser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.stats.StatsYAMLParser.StatBuilder;
import com.wandrell.tabletop.pendragon.util.tag.service.StatConstructorServiceAware;
import com.wandrell.tabletop.valuebox.SkillBox;
import com.wandrell.util.ResourceUtils;

public final class GetLandlordPassionsCommand implements
        ResultCommand<Collection<SkillBox>>, StatConstructorServiceAware {

    private Collection<SkillBox>   skills;
    private StatConstructorService statConsService;

    public GetLandlordPassionsCommand() {
        super();
    }

    @Override
    public final void execute() {
        final StatsYAMLParser<SkillBox> parser;

        parser = new StatsYAMLParser<SkillBox>(new StatBuilder<SkillBox>() {

            @Override
            public final SkillBox getStat(final String name,
                    final String descriptor, final Integer value) {
                return getStatConstructorService().getDirectedTrait(name,
                        descriptor, value);
            }

        });

        skills = parser
                .parse(ResourceUtils
                        .getClassPathReader("config/stat/pendragon_passion_landlord.yml"));
    }

    @Override
    public final Collection<SkillBox> getResult() {
        return skills;
    }

    @Override
    public final void setStatConstructorService(
            final StatConstructorService service) {
        statConsService = service;
    }

    private final StatConstructorService getStatConstructorService() {
        return statConsService;
    }

}
