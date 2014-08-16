package scripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.tribot.api2007.types.RSTile;

import scripts.actions.Deposit;
import scripts.actions.OpenBank;
import scripts.actions.Tan;
import scripts.actions.WalkToBank;
import scripts.actions.WalkToTanner;
import scripts.actions.Withdraw;
import scripts.sbf.graphics.AbstractGUI;
import scripts.sbf.state.State;
import scripts.states.Banking;
import scripts.states.Tanning;

public class MFTannerGUI extends AbstractGUI {

    /**
     * Creates new form MFTannerGUI
     */
    public MFTannerGUI() {
        initComponents();
    }
   
	@Override
	public List<State> createStateAssociations() {
		List<State> states = new ArrayList<State>();
		State banking = new Banking();
		State tanning = new Tanning();

		banking.addActions(new WalkToBank(), new OpenBank(), new Deposit(),
				new Withdraw());
		tanning.addActions(new WalkToTanner(), new Tan());
		Collections.addAll(states, banking, tanning);
		return states;
	}

	@Override
	public void processUserSelections() {
		processLocation();
		processTan();
	}

	private void processTan() {
		selections.put("product", (String) productComboBox.getSelectedItem());
		skillManager.loadMasterIndex(324);
		switch (selections.get("product")) {
		case "Leather":
			skillManager.loadChildIndex(100);
			skillManager.loadInventoryResources("Cowhide");
			break;
		case "Hard leather":
			skillManager.loadChildIndex(101);
			skillManager.loadInventoryResources("Cowhide");
			break;
		case "Green dragon leather":
			skillManager.loadChildIndex(104);
			skillManager.loadInventoryResources("Green dragonhide");
			break;

		case "Blue dragon leather":
			skillManager.loadChildIndex(105);
			skillManager.loadInventoryResources("Blue dragonhide");
			break;

		case "Red dragon leather":
			skillManager.loadChildIndex(106);
			skillManager.loadInventoryResources("Red dragonhide");
			break;
		case "Black dragon leather":
			skillManager.loadChildIndex(107);
			skillManager.loadInventoryResources("Black dragonhide");
			break;
		}

	}

	private void processLocation() {
		selections.put("Location", (String) locationComboBox.getSelectedItem());
		switch (selections.get("Location")) {
		case "Al Kharid":
			skillManager.loadSkillTile(new RSTile(3275, 3191));
			skillManager.loadBankTile(new RSTile(3270,3166));
			break;
		case "Canifis":
			skillManager.loadSkillTile(new RSTile(3490, 3500));
			skillManager.loadBankTile(new RSTile(3511, 3479));
			break;
		}
	}

}
