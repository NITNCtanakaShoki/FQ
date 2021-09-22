import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {
    private class CharacterChild extends Character {
        public CharacterChild(String name, int hp, int strength, int intelligence) {
            super(name, hp, strength, intelligence);
        }
        @Override
        public int specialCalc() {
            super.attack();
            return 10;
        }
        @Override
        public void listAction() {}
        @Override
        public boolean act(int type, Character c) {return true;}
    }
    private CharacterChild character1;
    private CharacterChild character2;
    private CharacterChild character3;

    @BeforeEach
    public void SetUp() {
        character1 = new CharacterChild("名前", 34, 300, 0);
        character2 = new CharacterChild("name", 0,11, 1);
        character3 = new CharacterChild("ne", 1, 0, 40);
    }

    @Test
    public void toStringのテスト() {
        assertEquals("名前 HP:34 SP:0", character1.toString());
        assertEquals("name HP:0 SP:0", character2.toString());
        assertEquals("ne HP:1 SP:0", character3.toString());
    }

    @Test
    public void nameのgetterのテスト() {
        assertEquals("名前", character1.getName());
        assertEquals("name", character2.getName());
        assertEquals("ne", character3.getName());
    }

    @Test
    public void addのテスト() {
        character1.addSP(100);
        assertEquals("名前 HP:34 SP:100", character1.toString());

        character1.addSP(200);
        assertEquals("名前 HP:34 SP:300", character1.toString());
    }

    @Test
    public void attackのダメージ量のテスト() {
        assertEquals(600, character1.attack());
        assertEquals(22, character2.attack());


    }

    @Test
    public void attackのSP増加テスト() {
        character1.attack();
        assertEquals("名前 HP:34 SP:1", character1.toString());
        character1.attack();
        assertEquals("名前 HP:34 SP:2", character1.toString());
    }

    @Test
    public void magicのダメージ量のテスト() {
        assertEquals(0, character1.magic());
        assertEquals(2, character2.magic());
    }

    @Test
    public void magicのSP増加のテスト() {
        character1.magic();
        assertEquals("名前 HP:34 SP:1", character1.toString());
        character1.magic();
        assertEquals("名前 HP:34 SP:2", character1.toString());
    }

    @Test
    public void addDamageのテスト() {
        character1.addDamage(10);
        assertEquals("名前 HP:24 SP:0", character1.toString());
        character1.addDamage(5);
        assertEquals("名前 HP:19 SP:0", character1.toString());
    }

    @Test
    public void isDownのテスト() {
        character1.addDamage(33);
        assertFalse(character1.isDown());
        character1.addDamage(1);
        assertTrue(character1.isDown());
        character1.addDamage(1);
        assertTrue(character1.isDown());
    }

    @Test
    public void specialのテスト() {
        // SPが0のとき
        assertEquals(-1, character1.special());
        assertEquals("名前 HP:34 SP:0", character1.toString());

        // SPが4のとき
        character1.addSP(4);
        assertEquals(-1, character1.special());
        assertEquals("名前 HP:34 SP:4", character1.toString());

        // SPが5のとき
        character1.addSP(1);
        assertEquals(10, character1.special());
        assertEquals("名前 HP:34 SP:0", character1.toString());

        // SPが6のとき
        character1.addSP(6);
        assertEquals(10, character1.special());
        assertEquals("名前 HP:34 SP:0", character1.toString());
    }

}
