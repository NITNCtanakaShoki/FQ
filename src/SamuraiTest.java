import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class SamuraiTest {
    private Samurai samurai1;
    private Samurai samurai2;
    private Samurai samurai3;
    private Scanner scanner;

    @BeforeEach
    public void SetUp() {
        scanner = new Scanner(new StdOutSnatcher());
        samurai1 = new Samurai("名前", 0, 10, 20);
        samurai2 = new Samurai("name", 10,20, 0);
        samurai3 = new Samurai("ne", 20, 0, 10);
    }

    @Test
    public void 継承のテスト() {
        Fighter fighter = samurai1;
        Character character = samurai2;
    }

    @Test
    public void toStringのテスト() {
        assertEquals("名前 HP:0 SP:0", samurai1.toString());
        assertEquals("name HP:10 SP:0", samurai2.toString());
        assertEquals("ne HP:20 SP:0", samurai3.toString());
    }

    @Test
    public void nameのgetter() {
        assertEquals("名前", samurai1.getName());
        assertEquals("name", samurai2.getName());
        assertEquals("ne", samurai3.getName());
    }

    @Test
    public void addのテスト() {
        samurai1.addSP(100);
        assertEquals("名前 HP:0 SP:100", samurai1.toString());

        samurai1.addSP(200);
        assertEquals("名前 HP:0 SP:300", samurai1.toString());
    }

    @Test
    public void attackのテスト() {
        assertEquals(20, samurai1.attack());
        assertEquals(40, samurai2.attack());

        assertEquals("名前 HP:0 SP:1", samurai1.toString());
        samurai1.attack();
        assertEquals("名前 HP:0 SP:2", samurai1.toString());
    }

    @Test
    public void readyのテスト() {
        samurai1.ready();
        assertEquals("名前 HP:0 SP:3", samurai1.toString());
        samurai1.ready();
        assertEquals("名前 HP:0 SP:6", samurai1.toString());
    }

    @Test
    public void magicのテスト() {
        assertEquals(0, samurai1.magic());
        assertEquals(0, samurai2.magic());

        assertEquals("名前 HP:0 SP:0", samurai1.toString());
        assertEquals(0, samurai1.magic());
        assertEquals("名前 HP:0 SP:0", samurai1.toString());
    }

    @Test
    public void addDamageのテスト() {
        samurai1.addDamage(10);
        assertEquals("名前 HP:-10 SP:0", samurai1.toString());
        samurai1.addDamage(5);
        assertEquals("名前 HP:-15 SP:0", samurai1.toString());
    }

    @Test
    public void isDownのテスト() {
        samurai2.addDamage(9);
        assertFalse(samurai2.isDown());
        samurai2.addDamage(1);
        assertTrue(samurai2.isDown());
        samurai2.addDamage(1);
        assertTrue(samurai2.isDown());
    }

    @Test
    public void specialCalcのテスト() {
        assertEquals(100, samurai1.specialCalc());
        assertEquals("名前 HP:0 SP:1", samurai1.toString());

        assertEquals(200, samurai2.specialCalc());
        assertEquals("name HP:10 SP:1", samurai2.toString());

        assertEquals(0, samurai3.specialCalc());
        assertEquals("ne HP:20 SP:1", samurai3.toString());
    }

    @Test
    public void listActionのテスト() {
        samurai1.listAction();
        assertEquals("1:attack, 3:ready, 5:special", scanner.nextLine());
    }

    @Test
    public void specialのテスト() {
        // SP = 0
        assertEquals("名前 HP:0 SP:0", samurai1.toString());
        assertEquals(-1, samurai1.special());

        // SP = 4
        samurai1.addSP(4);
        assertEquals("名前 HP:0 SP:4", samurai1.toString());
        assertEquals(-1, samurai1.special());

        // SP = 5
        samurai1.addSP(1);
        assertEquals("名前 HP:0 SP:5", samurai1.toString());
        assertEquals(100, samurai1.special());

        // SP = 5
        samurai1.addSP(5);
        assertEquals("名前 HP:0 SP:5", samurai1.toString());
        assertEquals(100, samurai1.special());
    }

    @Test
    public void act1のダメージ量のテスト() {
        assertTrue( samurai2.act(1, samurai1) );
        assertEquals("名前 HP:-40 SP:0", samurai1.toString());
        assertEquals("name HP:10 SP:1", samurai2.toString());
    }

    @Test
    public void act1の表示のテスト() {
        assertTrue( samurai2.act(1, samurai1) );
        assertEquals("attack", scanner.next());
    }

    @Test
    public void act3のSP回復量テスト() {
        assertTrue( samurai2.act(3, samurai1) );
        assertEquals("name HP:10 SP:3", samurai2.toString());

        assertTrue( samurai2.act(3, samurai1) );
        assertEquals("name HP:10 SP:6", samurai2.toString());
    }

    @Test
    public void act3の表示テスト() {
        assertTrue( samurai2.act(3, samurai1) );
        assertEquals("ready", scanner.next());
    }

    @Test
    public void act5のテストSP5未満() {
        samurai2.addSP(4);
        assertFalse( samurai2.act(5, samurai1) );
        assertEquals("名前 HP:0 SP:0", samurai1.toString());
        assertEquals("name HP:10 SP:4", samurai2.toString());
    }

    @Test
    public void act5のテストSP5以上() {
        samurai2.addSP(5);
        assertTrue( samurai2.act(5, samurai1) );
        assertEquals("名前 HP:-200 SP:0", samurai1.toString());
        assertEquals("name HP:10 SP:0", samurai2.toString());
    }

    @Test
    public void act5の表示のテスト() {
        samurai1.act(5, samurai2);
        assertEquals("lack of SP", scanner.nextLine());

        samurai1.addSP(5);
        samurai1.act(5, samurai2);
        assertEquals("special", scanner.nextLine());

        samurai1.act(5, samurai2);
        assertEquals("lack of SP", scanner.nextLine());
    }

    @Test
    public void actその他() {
        assertFalse( samurai2.act(2, samurai1) );
        assertEquals("Invalid input!", scanner.nextLine());
        assertFalse( samurai2.act(4, samurai1) );
        assertEquals("Invalid input!", scanner.nextLine());
    }
}
