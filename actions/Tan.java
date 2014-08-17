package scripts.actions;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSNPC;

import scripts.Globals;
import scripts.sbf.action.Action;
import scripts.sbf.skill.SkillGlobals;

public class Tan extends Action {

	private boolean findTanner() {
		RSNPC[] tanner = NPCs.find("Ellis", "Sbott");
		return tanner.length < 1 && tanner[0] != null
				&& DynamicClicking.clickRSNPC(tanner[0], "Trade");
	}

	private boolean tanAll() {
		if (!Timing.waitCondition(new Condition() {

			@Override
			public boolean active() {
				General.sleep(100, 200);
				return Interfaces.get(skillManager.getMasterIndex()) != null;
			}
		}, General.random(5000, 6000)))
			return false;
		RSInterface productInterface = Interfaces.get(
				skillManager.getMasterIndex(), skillManager.getChildIndex());
		return productInterface != null && !productInterface.click("Tan All");
	}

	@Override
	public void execute() {
		print("Tanning");

		if (findTanner() && tanAll())
			if (!Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					General.sleep(10, 20);
					return Interfaces.get(skillManager.getMasterIndex()) == null;
				}

			}, General.random(2000, 3000)))
				return;
		SkillGlobals.ARRIVED_AT_DEPOSITORY.setStatus(false);

	}

	@Override
	public boolean isValid() {
		return Globals.TAN.getStatus();
	}

}
