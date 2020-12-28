package com.feed_the_beast.mods.ftbultimine.net;

import com.feed_the_beast.mods.ftbultimine.FTBUltimine;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author LatvianModder
 */
public class ModeChangedPacket
{
	public final boolean next;

	public ModeChangedPacket(boolean n)
	{
		next = n;
	}

	public ModeChangedPacket(FriendlyByteBuf buf)
	{
		next = buf.readBoolean();
	}

	public void write(FriendlyByteBuf buf)
	{
		buf.writeBoolean(next);
	}

	public void handle(Supplier<NetworkEvent.Context> context)
	{
		context.get().enqueueWork(() -> FTBUltimine.instance.modeChanged(context.get().getSender(), next));
		context.get().setPacketHandled(true);
	}
}