public abstract class Character {
    private String name;
    private int hp;
    private int sp;
    private int strength;
    private int intelligence;

    public Character(String name, int hp, int strength, int intelligence) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.intelligence = intelligence;
        this.sp = 0;
    }

    public String getName() {
        return name;
    }

    public void addSP(int point) {
        sp += point;
    }

    public int attack() {
        addSP(1);
        return strength * 2;
    }

    public int magic() {
        addSP(1);
        return intelligence * 2;
    }

    public void addDamage(int damage) {
        hp -= damage;
    }

    public boolean isDown() {
        return hp <= 0;
    }

    public int special() {
        if ( !isCanSpecial() ) return -1;
        return executeSpecialDamage();
    }

    public abstract int specialCalc();
    public abstract void listAction();
    public abstract boolean act(int type, Character c);

    private boolean isCanSpecial() {
        return sp >= 5;
    }

    private int executeSpecialDamage() {
        // specialCalc実行時SP変動の可能性
        final int specialDamage = specialCalc();
        sp = 0;
        return specialDamage;
    }

    protected boolean specialAction( Character target ) {
        final int damage = special();
        if (damage == -1) {
            System.out.println("lack of SP");
            return false;
        }
        System.out.println("special");
        target.addDamage(damage);
        return true;
    }
    @Override
    public String toString() {
        return String.format(
                "%s HP:%d SP:%d",
                name,
                hp,
                sp
        );
    }
}
