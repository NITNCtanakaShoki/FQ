public class Sorcerer extends Character implements Wizard {

    private int concentration;

    public Sorcerer(String name, int hp, int strength, int intelligence) {
        super(name, hp, strength, intelligence);
        concentration = 1;
    }

    @Override
    public int specialCalc() {
        final int damage = magic();
        concentration--;
        return damage * 2;
    }

    @Override
    public void listAction() {
        System.out.println("1:attack, 2:magic, 4:concentrate, 5:special");
    }


    @Override
    public boolean act(int type, Character c) {
        if (type == 1) return attackAction(c);
        if (type == 2) return magicAction(c);
        if (type == 4) return concentrateAction();
        if (type == 5) return specialAction(c);
        System.out.println("Invalid input!");
        return false;
    }

    private boolean attackAction(Character c) {
        System.out.println("attack");
        c.addDamage(attack());
        return true;
    }
    private boolean magicAction(Character c) {
        System.out.println("magic");
        c.addDamage(magic());
        return true;
    }
    private boolean concentrateAction() {
        System.out.println("concentrate");
        concentrate();
        return true;
    }

    @Override
    public void concentrate() {
        concentration *= 2;
    }

    @Override
    public int magic() {
        return super.magic() * concentration;
    }
}
