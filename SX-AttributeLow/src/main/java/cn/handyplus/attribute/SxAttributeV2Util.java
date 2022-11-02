package cn.handyplus.attribute;

import github.saukiya.sxattribute.SXAttribute;
import github.saukiya.sxattribute.data.attribute.SXAttributeData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * SxAttribute V2 版本兼容
 *
 * @author handy
 */
public class SxAttributeV2Util {

    private SxAttributeV2Util() {
    }

    private static final SxAttributeV2Util INSTANCE = new SxAttributeV2Util();

    /**
     * 获取唯一实例
     *
     * @return this
     */
    protected static SxAttributeV2Util getInstance() {
        return INSTANCE;
    }

    /**
     * 给玩家添加属性
     *
     * @param plugin        插件
     * @param player        玩家
     * @param attributeList 属性
     */
    protected void addAttribute(Plugin plugin, Player player, List<String> attributeList) {
        ItemStack itemStack = new ItemStack(Material.NAME_TAG);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(plugin.getName());
        itemMeta.setLore(attributeList);
        itemStack.setItemMeta(itemMeta);
        SXAttributeData sxAttributeData = new SXAttributeData();
        sxAttributeData.add(SXAttribute.getApi().getItemData(null, null, itemStack));
        SXAttribute.getApi().setEntityAPIData(plugin.getClass(), player.getUniqueId(), sxAttributeData);
        SXAttribute.getApi().updateStats(player);
    }

    /**
     * 移除玩家属性
     *
     * @param plugin 插件
     * @param player 玩家
     */
    protected void removeAttribute(Plugin plugin, Player player) {
        SXAttribute.getApi().removeEntityAPIData(plugin.getClass(), player.getUniqueId());
    }

}