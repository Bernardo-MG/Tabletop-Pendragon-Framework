package com.wandrell.tabletop.pendragon.framework.util;

import com.wandrell.tabletop.character.Gender;
import com.wandrell.tabletop.dice.RollTable;
import com.wandrell.tabletop.pendragon.conf.factory.PendragonRulesFactory;
import com.wandrell.tabletop.pendragon.valuehandler.Skill;

public final class PendragonRulesUtils {

    private static RollTable<Integer> featuresCount;

    public static final Integer getFeaturesCount(final Integer appearance) {
	if (featuresCount == null) {
	    featuresCount = PendragonRulesFactory.getInstance()
		    .getFeaturesCountTable();
	}

	return featuresCount.getValue(appearance);
    }

    public static final Boolean isSkillAbleToBeCharGenExcellentSkill(
	    final Skill skill, final Gender gender) {
	final Boolean result;
	final Boolean combat;

	switch (gender) {
	case MALE:
	    result = true;
	    break;
	case FEMALE:
	    // TODO: Read from a file or something
	    combat = (skill.getName().equals("dagger"))
		    || (skill.getName().equals("siege"));

	    result = ((combat) || (!(skill.isCombatSkill())));
	    break;
	default:
	    result = false;
	}

	return result;
    }

    public static final Boolean isSkillAbleToBeCharGenExtraSkill(
	    final Skill skill, final Gender gender) {
	final Boolean result;
	final Boolean combat;

	switch (gender) {
	case MALE:
	    result = (!skill.isCombatSkill());
	    break;
	case FEMALE:
	    // TODO: Read from a file or something
	    combat = (skill.getName().equals("dagger"))
		    || (skill.getName().equals("siege"));

	    result = ((combat) || (!(skill.isCombatSkill())));
	    break;
	default:
	    result = false;
	}

	return result;
    }

    public static final Boolean isSkillAbleToBeCharGenIndividualDifferenceSkill(
	    final Skill skill) {
	return ((skill.isCombatSkill()) || (skill.getStoredValue() > 0));
    }

    public static final Integer weightCalculator(final Integer size) {
	return recursiveWeightCalculator(size);
    }

    private final static Integer recursiveWeightCalculator(final Integer size) {
	final int sizeValue;
	int result;

	if (size < 1000) {
	    sizeValue = size;
	} else {
	    sizeValue = 1000;
	}

	if (sizeValue <= 10) {
	    result = sizeValue * 10;
	} else {
	    // Calculates previous weight value
	    result = recursiveWeightCalculator(sizeValue - 1);
	    // This size weight is the 110% of the previous size's weight
	    result = Math.round(result + (float) (result * 0.1));

	    if (sizeValue == 11) {
		result++;
	    }

	}

	return result;
    }

    private PendragonRulesUtils() {
	super();
    }

}
