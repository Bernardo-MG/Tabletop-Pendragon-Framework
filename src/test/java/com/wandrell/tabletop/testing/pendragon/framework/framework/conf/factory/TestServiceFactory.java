package com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory;

import java.util.Collection;
import java.util.Map;

import org.mockito.Mockito;

import com.wandrell.tabletop.business.model.pendragon.chargen.ReligionBonus;
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
            public final ReligionBonus getReligionBonus(final String name,
                    final Collection<String> traits,
                    final Map<String, Integer> bonusDerived,
                    final Integer bonusArmor, final Integer bonusDamage,
                    final Integer bonusDamageDice) {
                final ReligionBonus religion;

                religion = Mockito.mock(ReligionBonus.class);

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

        };
    }

}
