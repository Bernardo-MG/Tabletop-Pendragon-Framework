package com.wandrell.tabletop.pendragon.service.model.command;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.google.common.base.Predicate;
import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.pendragon.model.character.stats.PendragonSkillBox;
import com.wandrell.tabletop.pendragon.util.tag.service.PendragonSkillBoxRepositoryAware;
import com.wandrell.util.ResourceUtils;

public final class GetInitialSkillsCommand implements
        ReturnCommand<Collection<PendragonSkillBox>>,
        PendragonSkillBoxRepositoryAware {

    private Repository<PendragonSkillBox> skillRepo;
    private Collection<PendragonSkillBox> skills;

    public GetInitialSkillsCommand() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void execute() {
        final Yaml yaml;
        final Collection<Map<String, String>> data;
        Collection<PendragonSkillBox> query;

        yaml = new Yaml();

        data = (Collection<Map<String, String>>) yaml.load(ResourceUtils
                .getClassPathReader("config/stat/pendragon_skill_initial.yml"));

        skills = new LinkedList<>();
        for (final Map<String, String> value : data) {
            query = getPendragonSkillBoxRepository().getCollection(
                    new Predicate<PendragonSkillBox>() {

                        @Override
                        public final boolean
                                apply(final PendragonSkillBox skill) {
                            return skill.getName().equals(value.get("name"));
                        }

                    });

            if (!query.isEmpty()) {
                skills.add(query.iterator().next());
            }
        }
    }

    @Override
    public final Collection<PendragonSkillBox> getResult() {
        return skills;
    }

    @Override
    public final void setPendragonSkillRepository(
            final Repository<PendragonSkillBox> repository) {
        skillRepo = repository;
    }

    private final Repository<PendragonSkillBox>
            getPendragonSkillBoxRepository() {
        return skillRepo;
    }

}
