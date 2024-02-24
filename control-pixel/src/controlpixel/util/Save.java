package controlpixel.util;

import java.io.Serializable;

public class Save implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean level01;
	private boolean level02;
	private boolean level03;
	private boolean level04;
	private boolean level05;
	private boolean level06;
	private boolean level07;
	private boolean level08;
	private boolean level09;
	private boolean level10;
	private boolean level11;
	private boolean level12;

	public Save() {
		this.level01 = true;
		this.level02 = false;
		this.level03 = false;
		this.level04 = false;
		this.level05 = false;
		this.level06 = false;
		this.level07 = false;
		this.level08 = false;
		this.level09 = false;
		this.level10 = false;
		this.level11 = false;
		this.level12 = false;
	}

	public boolean isLevel01() {
		return level01;
	}

	public void setLevel01(boolean level01) {
		this.level01 = level01;
	}

	public boolean isLevel02() {
		return level02;
	}

	public void setLevel02(boolean level02) {
		this.level02 = level02;
	}

	public boolean isLevel03() {
		return level03;
	}

	public void setLevel03(boolean level03) {
		this.level03 = level03;
	}

	public boolean isLevel04() {
		return level04;
	}

	public void setLevel04(boolean level04) {
		this.level04 = level04;
	}

	public boolean isLevel05() {
		return level05;
	}

	public void setLevel05(boolean level05) {
		this.level05 = level05;
	}

	public boolean isLevel06() {
		return level06;
	}

	public void setLevel06(boolean level06) {
		this.level06 = level06;
	}

	public boolean isLevel07() {
		return level07;
	}

	public void setLevel07(boolean level07) {
		this.level07 = level07;
	}

	public boolean isLevel08() {
		return level08;
	}

	public void setLevel08(boolean level08) {
		this.level08 = level08;
	}

	public boolean isLevel09() {
		return level09;
	}

	public void setLevel09(boolean level09) {
		this.level09 = level09;
	}

	public boolean isLevel10() {
		return level10;
	}

	public void setLevel10(boolean level10) {
		this.level10 = level10;
	}

	public boolean isLevel11() {
		return level11;
	}

	public void setLevel11(boolean level11) {
		this.level11 = level11;
	}

	public boolean isLevel12() {
		return level12;
	}

	public void setLevel12(boolean level12) {
		this.level12 = level12;
	}

}
