package com.wandrell.tabletop.pendragon.procedure.util.glory;

import com.wandrell.tabletop.pendragon.model.character.PendragonBaseCharacter;
import com.wandrell.tabletop.pendragon.model.character.PendragonHumanCharacter;
import com.wandrell.tabletop.pendragon.procedure.glory.GloryEvaluator;
import com.wandrell.tabletop.pendragon.procedure.stats.TraitsAchievementEvaluator;

public final class TraitsAchievementGloryEvaluator implements GloryEvaluator {

    private final TraitsAchievementEvaluator evaluator;
    private final Integer                    glory;
    private final String                     name;

    public TraitsAchievementGloryEvaluator(final String name,
            final Integer glory, final TraitsAchievementEvaluator evaluator) {
        super();

        this.name = name;
        this.glory = glory;
        this.evaluator = evaluator;
    }

    @Override
    public final Integer getGlory() {
        return glory;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Boolean isApplicable(final PendragonBaseCharacter character) {
        final Boolean result;

        if (character instanceof PendragonHumanCharacter) {
            result = getTraitsAchievementEvaluator().isFulfilling(
                    (PendragonHumanCharacter) character);
        } else {
            result = false;
        }

        return result;
    }

    private final TraitsAchievementEvaluator getTraitsAchievementEvaluator() {
        return evaluator;
    }

}
