package RS2.model.player;

import RS2.Settings;
import RS2.model.npc.NPCHandler;

public class ActionsPerformed {

	private Client c;

	public ActionsPerformed(Client Client) {
		this.c = Client;
	}

	public void firstClickObject(int objectType, int obX, int obY) {
		c.clickObjectType = 0;
		c.actionTimer = 4;
		if (c.actionTimer > 0) {
			return;
		}
		c.actionTimer = 4;
		switch (objectType) {
		}
	}

	public void secondClickObject(int objectType, int obX, int obY) {
		c.clickObjectType = 0;
		switch (objectType) {
		}
	}

	public void thirdClickObject(int objectType, int obX, int obY) {
		c.clickObjectType = 0;
		c.sendMessage("Object type: " + objectType);
		switch (objectType) {
		}
	}

	public void firstClickNpc(int npcType) {
		c.clickNpcType = 0;
		c.npcClickIndex = 0;
		if(Settings.SERVER_DEBUG) {
		c.sendMessage("[STONE-DEBUG]: Clicked on NPC ID:"+npcType+" Name: "+NPCHandler.getNpcName(npcType));	
		}
		switch (npcType) {
		default:
		c.npcTalkName = NPCHandler.getNpcName(npcType).replace("_", " ");
		c.sendMessage(c.npcTalkName);
		c.talkID = 0;
		c.npcTalkID = npcType;
		c.getDH().sendDialogues(c.npcTalkName);
			break;
		}
	}

	public void secondClickNpc(int npcType) {
		c.clickNpcType = 0;
		c.npcClickIndex = 0;		
		if(Settings.SERVER_DEBUG) {
			c.sendMessage("[STONE-DEBUG]: 2nd Clicked on NPC ID:"+npcType+" Name: "+NPCHandler.getNpcName(npcType));	
			}
		String name = NPCHandler.getNpcName(npcType).replace("_", "");
		switch (name) {
		case "Seddu":
			c.getShops().openShop(0);
			break;
		case "Kazemde":
			c.getShops().openShop(1);
			break;
			
		}
	}

	public void thirdClickNpc(int npcType) {
		c.clickNpcType = 0;
		c.npcClickIndex = 0;
		if(Settings.SERVER_DEBUG) {
			c.sendMessage("[STONE-DEBUG]: 3rd Clicked on NPC ID:"+npcType+" Name: "+NPCHandler.getNpcName(npcType));	
			}
		switch (npcType) {
		}
	}
}