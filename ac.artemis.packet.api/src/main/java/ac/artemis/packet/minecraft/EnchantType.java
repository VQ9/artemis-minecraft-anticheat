package ac.artemis.packet.minecraft;

import java.util.HashMap;
import java.util.Map;

public enum EnchantType {
    PROTECTION_ENVIRONMENTAL("protection"),
    PROTECTION_FIRE("fire_protection"),
    PROTECTION_FALL("feather_falling"),
    PROTECTION_EXPLOSIONS("blast_protection"),
    PROTECTION_PROJECTILE("projectile_protection"),
    OXYGEN("respiration"),
    WATER_WORKER("aqua_affinity"),
    THORNS("thorns"),
    DEPTH_STRIDER("depth_strider"),
    FROST_WALKER("frost_walker"),
    BINDING_CURSE("binding_curse"),
    DAMAGE_ALL("sharpness"),
    DAMAGE_UNDEAD("smite"),
    DAMAGE_ARTHROPODS("bane_of_arthropods"),
    KNOCKBACK("knockback"),
    FIRE_ASPECT("fire_aspect"),
    LOOT_BONUS_MOBS("looting"),
    SWEEPING_EDGE("sweeping"),
    DIG_SPEED("efficiency"),
    SILK_TOUCH("silk_touch"),
    DURABILITY("unbreaking"),
    LOOT_BONUS_BLOCKS("fortune"),
    ARROW_DAMAGE("power"),
    ARROW_KNOCKBACK("punch"),
    ARROW_FIRE("flame"),
    ARROW_INFINITE("infinity"),
    LUCK("luck_of_the_sea"),
    LURE("lure"),
    LOYALTY("loyalty"),
    IMPALING("impaling"),
    RIPTIDE("riptide"),
    CHANNELING("channeling"),
    MENDING("mending"),
    VANISHING_CURSE("vanishing_curse");

    private static final Map<String, EnchantType> idToEnchantMap = new HashMap<>();
    private final String name;

    EnchantType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EnchantType getByName(final String name) {
        return idToEnchantMap.get(name);
    }

    static {
        for (EnchantType value : values()) {
            idToEnchantMap.put(value.getName(), value);
        }
    }
}
