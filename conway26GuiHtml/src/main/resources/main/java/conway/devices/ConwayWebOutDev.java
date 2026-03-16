package main.java.conway.devices;

import conway.io.IoJavalin;
import io.javalin.Javalin;
import main.java.conway.domain.GameController;
import main.java.conway.domain.ICell;
import main.java.conway.domain.IGrid;
import main.java.conway.domain.IOutDev;
import unibo.basicomm23.utils.CommUtils;

public class ConwayWebOutDev implements IOutDev {

    private static final int GRID_SIZE = 20;
    private IoJavalin ioJavalin;
    private GameController controller;

    public ConwayWebOutDev() {
    }

    @Override
    public void display(String msg) {
        CommUtils.outyellow("ConwayWebOutDev | display: " + msg);
        ioJavalin.sendToPage(msg);
    }

    @Override
    public void displayCell(IGrid grid, int x, int y) {
        try {
            ICell cell = grid.getCell(x, y);
            boolean alive = cell.isAlive();
            String msg = "cell(" + y + "," + x + "," + (alive ? 1 : 0) + ")";
            CommUtils.outyellow("ConwayWebOutDev | displayCell: " + msg);
            ioJavalin.sendToPage(msg);
        } catch (Exception e) {
            CommUtils.outred("ConwayWebOutDev | displayCell ERROR: " + e.getMessage());
        }
    }

    @Override
    public void displayGrid(IGrid grid) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                displayCell(grid, i, j);
            }
        }
    }
    
    public void setIoJavalin(IoJavalin ioJavalin) {
    	this.ioJavalin = ioJavalin;
    }
    
    public void setController(GameController controller) {
    	this.controller = controller;
    }
    
    public GameController getController() {
    	return controller;
    }

    @Override
    public void close() {
        // nulla da chiudere lato web
    }
}