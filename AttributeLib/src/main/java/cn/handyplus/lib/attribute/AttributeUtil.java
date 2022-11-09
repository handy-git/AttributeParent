package cn.handyplus.lib.attribute;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性工具
 *
 * @author handy
 */
public class AttributeUtil {

    private static Plugin PLUGIN;
    private static boolean USE_AP;
    private static boolean USE_AS;
    private static boolean USE_SX_3;
    private static boolean USE_SX_2;
    private static boolean USE_MMO;

    private AttributeUtil() {
    }

    private static final AttributeUtil INSTANCE = new AttributeUtil();

    /**
     * 获取唯一实例
     *
     * @return this
     */
    public static AttributeUtil getInstance(Plugin plugin) {
        PLUGIN = plugin;
        init();
        return INSTANCE;
    }

    /**
     * 给玩家添加属性
     *
     * @param player        玩家
     * @param attributeList 属性
     * @param attributeEnum 属性类型
     */
    public void addAttribute(Player player, List<String> attributeList, AttributeEnum attributeEnum) {
        if (attributeList == null || attributeList.isEmpty()) {
            return;
        }
        if (player == null) {
            throw new RuntimeException("参数异常: player不能为null");
        }
        attributeList = replaceChatColor(attributeList);
        switch (attributeEnum) {
            case ALL:
                if (USE_AP) {
                    AttributePlusUtil.getInstance().addAttribute(PLUGIN, player, attributeList);
                }
                if (USE_AS) {
                    AttributeSystemUtil.getInstance().addAttribute(PLUGIN, player, attributeList);
                }
                if (USE_SX_3) {
                    SxAttributeUtil.getInstance().addAttribute(PLUGIN, player, attributeList);
                }
                if (USE_SX_2) {
                    SxAttributeV2Util.getInstance().addAttribute(PLUGIN, player, attributeList);
                }
                if (USE_MMO) {
                    MmoItemsUtil.getInstance(PLUGIN).addAttribute(player, attributeList);
                }
                break;
            case ATTRIBUTE_PLUS:
                if (USE_AP) {
                    AttributePlusUtil.getInstance().addAttribute(PLUGIN, player, attributeList);
                }
                break;
            case ATTRIBUTE_SYSTEM:
                if (USE_AS) {
                    AttributeSystemUtil.getInstance().addAttribute(PLUGIN, player, attributeList);
                }
                break;
            case SX_ATTRIBUTE:
                if (USE_SX_3) {
                    SxAttributeUtil.getInstance().addAttribute(PLUGIN, player, attributeList);
                }
                if (USE_SX_2) {
                    SxAttributeV2Util.getInstance().addAttribute(PLUGIN, player, attributeList);
                }
                break;
            case MMO_ITEMS:
                if (USE_MMO) {
                    MmoItemsUtil.getInstance(PLUGIN).addAttribute(player, attributeList);
                }
                break;
            default:
                throw new RuntimeException("参数异常: attributeEnum 错误");
        }
    }

    /**
     * 移除玩家属性
     *
     * @param player        玩家
     * @param attributeEnum 属性类型
     */
    public void removeAttribute(Player player, AttributeEnum attributeEnum) {
        switch (attributeEnum) {
            case ALL:
                if (USE_AP) {
                    AttributePlusUtil.getInstance().removeAttribute(PLUGIN, player);
                }
                if (USE_AS) {
                    AttributeSystemUtil.getInstance().removeAttribute(PLUGIN, player);
                }
                if (USE_SX_3) {
                    SxAttributeUtil.getInstance().removeAttribute(PLUGIN, player);
                }
                if (USE_SX_2) {
                    SxAttributeV2Util.getInstance().removeAttribute(PLUGIN, player);
                }
                if (USE_MMO) {
                    MmoItemsUtil.getInstance(PLUGIN).removeAttribute(player);
                }
                break;
            case ATTRIBUTE_PLUS:
                if (USE_AP) {
                    AttributePlusUtil.getInstance().removeAttribute(PLUGIN, player);
                }
                break;
            case ATTRIBUTE_SYSTEM:
                if (USE_AS) {
                    AttributeSystemUtil.getInstance().removeAttribute(PLUGIN, player);
                }
                break;
            case SX_ATTRIBUTE:
                if (USE_SX_3) {
                    SxAttributeUtil.getInstance().removeAttribute(PLUGIN, player);
                }
                if (USE_SX_2) {
                    SxAttributeV2Util.getInstance().removeAttribute(PLUGIN, player);
                }
                break;
            case MMO_ITEMS:
                if (USE_MMO) {
                    MmoItemsUtil.getInstance(PLUGIN).removeAttribute(player);
                }
                break;
            default:
                throw new RuntimeException("参数异常: attributeEnum 错误");
        }
    }

    /**
     * 初始化版本
     */
    private static void init() {
        USE_AP = Bukkit.getPluginManager().getPlugin("AttributePlus") != null;
        USE_AS = Bukkit.getPluginManager().getPlugin("AttributeSystem") != null;
        // 加载sx
        Plugin sxPlugin = Bukkit.getPluginManager().getPlugin("SX-Attribute");
        if (sxPlugin != null) {
            int firstPluginVersion = getFirstPluginVersion(sxPlugin);
            if (firstPluginVersion < 3) {
                USE_SX_2 = true;
            } else {
                USE_SX_3 = true;
            }
        }
        // 加载mmo属性
        USE_MMO = Bukkit.getPluginManager().getPlugin("MMOItems") != null;
        if (USE_MMO) {
            MmoItemsUtil.getInstance(PLUGIN);
        }
    }

    /**
     * 获取版本第一位
     *
     * @param plugin 插件
     * @return 版本第一位
     */
    private static int getFirstPluginVersion(Plugin plugin) {
        String version = plugin.getDescription().getVersion();
        String[] split = version.split("\\.");
        return Integer.parseInt(split[0]);
    }

    /**
     * 颜色代码转换
     *
     * @param strList 消息
     * @return 转换后的字符串
     */
    private static List<String> replaceChatColor(List<String> strList) {
        List<String> list = new ArrayList<>();
        if (strList == null || strList.isEmpty()) {
            return list;
        }
        for (String str : strList) {
            list.add(str.replace("&", "§"));
        }
        return list;
    }

}