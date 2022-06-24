package kw.proj2.service;

import kw.proj2.vo.boardVO;
import java.util.List;

public interface boardService // board Service interface(connect between DAO and controller)
{
	public void writeArticleMain(boardVO bVO) throws Exception;
	
	public void writeArticleSub(boardVO bVO) throws Exception;
	
	public void updateSubord(boardVO bVO) throws Exception;
	
	public List<boardVO> getList(int top) throws Exception;
	
	public boardVO readArticle(int anum) throws Exception;
	
	public void modifyArticle(boardVO bVO) throws Exception;
	
	public void deleteArticle(int anum) throws Exception;
	
	public void increaseViews(int anum) throws Exception;
	
	public int getTotalRows() throws Exception;
}
