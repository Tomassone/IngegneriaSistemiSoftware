package conway26appl;
import conway.io.IoJavalin;
import main.java.conway.devices.ConwayWebOutDev;
import main.java.conway.domain.GameController;
import main.java.conway.domain.IOutDev;
import main.java.conway.domain.Life;
import main.java.conway.domain.LifeController;
//import main.java.conway.devices.OutInWs;
//import main.java.conway.domain.*;
import unibo.basicomm23.utils.CommUtils;
public class MainConwayGui  {
   	private static IoJavalin server;
   	
    public static void main(String[] args) {
	    System.out.println("MainConway | STARTS " );  
	    
		var resource = MainConwayGui.class.getResource("/page");
		CommUtils.outgreen("DEBUG: La cartella /page si trova in: " + resource);
		
		Life life = new Life(20, 20);          
        IOutDev webUI = new ConwayWebOutDev(); //dispositivo i/o
        GameController controller = new LifeController(life, webUI); 
        server = new IoJavalin();
        ((ConwayWebOutDev) webUI).setController(controller);
        ((ConwayWebOutDev) webUI).setIoJavalin(server);
        server.setOutDev(webUI);
		
	    MainConwayGui app = new MainConwayGui();
	    System.out.println("MainConway | ENDS " );  
    }

}