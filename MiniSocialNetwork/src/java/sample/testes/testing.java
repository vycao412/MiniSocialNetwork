/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.testes;

import java.util.Date;
import java.sql.SQLException;
import java.util.Scanner;
import javax.naming.NamingException;
import org.apache.commons.codec.digest.DigestUtils;
import sample.daos.ArticleDAO;
import sample.dtos.ArticleDTO;

/**
 *
 * @author User
 */
public class testing {

    public static void main(String[] args) throws SQLException, NamingException {
//        Scanner sc = new Scanner(System.in);
//        
//        String rs = sc.nextLine();
//        boolean result = false;
//        
//        if(rs.matches(".+@gmail.com")) {
//            result = true;
//        }
//        
//        System.out.println(result);

        String encryptedPass = DigestUtils.sha256Hex("test");
    }
}
