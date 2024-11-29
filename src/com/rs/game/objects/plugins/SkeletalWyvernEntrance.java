package com.rs.game.objects.plugins;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectPlugin;
import com.rs.game.player.Player;

public class SkeletalWyvernEntrance extends ObjectPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] { 33173, 33174 };
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		player.setNextWorldTile(object.getId() == 33173 ? new WorldTile(3056, 9555, 0) : new WorldTile(3056, 9562, 0));
		return true;
	}
}
