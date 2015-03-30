package com.wandrell.tabletop.pendragon.service.model;

import java.util.Collection;
import java.util.Map;

import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.interval.IntervalTable;
import com.wandrell.tabletop.pendragon.model.character.DefaultHorse;
import com.wandrell.tabletop.pendragon.model.character.Horse;
import com.wandrell.tabletop.pendragon.model.character.background.DefaultReligion;
import com.wandrell.tabletop.pendragon.model.character.background.Religion;
import com.wandrell.tabletop.pendragon.model.character.stats.DefaultPendragonSkillBox;
import com.wandrell.tabletop.pendragon.model.character.stats.DerivedAttributesHolder;
import com.wandrell.tabletop.pendragon.model.character.stats.HumanAttributesHolder;
import com.wandrell.tabletop.pendragon.model.character.stats.PendragonSkillBox;
import com.wandrell.tabletop.pendragon.model.character.stats.TraitsHolder;
import com.wandrell.tabletop.pendragon.model.chargen.background.CultureCharacterTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.background.CultureTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.background.DefaultCultureCharacterTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.background.DefaultCultureTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.background.DefaultFamilyCharacteristicTable;
import com.wandrell.tabletop.pendragon.model.chargen.background.DefaultFamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.background.DefaultFatherClassTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.background.FamilyCharacteristicTable;
import com.wandrell.tabletop.pendragon.model.chargen.background.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.background.FatherClassTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.inventory.AdditionalBelongings;
import com.wandrell.tabletop.pendragon.model.chargen.inventory.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.model.chargen.inventory.DefaultAdditionalBelongings;
import com.wandrell.tabletop.pendragon.model.chargen.inventory.DefaultAdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.model.chargen.region.DefaultHomelandTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.region.DefaultRegionTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.region.HomelandTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.region.RegionTemplate;
import com.wandrell.tabletop.pendragon.model.glory.DefaultFatherClassGlory;
import com.wandrell.tabletop.pendragon.model.glory.FatherClassGlory;
import com.wandrell.tabletop.pendragon.model.inventory.DefaultItem;
import com.wandrell.tabletop.pendragon.model.inventory.DefaultMoney;
import com.wandrell.tabletop.pendragon.model.inventory.Item;
import com.wandrell.tabletop.pendragon.model.inventory.Money;
import com.wandrell.tabletop.pendragon.model.inventory.armor.Armor;
import com.wandrell.tabletop.pendragon.model.inventory.armor.ArmorType;
import com.wandrell.tabletop.pendragon.model.inventory.armor.DefaultArmor;
import com.wandrell.tabletop.pendragon.model.inventory.armor.DefaultShield;
import com.wandrell.tabletop.pendragon.model.inventory.armor.Shield;
import com.wandrell.tabletop.pendragon.model.inventory.weapon.DefaultRangedWeapon;
import com.wandrell.tabletop.pendragon.model.inventory.weapon.DefaultWeapon;
import com.wandrell.tabletop.pendragon.model.inventory.weapon.Weapon;
import com.wandrell.tabletop.pendragon.model.manor.AnimalYearResult;
import com.wandrell.tabletop.pendragon.model.manor.DefaultAnimalYearResult;
import com.wandrell.tabletop.pendragon.model.manor.DefaultPet;
import com.wandrell.tabletop.pendragon.model.manor.Pet;
import com.wandrell.tabletop.pendragon.model.util.DefaultTextList;
import com.wandrell.tabletop.pendragon.model.util.TextList;
import com.wandrell.tabletop.valuebox.SkillBox;

