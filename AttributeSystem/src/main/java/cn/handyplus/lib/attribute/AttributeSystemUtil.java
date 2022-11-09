package cn.handyplus.lib.attribute;

import com.skillw.attsystem.api.AttrAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * AttributeSystem 兼容
 *
 * @author handy
 */
public class AttributeSystemUtil {

    private AttributeSystemUtil() {
    }

    private static final AttributeSystemUtil INSTANCE = new AttributeSystemUtil();

    /**
     * 获取唯一实例
     *
     * @return this
     */
    protected static AttributeSystemUtil getInstance() {
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
        AttrAPI.addAttribute(player.getUniqueId(), plugin.getName(), attributeList, false);
    }

    /**
     * 移除玩家属性
     *
     * @param plugin 插件
     * @param player 玩家
     */
    protected void removeAttribute(Plugin plugin, Player player) {
        AttrAPI.removeAttribute(player.getUniqueId(), plugin.getName());
    }

}