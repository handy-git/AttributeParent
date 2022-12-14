package cn.handyplus.lib.attribute;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.serverct.ersha.api.AttributeAPI;

import java.util.List;

/**
 * AttributePlus 兼容
 *
 * @author handy
 */
public class AttributePlusUtil {

    private AttributePlusUtil() {
    }

    private static final AttributePlusUtil INSTANCE = new AttributePlusUtil();

    /**
     * 获取唯一实例
     *
     * @return this
     */
    protected static AttributePlusUtil getInstance() {
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
        AttributeAPI.addSourceAttribute(AttributeAPI.getAttrData(player), plugin.getName(), attributeList);
    }

    /**
     * 移除玩家属性
     *
     * @param plugin 插件
     * @param player 玩家
     */
    protected void removeAttribute(Plugin plugin, Player player) {
        AttributeAPI.takeSourceAttribute(AttributeAPI.getAttrData(player), plugin.getName());
    }

}