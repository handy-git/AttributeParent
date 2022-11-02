package cn.handyplus.attribute;

import github.saukiya.sxattribute.SXAttribute;
import github.saukiya.sxattribute.data.attribute.SXAttributeData;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * SxAttribute V3 版本兼容
 *
 * @author handy
 */
public class SxAttributeUtil {

    private SxAttributeUtil() {
    }

    private static final SxAttributeUtil INSTANCE = new SxAttributeUtil();

    /**
     * 获取唯一实例
     *
     * @return this
     */
    protected static SxAttributeUtil getInstance() {
        return INSTANCE;
    }

    /**
     * 给玩家添加属性
     *
     * @param plugin        插件
     * @param player        玩家
     * @param attributeList 属性列表
     */
    protected void addAttribute(Plugin plugin, Player player, List<String> attributeList) {
        SXAttributeData data = SXAttribute.getApi().loadListData(attributeList);
        SXAttribute.getApi().setEntityAPIData(plugin.getClass(), player.getUniqueId(), data);
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