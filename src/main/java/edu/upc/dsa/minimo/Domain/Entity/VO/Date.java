package edu.upc.dsa.minimo.Domain.Entity.VO;

import java.time.*;
public class Date {
    public static String getDate(){
        return LocalDate.now().toString();
    }
}
