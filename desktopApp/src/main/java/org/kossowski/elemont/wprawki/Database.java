/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.wprawki;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 *
 * @author jkossow
 */
public class Database {
    
    public static void main(String[] args) throws Exception {
        
        
        
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test","sa","sa");
        Statement s = conn.createStatement();
        s.execute("drop table if exists test");
        s.execute("create table if not exists test( name varchar, id int)");
        for(int i=0; i<5000; i++ )
           s.execute("insert into test(name,id ) values('janusz kossowski', " + i + ")" );
        //s.execute("insert into test(name) values('polskie ąśżźćńółę ')");
        
        
        //
            
        //s.execute("delete from test");
        
        ResultSet rs = s.executeQuery("select * from test where name like 'janu%'");
        while( rs.next() )
            System.out.println( rs.getString("name"));
        conn.close();
    }
    
}
