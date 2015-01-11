package com.wandrell.tabletop.testing.pendragon.framework.framework.service;

import java.util.Collection;
import java.util.Map;

import org.mockito.Mockito;

import com.wandrell.tabletop.business.model.dice.Dice;
import com.wandrell.tabletop.business.model.interval.Interval;
import com.wandrell.tabletop.business.model.pendragon.chargen.FamilyCharacteristicTable;
import com.wandrell.tabletop.business.model.pendragon.chargen.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.business.model.pendragon.chargen.FatherClassTemplate;
import com.wandrell.tabletop.business.model.pendragon.chargen.HomelandTemplate;
import com.wandrell.tabletop.business.model.pendragon.chargen.ReligionTemplate;
import com.wandrell.tabletop.business.model.pendragon.glory.FatherClassGlory;
import com.wandrell.tabletop.business.model.pendragon.inventory.Armor;
import com.wandrell.tabletop.business.model.pendragon.inventory.ArmorType;
import com.wandrell.tabletop.business.model.pendragon.inventory.Item;
import com.wandrell.tabletop.business.model.pendragon.inventory.Money;
import com.wandrell.tabletop.business.model.pendragon.inventory.RangedWeapon;
import com.wandrell.tabletop.business.model.pendragon.inventory.Shield;
import com.wandrell.tabletop.business.model.pendragon.inventory.Weapon;
import com.wandrell.tabletop.business.model.pendragon.stats.DirectedTrait;
import com.wandrell.tabletop.business.model.pendragon.stats.Passion;
import com.wandrell.tabletop.business.model.pendragon.stats.Skill;
import com.wandrell.tabletop.business.model.pendragon.stats.SpecialtySkill;
import com.wandrell.tabletop.business.model.pendragon.util.TextList;
import com.wandrell.tabletop.business.model.skill.NameAndDescriptor;
import com.wandrell.tabletop.business.model.valuebox.DefaultEditableValueBox;
import com.wandrell.tabletop.business.model.valuebox.EditableValueBox;
import com.wandrell.tabletop.business.service.pendragon.ModelService;

public final class TestModelService implements ModelService {

    @Override
    public final Armor getArmor(final String name, final String description,
            final Money money, final ArmorType type, final Integer armorValue,
            final Integer dexModifier, final Boolean heavyLoad) {
        final Armor armor;

        armor = Mockito.mock(Armor.class);

        Mockito.when(armor.getName()).thenReturn(name);

        Mockito.when(armor.getDescription()).thenReturn(description);
        Mockito.when(armor.getMoney()).thenReturn(money);
        Mockito.when(armor.getArmorType()).thenReturn(type);
        Mockito.when(armor.getArmorValue()).thenReturn(armorValue);
        Mockito.when(armor.getDexterityModifier()).thenReturn(dexModifier);
        Mockito.when(armor.isHeavyLoad()).thenReturn(heavyLoad);

        return armor;
    }

    @Override
    public final DirectedTrait getDirectedTrait(final String name) {
        final DirectedTrait trait;

        trait = Mockito.mock(DirectedTrait.class);

        Mockito.when(trait.getName()).thenReturn(name);

        return trait;
    }

    @Override
    public final FamilyCharacteristicTable getFamilyCharacteristicTable(
            final String name,
            final Map<Interval, FamilyCharacteristicTemplate> intervals) {
        final FamilyCharacteristicTable table;

        table = Mockito.mock(FamilyCharacteristicTable.class);

        Mockito.when(table.getName()).thenReturn(name);
        Mockito.when(table.getIntervals()).thenReturn(intervals);

        return table;
    }

    @Override
    public final FamilyCharacteristicTemplate getFamilyCharacteristicTemplate(
            final String name, final Map<String, Integer> attributes,
            final Map<NameAndDescriptor, Integer> skills) {
        final FamilyCharacteristicTemplate template;

        template = Mockito.mock(FamilyCharacteristicTemplate.class);

        Mockito.when(template.getAttributes()).thenReturn(attributes);
        Mockito.when(template.getFamilyCharacteristic()).thenReturn(name);
        Mockito.when(template.getSkills()).thenReturn(skills);

        return template;
    }

    @Override
    public final FatherClassGlory getFatherClassGlory(final String name,
            final Integer glory, final Integer yearlyGlory) {
        final FatherClassGlory fatherGlory;

        fatherGlory = Mockito.mock(FatherClassGlory.class);

        Mockito.when(fatherGlory.getFatherClass()).thenReturn(name);
        Mockito.when(fatherGlory.getBaseGlory()).thenReturn(glory);
        Mockito.when(fatherGlory.getYearlyGlory()).thenReturn(yearlyGlory);

        return fatherGlory;
    }

