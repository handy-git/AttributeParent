package cn.handyplus.lib.attribute;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.serverct.ersha.jd.AttributeAPI;

import java.util.List;

/**
 * AttributePlus 兼容
 *
 * @author handy
 */
public class AttributePlusV2Util {

    private AttributePlusV2Util() {
    }

    private static final AttributePlusV2Util INSTANCE = new AttributePlusV2Util();

    /**
     * 获取唯一实例
     *
     * @return this
     */
    protected static AttributePlusV2Util getInstance() {
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
        AttributeAPI.addAttribute(player, plugin.getName(), attributeList, false);
    }

    /**
     * 移除玩家属性
     *
     * @param plugin 插件
     * @param player 玩家
     */
    protected void removeAttribute(Plugin plugin, Player player) {
        AttributeAPI.deleteAttribute(player, plugin.getName());
    }

}