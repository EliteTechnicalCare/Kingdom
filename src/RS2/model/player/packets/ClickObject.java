package RS2.model.player.packets;
 
import RS2.model.player.Client;
import RS2.model.player.PacketType;

/**
 * Click Object
 */
public class ClickObject implements PacketType {

	public static final int FIRST_CLICK = 132, SECOND_CLICK = 252,
			THIRD_CLICK = 70;

	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {
		c.clickObjectType = c.objectX = c.objectId = c.objectY = 0;
		c.objectYOffset = c.objectXOffset = 0;
		c.getPA().resetFollow();
		switch (packetType) {

		case FIRST_CLICK:
			c.objectX = c.getInStream().readSignedWordBigEndianA();
			c.objectId = c.getInStream().readUnsignedWord();
			c.objectY = c.getInStream().readUnsignedWordA();
			c.objectDistance = 1;

			if (RS2.Settings.SERVER_DEBUG) {
				c.sendMessage("objectId1: " + c.objectId + " objectX: "
						+ c.objectX + " objectY: " + c.objectY);
			}
			if (Math.abs(c.getX() - c.objectX) > 25
					|| Math.abs(c.getY() - c.objectY) > 25) {
				c.resetWalkingQueue();
				break;
			}
			for (int i = 0; i < c.getWoodcutting().VALID_TREE.length; i++) {
				if(c.objectId == c.getWoodcutting().VALID_TREE[i]){
				c.logType = c.getWoodcutting().determineLogType(c.objectId);
				if(c.getWoodcutting().canWoodcut()) {
					c.getWoodcutting().beginWoodcut();
				}
				}
			}
			switch (c.objectId) {
			
			}
			break;

		case SECOND_CLICK:
			c.objectId = c.getInStream().readUnsignedWordBigEndianA();
			c.objectY = c.getInStream().readSignedWordBigEndian();
			c.objectX = c.getInStream().readUnsignedWordA();
			c.objectDistance = 1;

			if (RS2.Settings.SERVER_DEBUG) {
				c.sendMessage("objectId2: " + c.objectId + " objectX: "
						+ c.objectX + " objectY: " + c.objectY);
			}

			switch (c.objectId) {
			}
			break;

		case THIRD_CLICK:
			c.objectX = c.getInStream().readSignedWordBigEndian();
			c.objectY = c.getInStream().readUnsignedWord();
			c.objectId = c.getInStream().readUnsignedWordBigEndianA();

			if (RS2.Settings.SERVER_DEBUG) {
				c.sendMessage("objectId3: " + c.objectId + " objectX: "
						+ c.objectX + " objectY: " + c.objectY);
			}

			switch (c.objectId) {
			}
		}

	}

	public void handleSpecialCase(Client c, int id, int x, int y) {

	}

}
