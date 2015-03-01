package com.wandrell.tabletop.pendragon.service.chargen.command;

import java.util.Collection;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.character.Gender;
import com.wandrell.tabletop.pendragon.conf.FileConfig;
import com.wandrell.tabletop.pendragon.model.stats.Skill;
import com.wandrell.util.ResourceUtils;

public final class IsAbleBecomeExcellentCommand implements
        ReturnCommand<Boolean> {

    private final Gender gender;
    private final Skill  skill;

    public IsAbleBecomeExcellentCommand(final Skill skill, final Gender gender) {
        super();

        this.gender = gender;
        this.skill = skill;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Boolean execute() {
        final Boolean result;
        final Boolean valid;
        final Yaml yaml;
        final Collection<String> skills;
        Map<String, Object> values;

        switch (gender) {
            case MALE:
                result = true;
                break;
            case FEMALE:
                yaml = new Yaml();

                values = (Map<String, Object>) yaml.load(ResourceUtils
                        .getClassPathReader(FileConfig.RULESET_CHARGEN_CONFIG));
                values = (Map<String, Object>) values.get("excellentSkills");
                skills = (Collection<String>) values.get("female");

                valid = skills.contains(skill.getName());

                result = ((valid) || (!skill.isCombatSkill()));
                break;
            default:
                result = false;
        }

        return result;
    }

}
