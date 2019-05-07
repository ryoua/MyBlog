package com.ryoua.myblog.dao;

import com.ryoua.myblog.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * @Author ryoua Created on 2019-05-07
 */
public interface ArticleDao {
    /**
     * 返回相应的数据集合
     *
     * @param map
     * @return
     */
    List<Article> findArticles(Map<String, Object> map);

    /**
     * 数据数目
     *
     * @param map
     * @return
     */
    int getTotalArticles(Map<String, Object> map);

    /**
     * 添加
     *
     * @return
     */
    int insertArticle(Article article);

    /**
     * 修改
     *
     * @return
     */
    int updArticle(Article article);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delArticle(Integer id);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Article getArticleById(Integer id);

    /**
     * 批量删除
     *
     * @param id
     * @return
     */
    int deleteBatch(Object[] id);
}
