package scripts.actions;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Objects;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.util.DPathNavigator;

import scripts.Globals;
import scripts.sbf.action.Action;
import scripts.sbf.skill.SkillGlobals;

public class WalkToBank extends Action {
	private final Filter<RSObject> bankFilter = Filters.Objects
			.actionsContains("Bank").combine(new Filter<RSObject>() {

				@Override
				public boolean accept(RSObject bank) {

					return bank != null && bank.isOnScreen();
				}

			}, true);
	private final DPathNavigator pathNav = new DPathNavigator();
	private final RSTile bankTile = skillManager.getBankTile();

	@Override
	public void execute() {
		print("Walking to the bank");
		if (Objects.find(10, bankFilter).length < 1) {
			if (pathNav.traverse(bankTile))
				SkillGlobals.ARRIVED_AT_DEPOSITORY.setStatus(Timing
						.waitCondition(new Condition() {

							@Override
							public boolean active() {
								General.sleep(100, 200);
								return Objects.find(10, bankFilter).length > 0;
							}
						}, General.random(4000, 5000)));
		} else {
			SkillGlobals.ARRIVED_AT_DEPOSITORY.setStatus(true);
		}

	}

	@Override
	public boolean isValid() {
		return Globals.WALK_BANK.getStatus();
	}

}