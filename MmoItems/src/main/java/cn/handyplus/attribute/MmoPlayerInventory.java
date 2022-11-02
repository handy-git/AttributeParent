package cn.handyplus.attribute;

import io.lumine.mythic.lib.api.player.EquipmentSlot;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.player.inventory.EquippedItem;
import net.Indyuce.mmoitems.comp.inventory.PlayerInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 集成mmo
 *
 * @author handy
 */
public class MmoPlayerInventory implements PlayerInventory {

    protected static final Map<UUID, List<ItemStack>> MMO_ITEMS_BUFF_MAP = new HashMap<>();

    protected MmoPlayerInventory(Plugin plugin) {
        Bukkit.getScheduler().runTask(plugin, () -> MMOItems.plugin.registerPlayerInventory(this));
    }

    @Override
    public List<EquippedItem> getInventory(Player player) {
        List<EquippedItem> list = new ArrayList<>();
        List<ItemStack> itemStacks = MMO_ITEMS_BUFF_MAP.get(player.getUniqueId());
        if (itemStacks == null || itemStacks.isEmpty()) {
            return list;
        }
        itemStacks.forEach(item -> list.add(new EquippedItem(item, EquipmentSlot.ANY)));
        return list;
    }

}