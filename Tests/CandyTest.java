import Model.Candy;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CandyTest extends Canvas {

    @Test
    void CandyTest() {
        Candy candy = new Candy(10, 10, "yellow");
        assertEquals(candy.getType(), "yellow");
    }
}