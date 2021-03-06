package com.wallops.java.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.wallops.java.gui.GuiGameScreen;
import com.wallops.java.reference.Move;


public class Act implements ActionListener {
	private Move btnMv;
	private GuiGameScreen battle;

	public Act(Move m, GuiGameScreen g) {
		btnMv = m;
		battle = g;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(btnMv.getCategory() == Move.NO)
			return;
		if(btnMv.getCategory() == Move.STATUS_OPPONENT)
			battle.status(btnMv, battle.getActive(), battle.getOpponent());
		if(btnMv.getCategory() == Move.STATUS_SELF)
			battle.status(btnMv, battle.getActive(), battle.getActive());
		if(btnMv.getCategory() == Move.PHYSICAL || btnMv.getCategory() == Move.SPECIAL)
			battle.attack(btnMv, battle.getActive(), battle.getOpponent());
		if(battle.getOpponent() != null && battle.getOpponent().getCurrentHealth() > 0) {
			Move m2 = Move.NONE;
			
			while(m2 == Move.NONE) {
				int n = (int) (Math.random()*4+1);
				if(n == 1)
					m2 = battle.getOpponent().getMoveOne();
				if(n == 2)
					m2 = battle.getOpponent().getMoveTwo();
				if(n == 3)
					m2 = battle.getOpponent().getMoveThree();
				if(n == 4)
					m2 = battle.getOpponent().getMoveFour();
			}

			if(m2.getCategory() == Move.PHYSICAL || m2.getCategory() == Move.SPECIAL)
				battle.attack(m2, battle.getOpponent(), battle.getActive());
			if(m2.getCategory() == Move.STATUS_SELF)
				battle.status(m2, battle.getOpponent(), battle.getOpponent());
			if(m2.getCategory() == Move.STATUS_OPPONENT)
				battle.status(m2, battle.getOpponent(), battle.getActive());
			if(m2.getCategory() == Move.NO)
				return;

		}
	}

}
