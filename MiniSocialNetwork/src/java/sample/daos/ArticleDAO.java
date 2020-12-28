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
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import sample.dtos.ArticleDTO;
import sample.utils.DBUtils;

/**
 *
 * @author User
 */
public class ArticleDAO {

    private static final Logger LOG = Logger.getLogger(ArticleDAO.class);

    public int count() throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        LOG.info("count of ArticleDAO");

        try {
            cnn = DBUtils.getConnection();

            if (cnn != null) {
                String sql = "SELECT count(*) FROM tblArticles";
                stm = cnn.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            LOG.error("Error at count of ArticleDAO: " + e.toString());
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
        return 0;
    }

    public int count(String search) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        LOG.info("count(search) of ArticleDAO");

        try {
            cnn = DBUtils.getConnection();

            if (cnn != null) {
                String sql = "SELECT count(*) FROM tblArticles WHERE title LIKE ?";
                stm = cnn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();

                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            LOG.error("Error at count(search) of ArticleDAO: " + e.toString());
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
        return 0;
    }

    public List<ArticleDTO> getListArticle(int index, int size) throws SQLException {
        List<ArticleDTO> result = new ArrayList<ArticleDTO>();
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        LOG.info("getListArticle(index,size) of ArticleDAO");

        try {
            cnn = DBUtils.getConnection();

            if (cnn != null) {
                String sql = "SELECT postID, title, description, image, date, userID, status, numLike, numDislike FROM tblArticles "
                        + "WHERE status=3 ORDER BY date DESC OFFSET (? - 1) * ? ROWS FETCH NEXT ? ROWS ONLY";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, index);
                stm.setInt(2, size);
                stm.setInt(3, size);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int postID = rs.getInt("postID");
                    String title = rs.getNString("title");
                    String description = rs.getNString("description");
                    String image = rs.getString("image");
                    Date date = rs.getDate("date");
                    int userID = rs.getInt("userID");
                    int status = rs.getInt("status");
                    int numLike = rs.getInt("numLike");
                    int numDislike = rs.getInt("numDislike");

                    result.add(new ArticleDTO(postID, title, description, image, date, userID, status, numLike, numDislike));
                }
            }
        } catch (Exception e) {
            LOG.error("Error at getListArticle(index,size) of ArticleDAO: " + e.toString());
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

    public List<ArticleDTO> getListArticle(String search, int index, int size) throws SQLException {
        List<ArticleDTO> result = new ArrayList<ArticleDTO>();
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        LOG.info("getListArticle(search,index,size) of ArticleDAO");

        try {
            cnn = DBUtils.getConnection();

            if (cnn != null) {
                String sql = "SELECT postID, title, description, image, date, userID, status, numLike, numDislike FROM tblArticles WHERE title LIKE ? "
                        + "AND status=3 ORDER BY date DESC OFFSET (? - 1) * ? ROWS FETCH NEXT ? ROWS ONLY";
                stm = cnn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                stm.setInt(2, index);
                stm.setInt(3, size);
                stm.setInt(4, size);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int postID = rs.getInt("postID");
                    String title = rs.getNString("title");
                    String description = rs.getNString("description");
                    String image = rs.getString("image");
                    Date date = rs.getDate("date");
                    int userID = rs.getInt("userID");
                    int status = rs.getInt("status");
                    int numLike = rs.getInt("numLike");
                    int numDislike = rs.getInt("numDislike");

                    result.add(new ArticleDTO(postID, title, description, image, date, userID, status, numLike, numDislike));
                }
            }
        } catch (Exception e) {
            LOG.error("Error at getListArticle(search,index,size) of ArticleDAO: " + e.toString());
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

    public String checkMail(int postID) throws NamingException, SQLException {
        String result = null;
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT mail FROM tblArticles WHERE postID='" + postID + "'";
                stm = cnn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getString("mail");
                }
            }
        } catch (Exception e) {
            LOG.error("Error at checkMail(id) of ArticleDAO: " + e.toString());
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

    public ArticleDTO search(int search) throws SQLException {
        ArticleDTO result = null;
        Connection cnn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        LOG.info("search(search) of ArticleDAO");

        try {
            cnn = DBUtils.getConnection();

            if (cnn != null) {
                String sql = "SELECT title, description, image, date, userID, status, numLike, numDislike FROM tblArticles WHERE postID='" + search + "'"
                        + " AND status=3";
                stm = cnn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String title = rs.getNString("title");
                    String description = rs.getNString("description");
                    String image = rs.getString("image");
                    Date date = rs.getDate("date");
                    int userID = rs.getInt("userID");
                    int status = rs.getInt("status");
                    int numLike = rs.getInt("numLike");
                    int numDislike = rs.getInt("numDislike");

                    result = new ArticleDTO(search, title, description, image, date, userID, status, numLike, numDislike);
                }
            }
        } catch (Exception e) {
            LOG.error("Error at search(search) of ArticleDAO: " + e.toString());
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

    public void create(ArticleDTO dto) throws NamingException, SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;

        LOG.info("create of ArticleDAO");

        try {
            cnn = DBUtils.getConnection();

            if (cnn != null) {
                String sql = "INSERT INTO tblArticles(title,description,image,date,userID,status,numLike,numDislike)"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                stm = cnn.prepareStatement(sql);

                stm.setString(1, dto.getTitle());
                stm.setString(2, dto.getDescription());
                stm.setString(3, dto.getImage());
                stm.setDate(4, dto.getDate());
                stm.setInt(5, dto.getUserID());
                stm.setInt(6, dto.getStatus());
                stm.setInt(7, dto.getNumLike());
                stm.setInt(8, dto.getNumDislike());
                stm.executeUpdate();

            }
        } catch (Exception e) {
            LOG.error("Error at create of ArticleDAO: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public boolean deleteByAdmin(int postID) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;

        LOG.info("deleteByAdmin of ArticleDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "DELETE FROM tblArticles WHERE postID = ?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at deleteByAdmin of ArticleDAO: " + e.toString());
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

    public boolean deleteByUser(int postID) throws SQLException {
        boolean result = false;
        Connection cnn = null;
        PreparedStatement stm = null;

        LOG.info("deleteByUser of ArticleDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblArticles SET status=4 "
                        + "WHERE postID=?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, postID);
                int i = stm.executeUpdate();

                if (i > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at deleteByUser of ArticleDAO: " + e.toString());
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

    public boolean update(ArticleDTO dto) throws SQLException {
        Connection cnn = null;
        PreparedStatement stm = null;
        boolean result = false;

        LOG.info("update of ArticleDAO");

        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "UPDATE tblArticles SET numLike=?, numDislike=? "
                        + "WHERE postID=?";
                stm = cnn.prepareStatement(sql);
                stm.setInt(1, dto.getNumLike());
                stm.setInt(2, dto.getNumDislike());
                stm.setInt(3, dto.getPostID());
                
                int i = stm.executeUpdate();
                
                if (i > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOG.error("Error at update of ArticleDAO: " + e.toString());
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
