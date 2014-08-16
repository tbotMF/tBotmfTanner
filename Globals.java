package scripts;

import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.ext.Filters;

import scripts.sbf.graphics.UserSelections;
import scripts.sbf.skill.SkillGlobals;
import scripts.sbf.skill.SkillManager;
import scripts.sbf.util.MFUtil;

public enum Globals {
	DEPOSIT {

		@Override
		public boolean getStatus() {
			return Banking.isBankScreenOpen() && DEPOSIT_NEEDS.getStatus();
		}

	},
	OPEN_BANK {

		@Override
		public boolean getStatus() {
			return !Banking.isBankScreenOpen() && NEEDS_BANK.getStatus()
					&& SkillGlobals.ARRIVED_AT_DEPOSITORY.getUpdatedStatus();
		}

	},
	TAN {

		@Override
		public boolean getStatus() {
			return MFUtil.isOnTile(SkillManager.getInstance().getSkillTile())
					&& !NEEDS_BANK.getStatus();
		}

	},
	WALK_BANK {

		@Override
		public boolean getStatus() {
			return !Banking.isBankScreenOpen() && NEEDS_BANK.getStatus()
					&& !SkillGlobals.ARRIVED_AT_DEPOSITORY.getUpdatedStatus();
		}
	},
	WALK_TANNER {

		@Override
		public boolean getStatus() {
			return !MFUtil.isOnTile(SkillManager.getInstance().getSkillTile())
					&& !NEEDS_BANK.getStatus();
		}

	},
	WITHDRAW {

		@Override
		public boolean getStatus() {
			return Banking.isBankScreenOpen() && WITHDRAW_NEEDS.getStatus();
		}

	},
	DEPOSIT_NEEDS {

		@Override
		public boolean getStatus() {
			return Inventory.find(UserSelections.getInstance().get("product")).length > 0
					|| !MFUtil.inPossessionOfOnly(SkillManager.getInstance()
							.getInventoryResources()[0], "Coins");
		}

	},
	WITHDRAW_NEEDS {

		@Override
		public boolean getStatus() {
			return Inventory.find("Coins").length < 1
					|| Inventory.find(SkillManager.getInstance()
							.getInventoryResources()).length < 1;
		}

	},
	NEEDS_BANK {

		@Override
		public boolean getStatus() {
			return WITHDRAW_NEEDS.getStatus() || DEPOSIT_NEEDS.getStatus();
		}

	};
	public abstract boolean getStatus();

}
