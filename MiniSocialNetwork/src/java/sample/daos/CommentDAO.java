/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import sample.dtos.CommentDTO;
import sample.utils.DBUtils;

/**
 *
 * @author User
 */
public class CommentDAO {

    private static final Logger LOG = Logger.getLogger(CommentDAO.class);

    public boolean addComment(CommentDTO dto) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;
        boolean result = false;

        LOG.info("addComment of CommentDAO");

        try {
            cnn = DBUtils.getConnection();

            if (cnn != null) {
                String sql = "INSERT INTO tblComments(postID,userID,contents,date,status) VALUES (?,?,?,?,?)";
                stm = cnn.prepareStatement(sql);

                stm.setInt(1, dto.getPostID());
                stm.setInt(2, dto.getUserID());
                stm.setString(3, dto.getContent());
                stm.setDate(4, dto.getDate());
                stm.setInt(5, dto.getStatus());
                int i = stm.executeUpdate();

                if (i > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at addComment of CommentDAO: " + e.toString());
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

    public List<CommentDTO> getListComment(int postID) throws SQLException {
        List<CommentDTO> result = new ArrayList<CommentDTO>();
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        LOG.info("getListComment() of CommentDAO");

        try {
            cnn = DBUtils.getConnection();

            if (cnn != null) {
                String sql = "SELECT ID, userID, contents, date, status FROM tblComments WHERE postID=? AND status=1 "
                        + "ORDER BY date DESC";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    int userID = rs.getInt("userID");
                    String content = rs.getString("contents");
                    Date date = rs.getDate("date");
                    int status = rs.getInt("status");

                    result.add(new CommentDTO(id, postID, userID, content, date, status));
                }
            }
        } catch (Exception e) {
            LOG.error("Error at getListComment() of CommentDAO: " + e.toString());
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

    public boolean deleteByAdmin(int commentID) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;

        LOG.info("deleteByAdmin of CommentDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "DELETE FROM tblComments WHERE ID = ?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, commentID);
                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at deleteByAdmin of CommentDAO: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return false;
    }
    
    public boolean deleteByAdminArticle(int postID) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;

        LOG.info("deleteByAdminArticle of CommentDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "DELETE FROM tblComments WHERE postID = ?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at deleteByAdminArticle of CommentDAO: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return false;
    }

    public boolean deleteByUser(int commentID) throws SQLException {
        boolean result = false;
        Connection cnn = null;
        PreparedStatement stm = null;

        LOG.info("deleteByUser of CommentDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblComments SET status=4 "
                        + "WHERE ID=?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, commentID);
                int i = stm.executeUpdate();

                if (i > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at deleteByUser of CommentDAO: " + e.toString());
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
    
    public boolean deleteByUserArticle(int postID) throws SQLException {
        boolean result = false;
        Connection cnn = null;
        PreparedStatement stm = null;

        LOG.info("deleteByUserArticle of CommentDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblComments SET status=5 "
                        + "WHERE postID=?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                int i = stm.executeUpdate();

                if (i > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at deleteByUserArticle of CommentDAO: " + e.toString());
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
    
    public CommentDTO findComment(int postID) throws SQLException {
        LOG.info("findComment");

        CommentDTO result = null;
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT ID, userID, contents, date, status FROM tblComments WHERE postID=?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    int userID = rs.getInt("userID");
                    String content = rs.getString("contents");
                    Date date = rs.getDate("date");
                    int status = rs.getInt("status");

                    result = new CommentDTO(id, postID, userID, content, date, status);
                }
            }
        } catch (Exception e) {
            LOG.error("Error at findComment: " + e.toString());
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

}
