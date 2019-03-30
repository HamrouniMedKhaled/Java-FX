/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bonplan.test;

import edu.bonplan.util.DataSource;
import edu.bonplan.entities.*;
import edu.bonplan.services.DealServices;
import edu.bonplan.services.ReservationEnseigneService;
import edu.bonplan.services.ReservationdealServices;
import edu.bonplan.util.Vars;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Hamrouni
 */
public class Main11 {
    public static void main(String[] args) throws SQLException {
        
//        DealServices ds= new DealServices();
//        ReservationdealServices rds= new ReservationdealServices();
//        //System.out.println(ds.AfficherDeal(26).getImageId().toString());
//        
        //FosUser u=new FosUser();
       // u.setId(1);
//        List<Deal> maliste = ds.RechercheVillesDeal(u,"aria");
//        
//        System.out.println(maliste.size());
        
        /*Deal d = new Deal(10);
        Date dt= new java.sql.Date(118,02,15);
        d.setNom("hello");
        d.setScore(30);
        d.setPrix(250.50);
        d.setTred(20);
        d.setVisite(0);
        d.setDatedebut(dt);
        d.setDatefin(new java.sql.Date(118,04,22));
        d.setActive(true);
        d.setDescription("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        ds.ajouter(d,3);

        List<Deal> maliste = ds.lister();
        System.out.println(maliste.toString());
        
        ds.modifier(d);*/
        //ds.supprimer(20);
        
        
       // System.out.println(ds.AfficherDeal(17).toString());
        Vars.reservationEnseigne=new Reservation(10);
        Vars.reservationEnseigne.setNbplaces(6);
        Vars.reservationEnseigne.setDatereservation(new java.sql.Date(118,05,22));
        
        ReservationEnseigneService re = new ReservationEnseigneService();
        re.ModifierReservationEnseigne(Vars.reservationEnseigne);
       //List<Reservation> maliste = re.AfficherMesReservation(u);
       //maliste.forEach(r->{
         //       System.out.println(r.getNbplaces());
          //  });
        
    }
}
