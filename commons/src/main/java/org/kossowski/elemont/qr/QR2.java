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
public class QR2 extends QR {
    
    private static final String QR2Prefix = "02;";

    private String userCode;
    
    public QR2( String code ) {
        this.userCode = code;
    }
    
    public QR2( User user) {
        this.userCode = user.getKodQR();
    }

    @Override
    public String encode() {
        return QR2Prefix + userCode;
    }

    
    
    
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    
    
    
    
    
    
    
    
    
}
