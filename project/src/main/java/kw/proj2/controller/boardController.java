package kw.proj2.controller;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import kw.proj2.vo.boardVO;
import kw.proj2.service.boardService;

@Controller // set boardController class as controller bean.
@RequestMapping("/") // this controller deals pages under root.
public class boardController 
{
	@Inject // automatic dependency injection
	boardService bService; // use service to control between Model and View
	
	@RequestMapping(value = "", method = RequestMethod.GET) // server's top path is root.
	public String home() throws Exception
	{
		return "redirect:/Board/list"; // use board list page as main page.
	}
	
	@RequestMapping(value = "Board/writeMain", method = RequestMethod.GET)
	public String writeMain(Model model, Integer page) throws Exception // get page number to make it possible to go back to the previous list page
	{
		model.addAttribute("page", page); // add page number to model.
		return "Board/writeMain"; // /Board/writeMain.jsp
	}
	
	@RequestMapping(value = "Board/writeSub", method = RequestMethod.GET)
	public String writeSub(boardVO bVO, Model model, Integer page) throws Exception
	{
		if (bVO == null || bVO.getAnum() == null) // null exception handling
		{
			return "redirect:/Board/list";
		}
		
		model.addAttribute("page", page);
		model.addAttribute("read", bService.readArticle(bVO.getAnum()));
		return "Board/writeSub"; // /Board/writeSub.jsp
	}
	
	@RequestMapping(value = "Board/writeArticleMain", method = RequestMethod.POST)
	public String writeArticleMain(boardVO bVO, Integer page) throws Exception
	{
		if (bVO == null || bVO.getTitle() == null || bVO.getContent() == null || bVO.getName() == null || bVO.getName().length() > 20) // null exception handling
		{
			return "redirect:/Board/list";
		}
		
		bService.writeArticleMain(bVO);
		
		String page_s = "";
		if (page != null) { page_s = page.toString(); }
		
		return "redirect:/Board/list?page=" + page_s;
	}
	
	@RequestMapping(value = "Board/writeArticleSub", method = RequestMethod.POST)
	public String writeArticleSub(boardVO bVO, Integer page) throws Exception
	{
		if (bVO == null || bVO.getTitle() == null || bVO.getContent() == null || bVO.getName() == null
				|| bVO.getMainord() == null || bVO.getSubord() == null || bVO.getDepth() == null)
		{
			return "redirect:/Board/list";
		}
		
		bVO.setSubord(bVO.getSubord() + 1); // set subord of article to be inserted as reply.
		bVO.setDepth(bVO.getDepth() + 1); // set depth of article to be inserted as reply.
		bService.updateSubord(bVO);
		bService.writeArticleSub(bVO);
		
		String page_s = "";
		if (page != null) { page_s = page.toString(); }
		
		return "redirect:/Board/list?page=" + page_s; // redirect to list page.(entered page)
	}
	
	@RequestMapping(value = "Board/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response, Model model, Integer page) throws Exception
	{
		// delete "read" cookie if exists. (to remove the record that viewed read.jsp)
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
			{
				if (cookie.getName().equals("read"))
				{
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		
		int total = bService.getTotalRows(); // total article count
		int page_max = total / 10; // max page number
		if (total % 10 > 0) { ++page_max; } // If total is not a multiple of 10, one more page is needed.
		
		if (page == null || (page < 1 || page > page_max))
		{
			page = 1;
		}
		
		int page_leftmost = ((page - 1) / 10) * 10 + 1; // lowest page of current page group
		int page_rightmost = (page_leftmost + 9 <= page_max) ? (page_leftmost + 9) : page_max; // highest page of current page group
		
		// button is disabled if ctrl_btnX is -1, else, jump to page that value is ctrl_btnX.
		int ctrl_btn1 = (page > 10) ? 1 : -1; // start page button
		int ctrl_btn2 = (page > 10) ? ((page - 1) / 10) * 10 : -1; // -10 page button
		int ctrl_btn3 = (page > 1) ? (page - 1) : -1; // -1 page button
		int ctrl_btn4 = (page < page_max) ? (page + 1) : -1; // +1 page button
		int ctrl_btn5 = (page < ((page_max - 1) / 10) * 10 + 1) ? (((page - 1) / 10) + 1) * 10 + 1 : -1; // +10 page button
		int ctrl_btn6 = (page < ((page_max - 1) / 10) * 10 + 1) ? page_max : -1; // end page button
		
		// add attributes to model.
		model.addAttribute("total", total); model.addAttribute("page", page); model.addAttribute("page_max", page_max);
		model.addAttribute("page_leftmost", page_leftmost); model.addAttribute("page_rightmost", page_rightmost);
		model.addAttribute("ctrl_btn1", ctrl_btn1); model.addAttribute("ctrl_btn2", ctrl_btn2); model.addAttribute("ctrl_btn3", ctrl_btn3);
		model.addAttribute("ctrl_btn4", ctrl_btn4); model.addAttribute("ctrl_btn5", ctrl_btn5); model.addAttribute("ctrl_btn6", ctrl_btn6);
		
		model.addAttribute("list", bService.getList(10 * page - 9)); // get article list of current page and add to model
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd(E)"); 
		String today = dtf.format(LocalDate.now());
		model.addAttribute("today", today); // add today string to determine article's write date is today or not.
		
		return "Board/list"; // /Board/list.jsp
	}
	
	@RequestMapping(value = "Board/read", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(HttpServletRequest request, HttpServletResponse response, boardVO bVO, Model model, Integer page) throws Exception
	{	
		if (bVO == null || bVO.getAnum() == null) // null exception handling
		{
			return "redirect:/Board/list";
		}
		
		// check if received "read" cookie.
		boolean exists = false;
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
			{
				if (cookie.getName().equals("read"))
				{
					exists = true;
					break;
				}
			}
		}
		
		String referer = request.getHeader("referer");
		// if "read" cookie not exist or previous page is /Board/list, increase view of current article.
		if (!exists && referer != null && Pattern.compile("/Board/list(\\?page=[0-9]*)?$").matcher(referer).find())
		{
			bService.increaseViews(bVO.getAnum());
		}
		// set "read" cookie that expires after 30 days.
		Cookie cookie = new Cookie("read", "true");
		cookie.setMaxAge(60*60*24*30);
		response.addCookie(cookie);
		
		model.addAttribute("page", page);
		model.addAttribute("read", bService.readArticle(bVO.getAnum())); // read article of anum: bVO.getAnum()
		return "Board/read"; // /Board/read.jsp
	}
	
	@RequestMapping(value = "Board/modifyArticle", method = {RequestMethod.GET, RequestMethod.POST})
	public String update(boardVO bVO, Integer page) throws Exception
	{
		if (bVO == null || bVO.getAnum() == null || bVO.getTitle() == null || bVO.getContent() == null) // null exception handling
		{
			return "redirect:/Board/list";
		}
		
		bService.modifyArticle(bVO); // modify article
		
		String page_s = "";
		if (page != null) { page_s = page.toString(); }
		
		return "redirect:/Board/list?page=" + page_s; // redirect to list page.(entered page)
	}
	
	@RequestMapping(value = "Board/deleteArticle", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(boardVO bVO, Integer page) throws Exception
	{
		if (bVO == null || bVO.getAnum() == null) // null exception handling
		{
			return "redirect:/Board/list";
		}
		
		bService.deleteArticle(bVO.getAnum()); // delete article
		
		String page_s = "";
		if (page != null) { page_s = page.toString(); }
		
		return "redirect:/Board/list?page=" + page_s; // redirect to list page.(entered page)
	}
}



