
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ControllerTest {
MappeableFactory<String, String> factory  = new MappeableFactory<>();
    @Test
    void initHash() {
        Controller controller = new Controller(factory.getMapHash());
        try {
            controller.init(0);
            Assert.assertTrue(true);
        }catch (Exception e){
            Assert.assertTrue(false);
        }
    }

    @Test
    void initRedTree() {
        Controller controller = new Controller(factory.getTree());
        try {
            controller.init(0);
            Assert.assertTrue(true);
        }catch (Exception e){
            Assert.assertTrue(false);
        }
    }

    @Test
    void traduceTree() {
        Controller controller = new Controller(factory.getTree());
        try {
            controller.init(0);
            Assert.assertTrue(true);
            String result = controller.traduce("Este texto work");
            Assert.assertEquals(result, "*Este* *texto* trabajo ");
        }catch (Exception e){
            Assert.assertTrue(false);
        }
    }
    @Test
    void traduceHash() {
        Controller controller = new Controller(factory.getMapHash());
        try {
            controller.init(0);
            Assert.assertTrue(true);
            String result = controller.traduce("Este texto work");
            Assert.assertTrue(result.equalsIgnoreCase("*Este* *texto* trabajo "));
        }catch (Exception e){
            Assert.assertTrue(false);
        }
    }



}