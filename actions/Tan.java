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
	@Override
	public void execute() {
		print("Tanning");
		RSNPC[] tanner = NPCs.find("Ellis","Sbott");
		if (tanner.length < 1)
			return;
		if (tanner[0] == null)
			return;
		if (!DynamicClicking.clickRSNPC(tanner[0], "Trade"))
			return;
		if (!Timing.waitCondition(new Condition() {

			@Override
			public boolean active() {
				General.sleep(100, 200);
				return Interfaces.get(skillManager.getMasterIndex()) != null;
			}
		}, General.random(5000, 6000)))
			return;
		RSInterface productInterface = Interfaces.get(
				skillManager.getMasterIndex(), skillManager.getChildIndex());
		if (productInterface == null)
			return;
		if (!productInterface.click("Tan All"))
			return;
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