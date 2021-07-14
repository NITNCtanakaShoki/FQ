import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SorcererTest {
    private Sorcerer sorcerer1;
    private Sorcerer sorcerer2;
    private Sorcerer sorcerer3;

    @BeforeEach
    public void インスタンス化() {
        sorcerer1 = new Sorcerer("名前", 34, 300, 0);
        assertEquals("名前 HP:34 SP:0", sorcerer1.toString());

        sorcerer2 = new Sorcerer("name", 0,11, 1);
        assertEquals("name HP:0 SP:0", sorcerer2.toString());

        sorcerer3 = new Sorcerer("ne", 1, 0, 40);
        assertEquals("ne HP:1 SP:0", sorcerer3.toString());
    }


    @Test
    public void nameのgetter() {
        assertEquals("名前", sorcerer1.getName());
        assertEquals("name", sorcerer2.getName());
        assertEquals("ne", sorcerer3.getName());
    }

    @Test
    public void addのテスト() {
        sorcerer1.addSP(100);
        assertEquals("名前 HP:34 SP:100", sorcerer1.toString());

        sorcerer1.addSP(200);
        assertEquals("名前 HP:34 SP:300", sorcerer1.toString());
    }

    @Test
    public void attackのテスト() {
        assertEquals(600, sorcerer1.attack());
        assertEquals(22, sorcerer2.attack());

        assertEquals("名前 HP:34 SP:1", sorcerer1.toString());
        sorcerer1.attack();
        assertEquals("名前 HP:34 SP:2", sorcerer1.toString());
    }

    @Test
    public void magicとconcentrateのテスト() {
        assertEquals(0, sorcerer1.magic());
        assertEquals("名前 HP:34 SP:1", sorcerer1.toString());

        assertEquals(2, sorcerer2.magic());
        assertEquals("name HP:0 SP:1", sorcerer2.toString());

        assertEquals(80, sorcerer3.magic());
        assertEquals("ne HP:1 SP:1", sorcerer3.toString());

        sorcerer3.concentrate();
        assertEquals(160, sorcerer3.magic());
        assertEquals("ne HP:1 SP:2", sorcerer3.toString());

        sorcerer3.concentrate();
        assertEquals(320, sorcerer3.magic());
        assertEquals("ne HP:1 SP:3", sorcerer3.toString());
    }

    @Test
    public void addDamageのテスト() {
        sorcerer1.addDamage(10);
        assertEquals("名前 HP:24 SP:0", sorcerer1.toString());
        sorcerer1.addDamage(5);
        assertEquals("名前 HP:19 SP:0", sorcerer1.toString());
    }

    @Test
    public void isDownのテスト() {
        sorcerer1.addDamage(33);
        assertFalse(sorcerer1.isDown());
        sorcerer1.addDamage(1);
        assertTrue(sorcerer1.isDown());
        sorcerer1.addDamage(1);
        assertTrue(sorcerer1.isDown());
    }

    @Test
    public void specialCalcのテスト() {
        assertEquals(160, sorcerer3.specialCalc());
    }

    @Test
    public void specialのテスト() {
        assertEquals(-1, sorcerer3.special());
        assertEquals("ne HP:1 SP:0", sorcerer3.toString());

        sorcerer3.addSP(4);
        assertEquals(-1, sorcerer3.special());
        assertEquals("ne HP:1 SP:4", sorcerer3.toString());

        sorcerer3.concentrate();

        sorcerer3.addSP(1);
        assertEquals(320, sorcerer3.special());
        assertEquals("ne HP:1 SP:0", sorcerer3.toString());

        sorcerer3.addSP(5);
        assertEquals(160, sorcerer3.special());
        assertEquals("ne HP:1 SP:0", sorcerer3.toString());
    }

    @Test
    public void act1のテスト() {
       assertTrue(sorcerer2.act(1, sorcerer3));
       assertEquals("name HP:0 SP:1", sorcerer2.toString());
       assertEquals("ne HP:-21 SP:0", sorcerer3.toString());
    }

    @Test
    public void act2のテスト() {
        assertTrue(sorcerer3.act(2, sorcerer2));
        assertEquals("name HP:-80 SP:0", sorcerer2.toString());
        assertEquals("ne HP:1 SP:1", sorcerer3.toString());
    }

    @Test
    public void act4のテスト() {
        assertEquals(80, sorcerer3.magic());

        assertTrue( sorcerer3.act(4, sorcerer2) );

        assertEquals(160, sorcerer3.magic());
    }

    @Test
    public void act5のテスト() {
        // concentrateを4にする
        sorcerer3.concentrate();
        sorcerer3.concentrate();

        // sorcerer3のspが0なので何もせずfalse
        assertFalse(sorcerer3.act(5, sorcerer1));
        assertEquals("名前 HP:34 SP:0", sorcerer1.toString());
        assertEquals("ne HP:1 SP:0", sorcerer3.toString());

        // sorcerer3のspが4なので何もせずfalse
        sorcerer3.addSP(4);
        assertFalse(sorcerer3.act(5, sorcerer1));
        assertEquals("名前 HP:34 SP:0", sorcerer1.toString());
        assertEquals("ne HP:1 SP:4", sorcerer3.toString());

        /*
            sorcerer3のspが5なので
            1のHPが(2 × 40(inteligence) × 4(concentration))(magic) × 2減る。
            concentrateが1減る→3になる
        */
        sorcerer3.addSP(1);
        assertTrue(sorcerer3.act(5, sorcerer1));
        assertEquals("名前 HP:-606 SP:0", sorcerer1.toString());
        assertEquals("ne HP:1 SP:0", sorcerer3.toString());

        /*
            sorcerer3のspが6なので
            1のHPが(2 × 40(inteligence) × 3(concentration))(magic) × 2減る。
            concentrateが1減る→2になる
        */
        sorcerer3.addSP(6);
        assertTrue(sorcerer3.act(5, sorcerer1));
        assertEquals("名前 HP:-1086 SP:0", sorcerer1.toString());
        assertEquals("ne HP:1 SP:0", sorcerer3.toString());
    }

}