    @Override
    public final FatherClassTemplate getFatherClassTemplate(final String name,
            final Integer skillsGroupPoints,
            final Integer skillsGroupPointsDivide, final Integer skillsPoints,
            final Integer skillsNonCombatPoints, final Dice money,
            final Collection<NameAndDescriptor> skillsGroup,
            final Map<String, Integer> specialtySkills,
            final Map<NameAndDescriptor, Integer> directedTraits,
            final Map<NameAndDescriptor, Integer> directedTraitsBase) {
        final FatherClassTemplate fatherClass;

        fatherClass = Mockito.mock(FatherClassTemplate.class);

        Mockito.when(fatherClass.getName()).thenReturn(name);

        Mockito.when(fatherClass.getSkillsGroupBonusPoints()).thenReturn(
                skillsGroupPoints);
        Mockito.when(fatherClass.getSkillsGroupDividePoints()).thenReturn(
                skillsGroupPointsDivide);

        Mockito.when(fatherClass.getSkillsPoints()).thenReturn(skillsPoints);
        Mockito.when(fatherClass.getNonCombatSkillBonus()).thenReturn(
                skillsNonCombatPoints);

        Mockito.when(fatherClass.getMoney()).thenReturn(money);

        Mockito.when(fatherClass.getSkillsGroup()).thenReturn(skillsGroup);

        Mockito.when(fatherClass.getSpecialtySkills()).thenReturn(
                specialtySkills);

        Mockito.when(fatherClass.getDirectedTraits())
                .thenReturn(directedTraits);
        Mockito.when(fatherClass.getDirectedTraitsBase()).thenReturn(
                directedTraitsBase);

        return fatherClass;
    }

    @Override
    public final HomelandTemplate getHomelandTemplate(final String name,
            final Map<NameAndDescriptor, Integer> skills,
            final Map<String, Integer> specialtySkills,
            final Map<String, Integer> traits,
            final Collection<NameAndDescriptor> directedTraits,
            final Collection<NameAndDescriptor> passions) {
        final HomelandTemplate homeland;

        homeland = Mockito.mock(HomelandTemplate.class);

        Mockito.when(homeland.getHomeland()).thenReturn(name);
        Mockito.when(homeland.getSkills()).thenReturn(skills);
        Mockito.when(homeland.getSpecialtySkills()).thenReturn(specialtySkills);
        Mockito.when(homeland.getTraits()).thenReturn(traits);
        Mockito.when(homeland.getDirectedTraits()).thenReturn(directedTraits);
        Mockito.when(homeland.getPassions()).thenReturn(passions);

        return homeland;
    }

    @Override
    public final Item getItem(final String name, final String description) {
        final Item item;

        item = Mockito.mock(Item.class);

        Mockito.when(item.getName()).thenReturn(name);
        Mockito.when(item.getDescription()).thenReturn(description);

        return item;
    }

    @Override
    public final Money getMoney(final Integer denarii, final Integer libra) {
        final Money money;
        final EditableValueBox denariiValue;
        final EditableValueBox libraValue;

        money = Mockito.mock(Money.class);

        denariiValue = new DefaultEditableValueBox(denarii, 0,
                Integer.MAX_VALUE);
        libraValue = new DefaultEditableValueBox(libra, 0, Integer.MAX_VALUE);

        Mockito.when(money.getDenarii()).thenReturn(denariiValue);
        Mockito.when(money.getLibra()).thenReturn(libraValue);

        return money;
    }

    @Override
    public final Passion getPassion(final String name, final String descriptor,
            final Boolean repeatable) {
        final Passion passion;

        passion = Mockito.mock(Passion.class);

        Mockito.when(passion.getName()).thenReturn(name);
        Mockito.when(passion.getDescriptor()).thenReturn(descriptor);
        Mockito.when(passion.isDescribed()).thenReturn(repeatable);

        return passion;
    }

    @Override
    public final ReligionTemplate getReligionTemplate(final String name,
            final Collection<String> traits,
            final Map<String, Integer> bonusDerived, final Integer bonusArmor,
            final Integer bonusDamage, final Integer bonusDamageDice) {
        final ReligionTemplate religion;

        religion = Mockito.mock(ReligionTemplate.class);

        Mockito.when(religion.getArmorBonus()).thenReturn(bonusArmor);
        Mockito.when(religion.getDamageBonus()).thenReturn(bonusDamage);
        Mockito.when(religion.getDamageDiceBonus()).thenReturn(bonusDamageDice);
        Mockito.when(religion.getDerivedAttributeBonus()).thenReturn(
                bonusDerived);
        Mockito.when(religion.getReligiousTraits()).thenReturn(traits);
        Mockito.when(religion.getReligion()).thenReturn(name);

        return religion;
    }

