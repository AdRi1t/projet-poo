package Model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import Vue.TuileFond;

public class Creature {
	
	public static List<Creature> listCreature = new ArrayList<Creature>();
	private TypeCreature type;
	private Tuile emplacement;
	private Image creatureImage;
	
	public Image getCreatureImage() {
		return creatureImage;
	}

	public void setCreatureImage(Image creatureImage) {
		this.creatureImage = creatureImage;
	}

	public Creature(TypeCreature type) {
		this.setType(type);
		this.setEmplacement(null);
		TuileFond.setImageCreature(this);
	}
	
	public static void initCreature() {
		Tuile tuile1 = Tuile.listeTuile.get(Tuile.getIndex(3, 1));
		Tuile tuile2 = Tuile.listeTuile.get(Tuile.getIndex(22, 2));
		Tuile tuile3 = Tuile.listeTuile.get(Tuile.getIndex(12, 6));
		Tuile tuile4 = Tuile.listeTuile.get(Tuile.getIndex(3, 11));
		Tuile tuile5 = Tuile.listeTuile.get(Tuile.getIndex(22, 10));
		Creature creature1 = new Creature(TypeCreature.SERPENT);
		Creature creature2 = new Creature(TypeCreature.SERPENT);
		Creature creature3 = new Creature(TypeCreature.SERPENT);
		Creature creature4 = new Creature(TypeCreature.SERPENT);
		Creature creature5 = new Creature(TypeCreature.SERPENT);
		creature1.setEmplacement(tuile1);
		creature2.setEmplacement(tuile2);
		creature3.setEmplacement(tuile3);
		creature4.setEmplacement(tuile4);
		creature5.setEmplacement(tuile5);
		Creature.listCreature.add(creature1);
		Creature.listCreature.add(creature2);
		Creature.listCreature.add(creature3);
		Creature.listCreature.add(creature4);
		Creature.listCreature.add(creature5);
	}

	public Tuile getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Tuile emplacement) {
		this.emplacement = emplacement;
	}

	public TypeCreature getType() {
		return type;
	}

	public void setType(TypeCreature type) {
		this.type = type;
	}

}
