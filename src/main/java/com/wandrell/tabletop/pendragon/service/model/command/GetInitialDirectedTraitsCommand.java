package com.wandrell.tabletop.pendragon.service.model.command;

import java.util.Collection;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.service.model.StatConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.stats.StatsYAMLParser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.stats.StatsYAMLParser.StatBuilder;
import com.wandrell.tabletop.pendragon.util.tag.service.StatConstructorServiceAware;
import com.wandrell.tabletop.valuebox.SkillBox;
import com.wandrell.util.ResourceUtils;

public final class GetInitialDirectedTraitsCommand implements
        ReturnCommand<Collection<SkillBox>>, StatConstructorServiceAware {

    private StatConstructorService statConsService;

    public GetInitialDirectedTraitsCommand() {
        super();
    }

    @Override
    public final Collection<SkillBox> execute() {
        final StatsYAMLParser<SkillBox> parser;
        Collection<SkillBox> values;

        parser = new StatsYAMLParser<SkillBox>(new StatBuilder<SkillBox>() {

            @Override
            public final SkillBox getStat(final String name,
                    final String descriptor, final Integer value) {
                return getStatConstructorService().getDirectedTrait(name,
                        descriptor, value);
            }

        });

        values = parser
                .parse(ResourceUtils
                        .getClassPathReader("config/stat/pendragon_directed_trait_initial.yml"));

        return values;
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
