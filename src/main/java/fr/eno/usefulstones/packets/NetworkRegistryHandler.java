package fr.eno.usefulstones.packets;

import java.util.Optional;

import fr.eno.usefulstones.References;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkRegistryHandler
{
	private static final String PROTOCOL_VERSION = Integer.toString(1);
	
	public static final SimpleChannel network = NetworkRegistry.ChannelBuilder
			.named(References.getLoc("main_channel"))
			.clientAcceptedVersions(PROTOCOL_VERSION::equals)
			.serverAcceptedVersions(PROTOCOL_VERSION::equals)
			.networkProtocolVersion(() -> PROTOCOL_VERSION)
			.simpleChannel();
		
	public static void registerMessages()
	{		
		int id = 0;	    
		network.registerMessage(id++, SyncTileEntityDataPacket.class, SyncTileEntityDataPacket::encode, SyncTileEntityDataPacket::decode, SyncTileEntityDataPacket.ClientHandler::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
	}
}
