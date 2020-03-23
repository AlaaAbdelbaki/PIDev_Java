/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent;

import tunisiagottalent.util.DataSource;

/**
 *
 * @author alaa
 */
public class TunisiaGotTalent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DataSource ds1 = DataSource.getInstance();
        System.out.println(ds1);
    }
    
}
