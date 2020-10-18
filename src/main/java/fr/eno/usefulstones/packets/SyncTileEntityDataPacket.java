package fr.eno.usefulstones.packets;

import java.util.function.Supplier;

import fr.eno.usefulstones.containers.BluestoneInfuserContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class SyncTileEntityDataPacket
{
	private int timeLeft;
	public int windowId;
	
	public SyncTileEntityDataPacket(){}
	
	public SyncTileEntityDataPacket(int windowId, int timeLeft)
	{
		this.timeLeft = timeLeft;
		this.windowId = windowId;
	}
	
	public static void encode(SyncTileEntityDataPacket msg, PacketBuffer buf)
	{
		buf.writeInt(msg.windowId);
		buf.writeInt(msg.timeLeft);
	}
	
	public static SyncTileEntityDataPacket decode(PacketBuffer buf)
	{
		return new SyncTileEntityDataPacket(buf.readInt(), buf.readInt());
	}
	
	public static class ClientHandler
	{
		@SuppressWarnings("resource")
		public static void handle(SyncTileEntityDataPacket msg, Supplier<NetworkEvent.Context> ctx)
		{
			PlayerEntity player = Minecraft.getInstance().player;
			
			if(player.openContainer.windowId == msg.windowId)
			{
				BluestoneInfuserContainer container = (BluestoneInfuserContainer) player.openContainer;
				container.setTimeLeft(msg.timeLeft);
				
				ctx.get().setPacketHandled(true);
			}
		}
	}
}
