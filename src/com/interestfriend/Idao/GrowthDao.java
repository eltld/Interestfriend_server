package com.interestfriend.Idao;

import java.sql.ResultSet;

import com.interestfriend.bean.Growth;

public interface GrowthDao {
	int insertGrowthToDB(Growth growth);

	ResultSet getGrowthByCid(int cid, int refushState, String refushTime);

	int getGorwthPraiseCount(int growth_id);// 获取赞的数量

	boolean updateGrowthPraiseCount(int growth_id);// 更新赞的数量

	int getGorwthCommentCount(int growth_id);// 获取评论数量

	boolean updateGrowthCommentCount(int growth_id);// 更新评论数量
}
