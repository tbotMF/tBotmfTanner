package scripts;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Camera;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

import scripts.sbf.manager.Manager;
import scripts.sbf.util.ABC;
@ScriptManifest(authors = { "modulusfrank12" }, category = "Money Making", name = "mfTanner")
public class MFTanner extends Script implements Painting {
	private final Manager manager = Manager.getInstance();
	private long startTime;
	private Font paintFont = new Font("Miriam Fixed", Font.PLAIN, 14);

	@Override
	public void run() {
		println("Thank you very much for choosing this script, "
				+ General.getTRiBotUsername() + "!");
		manager.loadABC(new ABC());
		manager.loadScript(this);
		Camera.setCameraAngle(100);
		setMouseSpeed();
		start();
		final MFTannerGUI tannerGUI = new MFTannerGUI();
		tannerGUI.setTitle("mfTanner v1.0");
		tannerGUI.setVisible(true);
		startTime = Timing.currentTimeMillis();
		manager.initialize(tannerGUI, 15, false);
	}

	private void setMouseSpeed() {
		manager.setMouseSpeed(110);
		Mouse.setSpeed(110);
	}

	public void onPaint(Graphics gr) {
		Graphics2D g2d = (Graphics2D) gr;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);
		String runtime = Timing.msToString(System.currentTimeMillis()
				- startTime);
		g2d.setColor(Color.WHITE);
		g2d.setFont(paintFont);

	
		g2d.drawString("Runtime : " + runtime, 10, 80);
		g2d.drawString("mfTanner v1.0", 10, 110);
		g2d.setColor(Color.gray);
		g2d.drawRect(0, 60, 240, 27);
		g2d.drawRect(0, 90, 150, 27);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.5f));
		g2d.setColor(Color.orange);
		g2d.fillRect(0, 58, 240, 27);
		g2d.setColor(Color.green);
		g2d.fillRect(0, 88, 150, 27);
	}

}
