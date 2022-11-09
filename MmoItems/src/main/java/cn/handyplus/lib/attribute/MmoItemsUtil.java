package cn.handyplus.lib.attribute;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * MmoItem工具类
 *
 * @author handy
 */
public class MmoItemsUtil {

    private MmoItemsUtil() {
    }

    private static MmoItemsUtil INSTANCE;

    /**
     * 获取唯一实例
     *
     * @return this
     */
    protected static MmoItemsUtil getInstance(Plugin plugin) {
        if (INSTANCE == null) {
            INSTANCE = new MmoItemsUtil();
            new MmoPlayerInventory(plugin);
        }
        return INSTANCE;
    }

    /**
     * 给玩家添加属性
     *
     * @param player        玩家
     * @param attributeList 属性列表
     */
    protected void addAttribute(Player player, List<String> attributeList) {
        List<ItemStack> itemStackList = new ArrayList<>();
        for (String itemStackStr : attributeList) {
            ItemStack itemStack = itemStackDeserialize(itemStackStr);
            itemStackList.add(itemStack);
        }
        MmoPlayerInventory.MMO_ITEMS_BUFF_MAP.put(player.getUniqueId(), itemStackList);
    }

    /**
     * 移除玩家属性
     *
     * @param player 玩家
     */
    protected void removeAttribute(Player player) {
        MmoPlayerInventory.MMO_ITEMS_BUFF_MAP.remove(player.getUniqueId());
    }

    /**
     * 反序列化String为itemStack
     *
     * @param str 物品str
     * @return ItemStack
     */
    private static ItemStack itemStackDeserialize(String str) {
        YamlConfiguration yml = new YamlConfiguration();
        ItemStack item;
        try {
            yml.loadFromString(str);
            item = yml.getItemStack("item");
        } catch (InvalidConfigurationException ex) {
            item = new ItemStack(Material.AIR, 1);
        }
        return item;
    }

}