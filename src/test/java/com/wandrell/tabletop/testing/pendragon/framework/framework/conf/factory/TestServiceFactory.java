package com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory;

import java.util.Collection;
import java.util.Map;

import org.mockito.Mockito;

import com.wandrell.tabletop.business.model.pendragon.chargen.HomelandTemplate;
import com.wandrell.tabletop.business.model.pendragon.chargen.ReligionTemplate;
import com.wandrell.tabletop.business.model.skill.DefaultNameAndDescriptor;
import com.wandrell.tabletop.business.model.skill.NameAndDescriptor;
import com.wandrell.tabletop.business.service.pendragon.ModelService;

public final class TestServiceFactory {

    private static final TestServiceFactory instance = new TestServiceFactory();

    public static final TestServiceFactory getInstance() {
        return instance;
    }

    private TestServiceFactory() {
        super();
    }

    public final ModelService getModelService() {
        return new ModelService() {

            @Override
            public final HomelandTemplate getHomelandTemplate(
                    final String name,
                    final Map<NameAndDescriptor, Integer> skills,
                    final Map<String, Integer> specialtySkills,
                    final Map<String, Integer> traits,
                    final Collection<NameAndDescriptor> directedTraits,
                    final Collection<NameAndDescriptor> passions) {
                final HomelandTemplate homeland;

                homeland = Mockito.mock(HomelandTemplate.class);

                Mockito.when(homeland.getHomeland()).thenReturn(name);
                Mockito.when(homeland.getSkills()).thenReturn(skills);
                Mockito.when(homeland.getSpecialtySkills()).thenReturn(
                        specialtySkills);
                Mockito.when(homeland.getTraits()).thenReturn(traits);
                Mockito.when(homeland.getDirectedTraits()).thenReturn(
                        directedTraits);
                Mockito.when(homeland.getPassions()).thenReturn(passions);

                return homeland;
            }

            @Override
            public final ReligionTemplate getReligionTemplate(
                    final String name, final Collection<String> traits,
                    final Map<String, Integer> bonusDerived,
                    final Integer bonusArmor, final Integer bonusDamage,
                    final Integer bonusDamageDice) {
                final ReligionTemplate religion;

                religion = Mockito.mock(ReligionTemplate.class);

                Mockito.when(religion.getArmorBonus()).thenReturn(bonusArmor);
                Mockito.when(religion.getDamageBonus()).thenReturn(bonusDamage);
                Mockito.when(religion.getDamageDiceBonus()).thenReturn(
                        bonusDamageDice);
                Mockito.when(religion.getDerivedAttributeBonus()).thenReturn(
                        bonusDerived);
                Mockito.when(religion.getReligiousTraits()).thenReturn(traits);
                Mockito.when(religion.getReligion()).thenReturn(name);

                return religion;
            }

            @Override
            public final NameAndDescriptor getSkillData(final String name,
                    final String descriptor) {
                return new DefaultNameAndDescriptor(name, descriptor);
            }

        };
    }

}
