package scripts.actions;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;

import scripts.Globals;
import scripts.sbf.action.Action;

public class Withdraw extends Action {
	private final String[] resources = skillManager.getInventoryResources();

	@Override
	public void execute() {
		print("Withdrawing");
		if (Inventory.find("Coins").length < 1) {
			for (int i = 0; i < 50; i++) {
				if (Banking.find("Coins").length > 0)
					if (Banking.withdraw(0, "Coins")) {
						if (Timing.waitCondition(new Condition() {

							@Override
							public boolean active() {
								General.sleep(100, 200);
								return Inventory.find("Coins").length > 0;
							}

						}, General.random(3000, 3500)))
							i = 50;
					}
				if (i == 49) {
					print("Didn't find any money.");
					print("Exiting script");
					manager.loadTerminationCondition(true);
					General.sleep(2000);
					return;
				}
			}
		}

		if (Inventory.find(resources).length < 1) {
			for (int i = 0; i < 50; i++) {
				if (Banking.find(resources).length > 0)
					if (Banking.withdraw(0, resources)) {
						if (Timing.waitCondition(new Condition() {

							@Override
							public boolean active() {
								General.sleep(100, 200);
								return Inventory.find(resources).length > 0;
							}

						}, General.random(3000, 3500)))
							i = 50;
					}
				if (i == 49) {
					print("Didn't find any " + resources[0].toLowerCase() + "s.");
					print("Exiting script");
					manager.loadTerminationCondition(true);
					General.sleep(2000);
				}
			}
		}

	}

	@Override
	public boolean isValid() {
		return Globals.WITHDRAW.getStatus();
	}

}
