package com.rs.game.npc.combat;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.npc.NPC;

public class Default extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Default" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int attackStyle = defs.getAttackStyle();
		if (attackStyle == NPCCombatDefinitions.MELEE) {
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), attackStyle, target)));
		} else {
			int damage = getRandomMaxHit(npc, defs.getMaxHit(), attackStyle, target);
			delayHit(npc, 2, target,
					attackStyle == NPCCombatDefinitions.RANGE ? getRangeHit(npc, damage) : getMagicHit(npc, damage));
			if (defs.getAttackProjectile() != -1) {
				if (attackStyle == NPCCombatDefinitions.RANGE)
					World.sendFastBowProjectile(npc, target, defs.getAttackProjectile());
				else
					World.sendElemantalProjectile(npc, target, defs.getAttackProjectile());
			}
		}
		if (defs.getAttackGfx() != -1)
			npc.gfx(new Graphics(defs.getAttackGfx()));
		npc.animate(new Animation(defs.getAttackEmote()));
		return defs.getAttackDelay();
	}
}
