public class Samurai extends Character implements Fighter {

    public Samurai(String name, int hp, int strength, int intelligence) {
        super(name, hp, strength, intelligence);
    }

    @Override
    public int magic() {
        return 0;
    }

    @Override
    public int specialCalc() {
        return attack() * 5;
    }

    @Override
    public void listAction() {
        System.out.println("1:attack, 3:ready, 5:special");
    }

    @Override
    public boolean act(int type, Character c) {
        if (type == 1) return attackAction(c);
        if (type == 3) return readyAction();
        if (type == 5) return specialAction(c);
        System.out.println("Invalid input!");
        return false;
    }

    private boolean attackAction(Character c) {
        System.out.println("attack");
        c.addDamage( attack() );
        return true;
    }

    private boolean readyAction() {
        System.out.println("ready");
        ready();
        return true;
    }
    private boolean specialAction(Character c) {
        final int damage = special();
        if (damage == -1) {
            System.out.println("lack of SP");
            return false;
        }
        System.out.println("special");
        c.addDamage(damage);
        return true;
    }
    @Override
    public void ready() {
        addSP(3);
    }
}
