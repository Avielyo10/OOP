package Sql;

import java.sql.PreparedStatement;
import java.io.IOException;
import java.nio.channels.GatheringByteChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import wifi.wifi_scan;
import wifi.wifi_scans;

import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MySQL_101 {

	  public static String _ip = "5.29.193.52";
	  public static String _port = "3306";
	  public static String _urld = "oop_course_ariel";
	  public static String _url = "jdbc:mysql://"+_ip+":"+ _port+"/"+_urld;
	  public static String _user = "oop1";
	  public static String _password = "Lambda1();";
	  public static Connection _con = null;
 
    public synchronized static wifi_scans getData() throws NumberFormatException, ParseException {
        Statement st = null;
        ResultSet rs = null;
        wifi_scans db = new wifi_scans();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {     
            _con = DriverManager.getConnection(_url, _user, _password);
            st = _con.createStatement();
            PreparedStatement pst = _con.prepareStatement("SELECT * FROM ex4_db");
            rs = pst.executeQuery();
            int ind=0;
            while (rs.next()) {
            	int size = rs.getInt(7);
            	int len = 7+2*size;
            	wifi_scan scan = new wifi_scan();
            	if(ind%100==0) {
            		ArrayList<String> _scan = new ArrayList<String>();
            		for(int i=1;i<=len;i++){
            			_scan.add(rs.getString(i));
            		}
            		scan.setScan(_scan);
            	}
            	ind++;
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySQL_101.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (st != null) { st.close(); }
                if (_con != null) { _con.close();  }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(MySQL_101.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return db;
    }
    public static Date getUpdate() throws ParseException 
    {
    	Statement st = null;
        ResultSet rs = null;
        Date date = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {     
            _con = DriverManager.getConnection(_url, _user, _password);
            st = _con.createStatement();
            rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = 'oop_course_ariel' AND TABLE_NAME = 'ex4_db'");
            if (rs.next()) {
               date = format.parse(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySQL_101.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (st != null) { st.close(); }
                if (_con != null) { _con.close();  }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(MySQL_101.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return date;
    }
}