public final class DefaultModelConstructorService implements
        ModelConstructorService {

    public DefaultModelConstructorService() {
        super();
    }

    @Override
    public final AdditionalBelongings getAdditionaBelongings(
            final Boolean choose, final String moneyName, final Money money,
            final String rerollTable, final Collection<Dice> rerolls,
            final Collection<Horse> horses, final Collection<Item> items,
            final Collection<Pet> pets, final Collection<Shield> shields,
            final Collection<Weapon> weapons) {
        return new DefaultAdditionalBelongings(choose, money, moneyName,
                rerollTable, rerolls, items, horses, pets, shields, weapons);
    }

    @Override
    public final AdditionalBelongingsTable getAdditionalBelongingsTable(
            final String name,
            final Map<Interval, AdditionalBelongings> intervalsMap) {
        return new DefaultAdditionalBelongingsTable(name, intervalsMap);
    }

    @Override
    public final AnimalYearResult getAnimalYearResult(final String description,
            final String puppy, final Boolean dies, final Money money) {
        return new DefaultAnimalYearResult(description, money, puppy, dies);
    }

    @Override
    public final Armor getArmor(final String name, final String description,
            final Money money, final ArmorType type, final Integer armorValue,
            final Integer dexModifier, final Boolean heavyLoad) {
        return new DefaultArmor(name, description, money, type, armorValue,
                dexModifier, heavyLoad);
    }

    @Override
    public final CultureCharacterTemplate getCultureCharacterTemplate(
            final HumanAttributesHolder attributesBonus,
            final Map<String, Dice> attributesRandom,
            final Collection<SkillBox> skillsBonus,
            final Collection<SkillBox> specialtySkills,
            final Collection<SkillBox> passionsBonus,
            final Map<SkillBox, Dice> passionsRandom,
            final Collection<SkillBox> directedBonus,
            final TraitsHolder traitsBonus) {
        return new DefaultCultureCharacterTemplate(attributesBonus,
                attributesRandom, skillsBonus, specialtySkills, traitsBonus,
                directedBonus, passionsBonus, passionsRandom);
    }

    @Override
    public final CultureTemplate getCultureTemplate(final String name,
            final FamilyCharacteristicTemplate charMale,
            final FamilyCharacteristicTemplate charFemale,
            final AdditionalBelongingsTable belonginsMale,
            final AdditionalBelongingsTable belonginsFemale,
            final CultureCharacterTemplate templateMale,
            final CultureCharacterTemplate templateFemale) {
        return new DefaultCultureTemplate(name, templateFemale, templateMale,
                belonginsFemale, belonginsMale, charFemale, charMale);
    }

    @Override
    public final FamilyCharacteristicTable getFamilyCharacteristicTable(
            final String name,
            final Map<Interval, FamilyCharacteristicTemplate> intervals) {
        return new DefaultFamilyCharacteristicTable(name, intervals);
    }

    @Override
    public final FamilyCharacteristicTemplate getFamilyCharacteristicTemplate(
            final String name, final HumanAttributesHolder attributes,
            final Collection<SkillBox> skills) {
        return new DefaultFamilyCharacteristicTemplate(name, attributes, skills);
    }

    @Override
    public final FatherClassGlory getFatherClassGlory(final String name,
            final Integer glory, final Integer yearlyGlory) {
        return new DefaultFatherClassGlory(name, glory, yearlyGlory);
    }

    @Override
    public final FatherClassTemplate getFatherClassTemplate(final String name,
            final Integer skillsGroupPoints,
            final Integer skillsGroupPointsDivide, final Integer skillsPoints,
            final Integer skillsNonCombatPoints, final Dice money,
            final Collection<SkillBox> skillsGroup,
            final Collection<SkillBox> specialtySkills,
            final Collection<SkillBox> directedTraits,
            final Collection<SkillBox> directedTraitsBase) {
        return new DefaultFatherClassTemplate(name, skillsGroupPoints,
                skillsGroupPointsDivide, skillsPoints, skillsNonCombatPoints,
                money, skillsGroup, specialtySkills, directedTraits,
                directedTraitsBase);
    }

    @Override
    public final HomelandTemplate getHomelandTemplate(final String name,
            final RegionTemplate region, final Collection<SkillBox> skills,
            final Collection<SkillBox> specialtySkills,
            final Collection<SkillBox> directedTraits,
            final Collection<SkillBox> passions) {
        return new DefaultHomelandTemplate(name, region, skills,
                specialtySkills, directedTraits, passions);
    }

    @Override
    public final Horse getHorse(final String type, final Integer constitution,
            final Integer dexterity, final Integer size,
            final Integer strength, final Integer damage,
            final Integer movement, final Integer armor, final Boolean armored,
            final Boolean combat, final Boolean hunting) {
        final Horse horse;

        horse = new DefaultHorse(type, null, type, armor, damage, movement,
                combat, hunting, armored);

        horse.getAttributes().setConstitution(constitution);
        horse.getAttributes().setDexterity(dexterity);
        horse.getAttributes().setSize(size);
        horse.getAttributes().setStrength(strength);

        return horse;
    }

    @Override
    public final Item getItem(final String name, final String description) {
        return new DefaultItem(name, description, getMoney(0, 0));
    }

    @Override
    public final Money getMoney(final Integer denarii, final Integer libra) {
        return new DefaultMoney(libra, denarii);
    }

    @Override
    public final Pet getPet(final String name,
            final IntervalTable<AnimalYearResult> yearResults) {
        return new DefaultPet(name, yearResults);
    }

    @Override
    public final RegionTemplate getRegionTemplate(final String name,
            final TraitsHolder traits) {
        return new DefaultRegionTemplate(name, traits);
    }

    @Override
    public final Religion getReligion(final String name,
            final Collection<String> traits,
            final DerivedAttributesHolder bonusDerived,
            final Integer bonusArmor, final Integer bonusDamage,
            final Integer bonusDamageDice) {
        return new DefaultReligion(name, traits, bonusDerived, bonusArmor,
                bonusDamage, bonusDamageDice);
    }

    @Override
    public final Shield getShield(final String name, final String description,
            final Integer armorValue) {
        return new DefaultShield(name, description, getMoney(0, 0), armorValue);
    }

    @Override
    public final PendragonSkillBox getSkill(final String name,
            final Boolean described, final String descriptor,
            final Boolean combat, final Boolean court, final Boolean knight,
            final Boolean knowledge) {
        final PendragonSkillBox skill;

        skill = new DefaultPendragonSkillBox(name, 0, combat, knight,
                knowledge, court);

        if (described) {
            skill.setDescriptor(descriptor);
        }

        skill.setDescribed(described);

        return skill;
    }

    @Override
    public final TextList getTextList(final String name,
            final Collection<String> values) {
        return new DefaultTextList(name, values);
    }

    @Override
    public final Weapon getWeapon(final String name, final String description,
            final String skill, final Boolean twoHanded,
            final Integer damageBonus, final Integer diceBonus,
            final Integer damageOverride, final Integer maxRange,
            final Integer reload, final Map<ArmorType, Integer> armorBonus,
            final Boolean breaksEnemyOnDraw, final Boolean breaksOnFumble,
            final Boolean hitsBack, final Boolean ignoresShield,
            final Boolean reducesShieldToRoll) {
        final Weapon weapon;

        if (maxRange == 0) {
            weapon = new DefaultWeapon(name, description, getMoney(0, 0),
                    skill, damageBonus, diceBonus, damageOverride, armorBonus,
                    breaksEnemyOnDraw, breaksOnFumble, hitsBack, ignoresShield,
                    reducesShieldToRoll, twoHanded);
        } else {
            weapon = new DefaultRangedWeapon(name, description, getMoney(0, 0),
                    skill, damageBonus, diceBonus, damageOverride, armorBonus,
                    breaksEnemyOnDraw, breaksOnFumble, hitsBack, ignoresShield,
                    reducesShieldToRoll, twoHanded, maxRange, reload);
        }

        return weapon;
    }

}
