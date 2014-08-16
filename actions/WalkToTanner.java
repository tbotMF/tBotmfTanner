package scripts.actions;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.util.DPathNavigator;

import scripts.Globals;
import scripts.sbf.action.Action;
import scripts.sbf.util.MFUtil;

public class WalkToTanner extends Action {
	private final RSTile tannerTile = skillManager.getSkillTile();
	private final DPathNavigator pathNav = new DPathNavigator();

	@Override
	public void execute() {
		print("Walking to tanner");
		if (pathNav.traverse(tannerTile))
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					General.sleep(100, 200);
					return MFUtil.isOnTile(tannerTile);
				}

			}, General.random(5000, 6000));
	}

	@Override
	public boolean isValid() {
		return Globals.WALK_TANNER.getStatus();
	}

}