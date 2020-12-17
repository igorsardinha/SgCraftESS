package br.com.sgcraft;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class TitleAPI {
	
	
	public static void sendTitle(Player p, String title, String subtitle) {
		PacketPlayOutTitle titulo = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":" + "\"" + title + "\"}"), 5, 5, 5);
		PacketPlayOutTitle subtitulo = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":" + "\"" + subtitle + "\"}"), 5, 5, 5);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(titulo);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitulo);
	}
	
    public void sendActionBar(Player p, String msg){
        PacketPlayOutChat action = new PacketPlayOutChat(new ChatComponentText(msg), (byte)2);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(action);
    }

}
