import Model.Pacman;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacmanTest {

    @Test
    public void PacmanTest(){
        Pacman pacman = new Pacman(10, 20, "PacmanRules");
        assertEquals(pacman.getPacmanName(), "PacmanRules");
    }

    @Test
    public void PacmanScoreTest(){
        Pacman pacman = new Pacman(10, 20, "PacmanRules");
        assertTrue(pacman.getScore() == 0);
        assertTrue(pacman.getLifeScore() == 3);
    }

}