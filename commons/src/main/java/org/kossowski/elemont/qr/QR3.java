/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.qr;

import org.kossowski.elemont.domain.User;

/**
 *
 * @author jkossow
 */
public class QR3 extends QR {
    
    private static final String QR3Prefix = "03;";

    private String userCode;
    
    public QR3( String code ) {
        this.userCode = code;
    }
    
    public QR3( User user) {
        this.userCode = user.getKodQR();
    }

    @Override
    public String encode() {
        return QR3Prefix + userCode;
    }

    
    
    
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    
    
    
    
    
    
    
    
    
}
