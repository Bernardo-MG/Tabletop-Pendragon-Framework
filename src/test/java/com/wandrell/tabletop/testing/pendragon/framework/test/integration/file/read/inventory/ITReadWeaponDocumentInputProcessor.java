package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.inventory;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.pendragon.inventory.Weapon;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.inventory.WeaponDocumentInputProcessor;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;
import com.wandrell.util.parser.xml.input.JDOMStAXInputParser;

public final class ITReadWeaponDocumentInputProcessor {

    private Weapon weapon;

    public ITReadWeaponDocumentInputProcessor() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<Weapon> parser;
        final JDOMDocumentInputProcessor<Weapon> processor;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        processor = new WeaponDocumentInputProcessor(modelService);
        parser = new JDOMStAXInputParser<Weapon>(processor);

        weapon = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.WEAPON));
    }

    @Test
    public final void testArmorBonus() {
        Assert.assertEquals(weapon.getArmorBonusDice().size(), 0);
    }

    @Test
    public final void testDamageBonus() {
        Assert.assertEquals(weapon.getDamageBonus(), (Integer) 11);
    }

    @Test
    public final void testDamageDiceBonus() {
        Assert.assertEquals(weapon.getDamageDiceBonus(), (Integer) 22);
    }

    @Test
    public final void testDamageOverride() {
        Assert.assertEquals(weapon.getDamageOverrideDice(), (Integer) 0);
    }

    @Test
    public final void testFlags() {
        Assert.assertTrue(weapon.isBreakingEnemyOnDraw());
        Assert.assertTrue(!weapon.isBreakingOnFumble());
        Assert.assertTrue(!weapon.isHittingBack());
        Assert.assertTrue(weapon.isIgnoringShield());
        Assert.assertTrue(!weapon.isReducingShieldToRoll());
    }

    @Test
    public final void testName() {
        Assert.assertEquals(weapon.getName(), "test_weapon");
    }

    @Test
    public final void testSkill() {
        Assert.assertEquals(weapon.getSkill(), "skill_1");
    }

    @Test
    public final void testTwoHanded() {
        Assert.assertEquals(weapon.isTwoHanded(), (Boolean) false);
    }

}
