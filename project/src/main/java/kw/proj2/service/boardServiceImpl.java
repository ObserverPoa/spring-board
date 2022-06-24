package kw.proj2.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import kw.proj2.vo.boardVO;
import kw.proj2.dao.boardDAO;

import java.util.List;

@Service // set boardServiceImpl class as service bean
public class boardServiceImpl implements boardService // board Service implementation class
{
	@Inject // automatic dependency injection
	private boardDAO bDAO;
	
	// just call DAO functions.
	@Override
	public void writeArticleMain(boardVO bVO) throws Exception
	{
		bDAO.writeArticleMain(bVO);
	}
	
	@Override
	public void writeArticleSub(boardVO bVO) throws Exception
	{
		bDAO.writeArticleSub(bVO);
	}
	
	@Override
	public void updateSubord(boardVO bVO) throws Exception
	{
		bDAO.updateSubord(bVO);
	}
	
	@Override
	public List<boardVO> getList(int top) throws Exception 
	{
		return bDAO.getList(top);
	}
	
	@Override
	public boardVO readArticle(int anum) throws Exception
	{
		return bDAO.readArticle(anum);
	}
	
	@Override
	public void modifyArticle(boardVO bVO) throws Exception
	{
		bDAO.modifyArticle(bVO);
	}
	
	
	@Override
	public void deleteArticle(int anum) throws Exception
	{
		bDAO.deleteArticle(anum);
	}
	
	@Override
	public void increaseViews(int anum) throws Exception
	{
		bDAO.increaseViews(anum);
	}
	
	@Override
	public int getTotalRows() throws Exception
	{
		return bDAO.getTotalRows();
	}
}
