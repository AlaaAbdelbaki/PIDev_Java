/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.services;


import Entity.Order;
import tunisiagottalent.Entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tunisiagottalent.util.DataSource;

/**
 *
 * @author paspo
 */
public class OrderServices {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pset;
    private ResultSet rez;

    public OrderServices() {
        cnx = DataSource.getInstance().getCnx();
    }

    public ObservableList<Order> getAll() {
        String req = "select * from orders ;";
        ObservableList<Order> list;
        list = FXCollections.observableArrayList();

        try {
            ste = cnx.createStatement();
            rez = ste.executeQuery(req);

            while (rez.next()) {
                list.add(new Order(rez.getInt("id"), rez.getDate("order_date"), rez.getDouble("total"), rez.getString("address")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void addOrder(Order o, List<Product> list) {
        String req1 = "insert into orders(order_date,total,address) values(?,?,?) ;";

        String req3 = "insert into order_line(product_id,orders_id,quantity) values (?,?,?) ;";

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        try {

            pset = cnx.prepareStatement(req1);

            pset.setDate(1, date);
            pset.setDouble(2, o.getTotal());
            pset.setString(3, o.getAddress());
            pset.executeUpdate();

            pset = cnx.prepareStatement("select last_insert_id() as a from orders ;");
            rez = pset.executeQuery();
            rez.next();
            int oid = rez.getInt("a");


            for (Product i : list) {
                pset = cnx.prepareStatement("SET FOREIGN_KEY_CHECKS=0;");
                pset.executeQuery();
                pset = cnx.prepareStatement(req3);
                pset.setInt(1, i.getId());
                pset.setInt(2, oid);
                pset.setDouble(3, i.getQuantity());

                pset.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteOrder(int idorder,int idorderline) {
        String req = "delete from orders where id='" + idorder + "' ;";
        String req2 = "delete from order_line where orders_id='" + idorderline + "' ;";
        try {
            pset = cnx.prepareStatement("SET FOREIGN_KEY_CHECKS=0;");
            pset.executeQuery();
            ste = cnx.createStatement();
            ste.executeUpdate(req);
            
            ste = cnx.createStatement();
            ste.executeUpdate(req2);
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifyOrder(Order o1, int id) {
        String req = "update orders set order_date=?,total=?,address=? where id='" + id + "' ;";

        try {
            pset = cnx.prepareStatement(req);
            pset.setDate(1, o1.getOrder_date());
            pset.setDouble(2, o1.getTotal());
            pset.setString(3, o1.getAddress());
            pset.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteProduct(int id) {
        String req = "delete from product where id='" + id + "' ;";

        try {
            ste = cnx.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(OrderServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
