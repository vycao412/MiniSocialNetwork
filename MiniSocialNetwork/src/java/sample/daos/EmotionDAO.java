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
import sample.dtos.EmotionDTO;
import sample.utils.DBUtils;

/**
 *
 * @author User
 */
public class EmotionDAO {

    private static final Logger LOG = Logger.getLogger(EmotionDAO.class);

    public EmotionDTO findEmoByUser(int postID, int userID) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        EmotionDTO result = null;

        LOG.info("findEmoByUser of EmotionDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT isLike,isDislike FROM tblEmotions "
                        + "WHERE postID=? AND userID=?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                stm.setInt(2, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    boolean isLike = rs.getBoolean("isLike");
                    boolean isDislike = rs.getBoolean("isDislike");
                    result = new EmotionDTO(postID, userID, isLike, isDislike);
                }
            }
        } catch (Exception e) {
            LOG.error("Error at findEmoByUser of EmotionDAO: " + e.toString());
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

    public List<EmotionDTO> findEmotion(int postID) throws SQLException {
        LOG.info("findEmotion");

        List<EmotionDTO> result = new ArrayList<EmotionDTO>();
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT isLike,isDislike FROM tblEmotions "
                        + "WHERE postID=?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    int userID = rs.getInt("userID");
                    boolean isLike = rs.getBoolean("isLike");
                    boolean isDislike = rs.getBoolean("isDislike");
                    Date date = rs.getDate("date");
                    result.add(new EmotionDTO(id, postID, userID, isLike, isDislike, date));
                }
            }
        } catch (Exception e) {
            LOG.error("Error at findEmoByUser of EmotionDAO: " + e.toString());
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

    public boolean update(EmotionDTO dto) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;
        boolean result = false;

        LOG.info("update of EmotionDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblEmotions SET isLike=?, isDislike=?, date=? "
                        + "WHERE postID=? AND userID=?";
                stm = cnn.prepareStatement(sql);
                stm.setBoolean(1, dto.isIsLike());
                stm.setBoolean(2, dto.isIsDislike());
                stm.setDate(3, dto.getDate());
                stm.setInt(4, dto.getPostID());
                stm.setInt(5, dto.getUserID());
                int i = stm.executeUpdate();

                if (i > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at update of EmotionDAO: " + e.toString());
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

    public boolean deleteByAdminArticle(int postID) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;
        boolean result = false;

        LOG.info("deleteByAdminArticle of EmotionDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "DELETE FROM tblEmotions WHERE postID = ?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at deleteByAdminArticle of EmotionDAO: " + e.toString());
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
        Connection cnn = null;
        PreparedStatement stm = null;
        boolean result = false;

        LOG.info("deleteByUserArticle of EmotionDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblEmotions SET status=4 "
                        + "WHERE postID=?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at deleteByUserArticle of EmotionDAO: " + e.toString());
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

    public boolean create(EmotionDTO dto) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;
        boolean result = false;

        LOG.info("create of EmotionDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "INSERT INTO tblEmotions(postID,userID,isLike,isDislike,date) VALUES(?,?,?,?,?)";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, dto.getPostID());
                stm.setInt(2, dto.getUserID());
                stm.setBoolean(3, dto.isIsLike());
                stm.setBoolean(4, dto.isIsDislike());
                stm.setDate(5, dto.getDate());

                int i = stm.executeUpdate();

                if (i > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at create of EmotionDAO: " + e.toString());
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

    public int countLike(int postID) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;

        LOG.info("count(like) of EmotionDAO");

        try {
            cnn = DBUtils.getConnection();

            if (cnn != null) {
                String sql = "SELECT count(*) FROM tblEmotions WHERE postID=? AND isLike=1";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                rs = stm.executeQuery();

                while (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            LOG.error("Error at count(like) of EmotionDAO: " + e.toString());
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

    public int countDislike(int postID) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;

        try {
            cnn = DBUtils.getConnection();

            if (cnn != null) {
                String sql = "SELECT count(*) FROM tblEmotions WHERE postID=? AND isDislike=1";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                rs = stm.executeQuery();

                while (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            LOG.error("Error at count(dislike) of EmotionDAO: " + e.toString());
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
