/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import sample.dtos.NotifyDTO;
import sample.utils.DBUtils;

/**
 *
 * @author VYCAO
 */
public class NotifyDAO implements Serializable {

    private static final Logger LOG = Logger.getLogger(NotifyDAO.class);

    public boolean create(NotifyDTO dto) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;
        boolean result = false;

        LOG.info("create of NotifyDAO");

        try {
            cnn = DBUtils.getConnection();

            if (cnn != null) {
                String sql = "INSERT INTO tblNotifies(postID,userID,date,type,status) VALUES (?,?,?,?,?)";
                stm = cnn.prepareStatement(sql);

                stm.setInt(1, dto.getPostID());
                stm.setInt(2, dto.getUserID());
                stm.setDate(3, dto.getDate());
                stm.setString(4, dto.getType());
                stm.setInt(5, dto.getStatus());
                int i = stm.executeUpdate();

                if (i > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at create of NotifyDAO: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return result;
    }
    
    public List<NotifyDTO> getAllNotify(int user) throws SQLException {
        List<NotifyDTO> result = new ArrayList<NotifyDTO>();
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        LOG.info("getAllNotify() of NotifyDAO");

        try {
            cnn = DBUtils.getConnection();

            if (cnn != null) {
                String sql = "SELECT ID, tblArticles.postID, tblNotifies.date, type, tblNotifies.userID, tblNotifies.status "
                    + "FROM tblNotifies JOIN tblArticles ON tblArticles.postID = tblNotifies.postID "
                    + "WHERE tblNotifies.status != 4 AND tblArticles.userID = ? ORDER BY date DESC";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, user);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    int postID = rs.getInt("postID");
                    Date date = rs.getDate("date");
                    String type = rs.getString("type");
                    int userID = rs.getInt("userID");
                    int status = rs.getInt("status");
                    
                    result.add(new NotifyDTO(id, postID, userID, date, type, status));
                }
            }
        } catch (Exception e) {
            LOG.error("Error at getAllNotify() of NotifyDAO: " + e.toString());
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

    public boolean deleteByUserNotify(int postID) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;
        boolean result = false;

        LOG.info("deleteByUserNotify of NotifyDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblNotifies SET status=4 "
                        + "WHERE ID=?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                int row = stm.executeUpdate();

                if (row > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at deleteByUserNotify of NotifyDAO: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return result;
    }
    
    public boolean deleteByAdminNotify(int postID) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;
        boolean result = false;

        LOG.info("deleteByAdminNotify of NotifyDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "DELETE tblNotifies "
                        + "WHERE postID=?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                int row = stm.executeUpdate();

                if (row > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at deleteByAdminNotify of NotifyDAO: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return result;
    }

    public boolean updateNotifyRead(int notifyID) throws SQLException {
        boolean result = false;
        Connection cnn = null;
        PreparedStatement stm = null;

        LOG.info("updateNotifyRead of NotifyDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblNotifies SET status=5 "
                        + "WHERE ID=?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, notifyID);
                int i = stm.executeUpdate();

                if (i > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at updateNotifyRead of NotifyDAO: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return result;
    }
    
    public boolean updateUserNotifyDelete(int postID) throws SQLException {
        boolean result = false;
        Connection cnn = null;
        PreparedStatement stm = null;

        LOG.info("updateUserNotifyDelete of NotifyDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblNotifies SET status=4 "
                        + "WHERE postID=?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                int i = stm.executeUpdate();

                if (i > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at updateUserNotifyDelete of NotifyDAO: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return result;
    }
    
    public boolean updateAdminNotifyDelete(int postID) throws SQLException {
        boolean result = false;
        Connection cnn = null;
        PreparedStatement stm = null;

        LOG.info("updateAdminNotifyDelete of NotifyDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "DELETE tblNotifies "
                        + "WHERE postID=?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                int i = stm.executeUpdate();

                if (i > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at updateAdminNotifyDelete of NotifyDAO: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return result;
    }

}
