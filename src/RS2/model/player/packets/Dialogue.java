package RS2.model.player.packets;

import RS2.model.player.Client;
import RS2.model.player.PacketType;


/**
 * Dialogue
 **/
public class Dialogue implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		
		if(c.talkID != -1) {
			c.talkID++;
			c.getDH().sendDialogues(c.npcTalkName);
		} else {
			c.npcTalkName = "MISSINGNPC";
			c.npcTalkID = -1;
			c.getPA().closeAllWindows();
		}
	}
}
