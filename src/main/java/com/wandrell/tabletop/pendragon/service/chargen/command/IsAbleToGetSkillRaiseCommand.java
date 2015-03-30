package com.wandrell.tabletop.pendragon.service.chargen.command;

import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.conf.FileConfig;
import com.wandrell.tabletop.pendragon.model.character.stats.PendragonSkillBox;
import com.wandrell.util.ResourceUtils;

public final class IsAbleToGetSkillRaiseCommand implements
        ReturnCommand<Boolean> {

    private final PendragonSkillBox skill;
    private Boolean                 valid;

    public IsAbleToGetSkillRaiseCommand(final PendragonSkillBox skill) {
        super();

        this.skill = skill;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void execute() {
        final Yaml yaml;
        final Integer max;
        Map<String, Object> values;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(ResourceUtils
                .getClassPathReader(FileConfig.RULESET_CHARGEN_CONFIG));
        values = (Map<String, Object>) values.get("individualDiff");
        values = (Map<String, Object>) values.get("skill");
        max = (Integer) values.get("max");

        valid = ((skill.getValue() < max) && ((skill.isCombatSkill()) || (skill
                .getValue() > 0)));
    }

    @Override
    public final Boolean getResult() {
        return valid;
    }

}
