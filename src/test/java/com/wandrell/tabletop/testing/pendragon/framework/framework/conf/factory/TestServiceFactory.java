package com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory;

import java.util.LinkedHashMap;

import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.pendragon.model.character.Horse;
import com.wandrell.tabletop.pendragon.model.chargen.AdditionalBelongings;
import com.wandrell.tabletop.pendragon.model.chargen.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.model.chargen.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.model.inventory.Item;
import com.wandrell.tabletop.pendragon.model.inventory.Shield;
import com.wandrell.tabletop.pendragon.model.inventory.Weapon;
import com.wandrell.tabletop.pendragon.model.manor.Pet;
import com.wandrell.tabletop.pendragon.service.model.ModelService;
import com.wandrell.tabletop.skill.NameAndDescriptor;
import com.wandrell.tabletop.testing.pendragon.framework.framework.service.TestModelService;

public final class TestServiceFactory {

    private static final TestServiceFactory instance = new TestServiceFactory();

    public static final TestServiceFactory getInstance() {
        return instance;
    }

    private TestServiceFactory() {
        super();
    }

    public final Repository<AdditionalBelongingsTable>
            getAdditionalBelongingsTableRepository() {
        final Repository<AdditionalBelongingsTable> repository;

        repository = new CollectionRepository<AdditionalBelongingsTable>();

        repository.add(getModelService().getAdditionalBelongingsTable(
                "luck_female",
                new LinkedHashMap<Interval, AdditionalBelongings>()));
        repository.add(getModelService().getAdditionalBelongingsTable(
                "luck_male",
                new LinkedHashMap<Interval, AdditionalBelongings>()));

        return repository;
    }

    public final Repository<FamilyCharacteristicTemplate>
            getFamilyCharacteristicTemplateRepository() {
        final Repository<FamilyCharacteristicTemplate> repository;

        repository = new CollectionRepository<FamilyCharacteristicTemplate>();

        repository.add(getModelService().getFamilyCharacteristicTemplate(
                "char_female", new LinkedHashMap<String, Integer>(),
                new LinkedHashMap<NameAndDescriptor, Integer>()));
        repository.add(getModelService().getFamilyCharacteristicTemplate(
                "char_male", new LinkedHashMap<String, Integer>(),
                new LinkedHashMap<NameAndDescriptor, Integer>()));

        return repository;
    }

    public final Repository<Horse> getHorseRepository() {
        final Repository<Horse> repository;

        repository = new CollectionRepository<Horse>();

        repository.add(getModelService().getHorse("horse_1", 1, 2, 3, 4, 5, 6,
                7, true, true, true));
        repository.add(getModelService().getHorse("horse_2", 1, 2, 3, 4, 5, 6,
                7, true, true, true));
        repository.add(getModelService().getHorse("horse_3", 1, 2, 3, 4, 5, 6,
                7, true, true, true));
        repository.add(getModelService().getHorse("horse_4", 1, 2, 3, 4, 5, 6,
                7, true, true, true));
        repository.add(getModelService().getHorse("horse_5", 1, 2, 3, 4, 5, 6,
                7, true, true, true));

        return repository;
    }

    public final Repository<Item> getItemRepository() {
        final Repository<Item> repository;

        repository = new CollectionRepository<Item>();

        repository.add(getModelService().getItem("item_1", "description_1"));
        repository.add(getModelService().getItem("item_2", "description_2"));
        repository.add(getModelService().getItem("item_3", "description_3"));
        repository.add(getModelService().getItem("item_4", "description_4"));
        repository.add(getModelService().getItem("item_5", "description_5"));

        return repository;
    }

    public final ModelService getModelService() {
        return new TestModelService();
    }

    public final Repository<Pet> getPetRepository() {
        final Repository<Pet> repository;

        repository = new CollectionRepository<Pet>();

        repository.add(getModelService().getPet("pet_1", null));
        repository.add(getModelService().getPet("pet_2", null));
        repository.add(getModelService().getPet("pet_3", null));
        repository.add(getModelService().getPet("pet_4", null));
        repository.add(getModelService().getPet("pet_5", null));

        return repository;
    }

    public final Repository<Shield> getShieldRepository() {
        final Repository<Shield> repository;

        repository = new CollectionRepository<Shield>();

        repository.add(getModelService().getShield("shield_1", "description_1",
                0));
        repository.add(getModelService().getShield("shield_2", "description_2",
                0));
        repository.add(getModelService().getShield("shield_3", "description_3",
                0));
        repository.add(getModelService().getShield("shield_4", "description_4",
                0));
        repository.add(getModelService().getShield("shield_5", "description_5",
                0));

        return repository;
    }

    public final Repository<Weapon> getWeaponRepository() {
        final Repository<Weapon> repository;

        repository = new CollectionRepository<Weapon>();

        repository.add(getModelService().getWeapon("weapon_1", "description_1",
                "skill_1", false, 0, 0, 0, 0, 0, null, false, false, false,
                false, false));
        repository.add(getModelService().getWeapon("weapon_2", "description_2",
                "skill_2", false, 0, 0, 0, 0, 0, null, false, false, false,
                false, false));
        repository.add(getModelService().getWeapon("weapon_3", "description_3",
                "skill_3", false, 0, 0, 0, 0, 0, null, false, false, false,
                false, false));
        repository.add(getModelService().getWeapon("weapon_4", "description_4",
                "skill_4", false, 0, 0, 0, 0, 0, null, false, false, false,
                false, false));
        repository.add(getModelService().getWeapon("weapon_5", "description_5",
                "skill_5", false, 0, 0, 0, 0, 0, null, false, false, false,
                false, false));

        return repository;
    }

}
