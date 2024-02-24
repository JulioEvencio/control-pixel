package controlpixel.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import controlpixel.Game;
import controlpixel.gui.Button;
import controlpixel.gui.ButtonSmall;
import controlpixel.gui.Text;
import controlpixel.strings.StringLevel;
import controlpixel.util.GameStatus;
import controlpixel.util.Spritesheet;
import controlpixel.util.Util;

public abstract class Screen {

	private final Game game;

	private static final BufferedImage background;

	private final String title;

	protected final List<Text> texts;
	protected final List<Button> buttons;
	protected final List<ButtonSmall> buttonSmalls;

	private boolean mousePressed;
	private boolean mouseReleased;

	private int mouseX;
	private int mouseY;

	static {
		background = Spritesheet.getSpriteGUI(97, 37, 24, 22);
	}

	public Screen(Game game, String title) {
		this.game = game;

		this.title = title;

		this.texts = new ArrayList<>();
		this.buttons = new ArrayList<>();
		this.buttonSmalls = new ArrayList<>();

		this.mousePressed = false;
		this.mouseReleased = false;

		this.mouseX = 0;
		this.mouseY = 0;

		this.texts.add(new Text(String.format("v %s", this.game.getVERSION()), 50, 50, Color.WHITE));
	}

	public abstract GameStatus getGameStatus();

	private void updateButtonEnable() {
		for (ButtonSmall button : this.buttonSmalls) {
			if (button.getText().equals(StringLevel.LEVEL_02.getValue())) {
				button.setEnabled(Util.getSave().isLevel02());
			} else if (button.getText().equals(StringLevel.LEVEL_03.getValue())) {
				button.setEnabled(Util.getSave().isLevel03());
			} else if (button.getText().equals(StringLevel.LEVEL_04.getValue())) {
				button.setEnabled(Util.getSave().isLevel04());
			} else if (button.getText().equals(StringLevel.LEVEL_05.getValue())) {
				button.setEnabled(Util.getSave().isLevel05());
			} else if (button.getText().equals(StringLevel.LEVEL_06.getValue())) {
				button.setEnabled(Util.getSave().isLevel06());
			} else if (button.getText().equals(StringLevel.LEVEL_07.getValue())) {
				button.setEnabled(Util.getSave().isLevel07());
			} else if (button.getText().equals(StringLevel.LEVEL_08.getValue())) {
				button.setEnabled(Util.getSave().isLevel08());
			} else if (button.getText().equals(StringLevel.LEVEL_09.getValue())) {
				button.setEnabled(Util.getSave().isLevel09());
			} else if (button.getText().equals(StringLevel.LEVEL_10.getValue())) {
				button.setEnabled(Util.getSave().isLevel10());
			} else if (button.getText().equals(StringLevel.LEVEL_11.getValue())) {
				button.setEnabled(Util.getSave().isLevel11());
			} else if (button.getText().equals(StringLevel.LEVEL_12.getValue())) {
				button.setEnabled(Util.getSave().isLevel12());
			}
		}
	}

	public void tick() {
		this.updateButtonEnable();

		if (this.mousePressed) {
			for (Button button : this.buttons) {
				if (button.wasClicked(this.mouseX, this.mouseY)) {
					button.setButtonPressed();
				}
			}

			for (ButtonSmall button : this.buttonSmalls) {
				if (button.wasClicked(this.mouseX, this.mouseY)) {
					button.setButtonPressed();
				}
			}

			this.mousePressed = false;
		}

		if (this.mouseReleased) {
			for (Button button : this.buttons) {
				if (button.wasClicked(this.mouseX, this.mouseY)) {
					button.onClick();
				}

				button.setButtonReleased();
			}

			for (ButtonSmall button : this.buttonSmalls) {
				if (button.wasClicked(this.mouseX, this.mouseY)) {
					button.onClick();
				}

				button.setButtonReleased();
			}

			this.mouseReleased = false;
		}
	}

	public void render(Graphics render) {
		render.drawImage(Screen.background, 0, 0, this.game.getGameWidth(), this.game.getGameHeight(), null);

		render.setColor(Color.WHITE);
		render.setFont(Util.getFontTitle());

		int titleWidth = render.getFontMetrics().stringWidth(this.title);

		render.drawString(this.title, (game.getGameWidth() - titleWidth) / 2, 80);

		for (Text text : this.texts) {
			text.render(render);
		}

		for (Button button : this.buttons) {
			button.render(render);
		}

		for (ButtonSmall button : this.buttonSmalls) {
			button.render(render);
		}
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.mousePressed = true;

			this.mouseX = e.getX();
			this.mouseY = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.mouseReleased = true;

			this.mouseX = e.getX();
			this.mouseY = e.getY();
		}
	}

}
