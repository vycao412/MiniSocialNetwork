/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import sample.dtos.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author User
 */
public class UserDAO {

    private static final Logger LOG = Logger.getLogger(UserDAO.class);

    public UserDTO checkLogin(String mail, String password) throws NamingException, SQLException {
        LOG.info("checkLogin");

        UserDTO result = null;
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String encryptedPass = DigestUtils.sha256Hex(password);
                String sql = "SELECT userID,name,role,status FROM tblUsers WHERE mail='" + mail + "' AND password='" + encryptedPass + "'" + " AND status=1";
                stm = cnn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = new UserDTO(rs.getInt("userID"), mail, rs.getNString("name"), "", rs.getInt("role"), rs.getInt("status"));
                }
            }
        } catch (Exception e) {
            LOG.error("Error at checkLogin: " + e.toString());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return result;
    }

    public UserDTO searchUserByUserID(int userID) throws NamingException, SQLException {
        LOG.info("searchUserByUserID");

        UserDTO result = null;
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT mail,name,role,status FROM tblUsers WHERE userID='" + userID + "'";
                stm = cnn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = new UserDTO(userID, rs.getString("mail"), rs.getString("name"), rs.getInt("role"), rs.getInt("status"));
                }
            }
        } catch (Exception e) {
            LOG.error("Error at searchUserByUserID: " + e.toString());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return result;
    }

    public boolean checkMail(String mail) throws NamingException, SQLException {
        LOG.info("checkMail");

        boolean result = true;
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT name FROM tblUsers WHERE mail='" + mail + "'" + " WHERE status=1";
                stm = cnn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = false;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at checkMail: " + e.toString());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return result;
    }

    public void create(UserDTO dto) throws NamingException, SQLException {
        LOG.info("create");

        Connection cnn = null;
        PreparedStatement stm = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "INSERT INTO tblUsers(mail,name,password,role,status) VALUES(?,?,?,?,?)";
                stm = cnn.prepareStatement(sql);
                String encryptedPass = DigestUtils.sha256Hex(dto.getPassword());
                stm.setString(1, dto.getMail());
                stm.setString(2, dto.getName());
                stm.setString(3, encryptedPass);
                stm.setInt(4, dto.getRole());
                stm.setInt(5, dto.getStatus());
                stm.executeUpdate();
            }
        } catch (Exception e) {
            LOG.error("Error at create: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public void loginByEmail(UserDTO dto) throws NamingException, SQLException {
        LOG.info("loginByEmail");

        Connection cnn = null;
        PreparedStatement stm = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "INSERT INTO tblUsers(mail,name,password,role,status,verifyCode) VALUES(?,?,?,?,?,?)";
                stm = cnn.prepareStatement(sql);
                String encryptedPass = DigestUtils.sha256Hex(dto.getPassword());
                stm.setString(1, dto.getMail());
                stm.setString(2, dto.getName());
                stm.setString(3, encryptedPass);
                stm.setInt(4, dto.getRole());
                stm.setInt(5, dto.getStatus());
                stm.setString(6, dto.getVerifyCode());
                stm.executeUpdate();
            }
        } catch (Exception e) {
            LOG.error("Error at loginByEmail: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

}