    @Override
    public final Shield getShield(final String name, final String description,
            final Money money, final Integer armorValue) {
        final Shield shield;

        shield = Mockito.mock(Shield.class);

        Mockito.when(shield.getName()).thenReturn(name);
        Mockito.when(shield.getDescription()).thenReturn(description);
        Mockito.when(shield.getMoney()).thenReturn(money);
        Mockito.when(shield.getArmorValue()).thenReturn(armorValue);

        return shield;
    }

    @Override
    public final Skill getSkill(final String name, final String descriptor,
            final Boolean combat, final Boolean court, final Boolean knight,
            final Boolean knowledge, final Boolean repeat) {
        final Skill skill;

        skill = Mockito.mock(Skill.class);

        Mockito.when(skill.getName()).thenReturn(name);
        Mockito.when(skill.getDescriptor()).thenReturn(descriptor);

        Mockito.when(skill.isDescribed()).thenReturn(repeat);

        Mockito.when(skill.isCombatSkill()).thenReturn(combat);
        Mockito.when(skill.isCourtlySkill()).thenReturn(court);
        Mockito.when(skill.isKnightlySkill()).thenReturn(knight);
        Mockito.when(skill.isKnowledgeSkill()).thenReturn(knowledge);

        return skill;
    }

    @Override
    public final SpecialtySkill getSpecialtySkill(final String name,
            final Collection<String> skills) {
        final SpecialtySkill skill;

        skill = Mockito.mock(SpecialtySkill.class);

        Mockito.when(skill.getName()).thenReturn(name);
        Mockito.when(skill.getSurrogatedSkills()).thenReturn(skills);

        return skill;
    }

    @Override
    public final TextList getTextList(final String name,
            final Collection<String> values) {
        final TextList list;

        list = Mockito.mock(TextList.class);

        Mockito.when(list.getName()).thenReturn(name);
        Mockito.when(list.getValues()).thenReturn(values);

        return list;
    }

    @Override
    public final Weapon getWeapon(final String name, final String description,
            final Money money, final String skill, final Boolean twoHanded,
            final Integer damageBonus, final Integer diceBonus,
            final Integer damageOverride, final Integer range,
            final Integer rof, final Map<ArmorType, Integer> armorBonus,
            final Boolean breaksEnemyOnDraw, final Boolean breaksOnFumble,
            final Boolean hitsBack, final Boolean ignoresShield,
            final Boolean reducesShieldToRoll) {
        final Weapon weapon;

        if ((range <= 0) || (rof <= 0)) {
            weapon = Mockito.mock(Weapon.class);
        } else {
            weapon = Mockito.mock(RangedWeapon.class);

            Mockito.when(((RangedWeapon) weapon).getMaxRange()).thenReturn(
                    range);
            Mockito.when(((RangedWeapon) weapon).getRoundsToReload())
                    .thenReturn(rof);
        }

        Mockito.when(weapon.getName()).thenReturn(name);
        Mockito.when(weapon.getDescription()).thenReturn(description);
        Mockito.when(weapon.getMoney()).thenReturn(money);

        Mockito.when(weapon.getSkill()).thenReturn(skill);
        Mockito.when(weapon.isTwoHanded()).thenReturn(twoHanded);
        Mockito.when(weapon.getDamageBonus()).thenReturn(damageBonus);
        Mockito.when(weapon.getDamageDiceBonus()).thenReturn(diceBonus);

        Mockito.when(weapon.isBreakingEnemyOnDraw()).thenReturn(
                breaksEnemyOnDraw);
        Mockito.when(weapon.isBreakingOnFumble()).thenReturn(breaksOnFumble);
        Mockito.when(weapon.isHittingBack()).thenReturn(hitsBack);
        Mockito.when(weapon.isIgnoringShield()).thenReturn(ignoresShield);
        Mockito.when(weapon.isReducingShieldToRoll()).thenReturn(
                reducesShieldToRoll);

        Mockito.when(weapon.getDamageOverrideDice()).thenReturn(damageOverride);

        Mockito.when(weapon.getArmorBonusDice()).thenReturn(armorBonus);

        return weapon;
    }

}
