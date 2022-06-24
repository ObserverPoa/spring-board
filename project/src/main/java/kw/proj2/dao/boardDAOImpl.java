package kw.proj2.dao;

import java.util.List;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kw.proj2.vo.boardVO;

@Repository // set boardDAOImpl class as repository bean
public class boardDAOImpl implements boardDAO // board Data Access Object(DAO) implementation class
{
	@Inject // automatic dependency injection
	private SqlSession sqlSession;
	
	@Override // below method is implementing interface.
	public void writeArticleMain(boardVO bVO) throws Exception {
		sqlSession.insert("boardMapper.insertMain", bVO);
	}
	
	@Override
	public void writeArticleSub(boardVO bVO) throws Exception {
		sqlSession.insert("boardMapper.insertSub", bVO);
	}
	
	@Override
	public void updateSubord(boardVO bVO) throws Exception {
		sqlSession.update("boardMapper.update_subord", bVO);
	}
	
	@Override
	public List<boardVO> getList(int top) throws Exception {
		return sqlSession.selectList("boardMapper.getlist", top);
	}
	
	@Override
	public boardVO readArticle(int anum) throws Exception {
		return sqlSession.selectOne("boardMapper.open", anum);
	}
	
	@Override
	public void modifyArticle(boardVO bVO) throws Exception {
		sqlSession.update("boardMapper.modify", bVO);
	}
	
	@Override
	public void deleteArticle(int anum) throws Exception {
		sqlSession.delete("boardMapper.delete", anum);
	}
	
	@Override
	public void increaseViews(int anum) throws Exception {
		sqlSession.update("boardMapper.increase_views", anum);
	}
	
	@Override
	public int getTotalRows() throws Exception {
		return sqlSession.selectOne("boardMapper.get_total");
	}
}
