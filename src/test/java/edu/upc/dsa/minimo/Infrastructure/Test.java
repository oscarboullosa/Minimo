package edu.upc.dsa.minimo.Infrastructure;

import edu.upc.dsa.minimo.Domain.Repository.Manager;
import org.junit.Before;

public class Test {
    Manager pm;
    @Before
    public void setUp(){
        pm=new ManagerImpl();
        pm.addUser("1111111", "David", "lopez");
        pm.addUser("2222222",  "Pedro", "guitierrez");
        pm.addUser("3333333",  "Luis", "Hernández");
        pm.addGame("B001","De disparos",4);
        pm.addGame("B008","De futbol",2);
        pm.addGame("B009","De baloncesto",8);
        prepareOrders();
    }

    private void prepareOrders() {
        Order o1
    }
}
