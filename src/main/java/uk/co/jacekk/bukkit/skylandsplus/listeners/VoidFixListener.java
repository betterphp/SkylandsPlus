package uk.co.jacekk.bukkit.skylandsplus.listeners;

import net.minecraft.server.v1_4_R1.Packet1Login;
import net.minecraft.server.v1_4_R1.WorldType;
import uk.co.jacekk.bukkit.skylandsplus.SkylandsPlus;
import uk.co.jacekk.bukkit.skylandsplus.generation.ChunkGenerator;

import com.comphenix.protocol.Packets;
import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.injector.GamePhase;

public class VoidFixListener extends PacketAdapter {
	
	public VoidFixListener(SkylandsPlus plugin){
		super(plugin, ConnectionSide.SERVER_SIDE, ListenerPriority.NORMAL, GamePhase.LOGIN, Packets.Server.LOGIN);
	}
	
	@Override
	public void onPacketSending(PacketEvent event){
		if (event.getPlayer().getWorld().getGenerator() instanceof ChunkGenerator){
			((Packet1Login) event.getPacket().getHandle()).b = WorldType.FLAT;
		}
	}
	
}
