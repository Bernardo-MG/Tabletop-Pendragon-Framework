package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.inventory;

import java.io.Reader;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.XMLFileParser;
import com.wandrell.tabletop.pendragon.model.inventory.RangedWeapon;
import com.wandrell.tabletop.pendragon.model.inventory.Weapon;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.xml.input.inventory.WeaponDocumentParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadRangedWeaponDocumentParser {

    private Weapon weapon;

    public ITReadRangedWeaponDocumentParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Weapon> parserDoc;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        parserDoc = new WeaponDocumentParser(modelService);
        parserFile = new XMLFileParser();

        weapon = parserDoc.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.WEAPON_RANGED)));
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
    public final void testRangedWeapon() {
        Assert.assertTrue(weapon instanceof RangedWeapon);

        Assert.assertEquals(((RangedWeapon) weapon).getMaxRange(),
                (Integer) 123);
        Assert.assertEquals(((RangedWeapon) weapon).getRoundsToReload(),
                (Integer) 4);
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
