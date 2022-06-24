package kw.proj2.dao;

import kw.proj2.vo.boardVO;
import java.util.List;

public interface boardDAO // board Data Access Object(DAO) interface
{
	// insert new article to DB.
	public void writeArticleMain(boardVO bVO) throws Exception; 
	
	// insert new reply of specific article to DB.
	public void writeArticleSub(boardVO bVO) throws Exception; 
	
	// update sub sorting order of existing replies of same parent article when inserting new reply.
	public void updateSubord(boardVO bVO) throws Exception; 
	
	// return list of articles which article number is top ~ top + 9
	public List<boardVO> getList(int top) throws Exception; 
	
	// return single article which article number is anum.
	public boardVO readArticle(int anum) throws Exception;
	
	// modify article which article number is bVO.anum
	public void modifyArticle(boardVO bVO) throws Exception; 
	
	// delete article which article number is anum.
	public void deleteArticle(int anum) throws Exception;
	
	// increase views by 1 of article which article number is anum.
	public void increaseViews(int anum) throws Exception;
	
	// return total article count.
	public int getTotalRows() throws Exception; 
}
