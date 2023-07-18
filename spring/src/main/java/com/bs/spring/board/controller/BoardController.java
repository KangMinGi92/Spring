package com.bs.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.service.BoardService;
import com.bs.spring.common.PageFactory;
import com.bs.spring.member.model.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

	private BoardService service;
	
	public BoardController(BoardService service) {
		this.service=service;
	}
	
	@RequestMapping("/boardList.do")
	public String selectBoardAll(@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="5") int numPerpage,
			Model m,HttpServletRequest request) {
		//페이지에 맞는 데이터를 가져와야함.
		List<Board> boards=service.selectBoardAll(Map.of("cPage",cPage,"numPerpage",numPerpage));
		int totalData=service.selectBoardAllCount();
		//paging
		m.addAttribute("pageBar",PageFactory.getPage(cPage, numPerpage, totalData,"boardList.do"));
		m.addAttribute("totalContents",totalData);
		m.addAttribute("boards",boards);
		return "board/boardList";
	}
	@RequestMapping("/boardForm.do")
	public String insertBoardpage(Board b){
		return "board/boardForm";
	}
	
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(Member member, Board b, Model m,MultipartFile[] upFile,HttpSession session) {
		//첨부파일이 1개일땐 MultipartFile upFile
		//첨부파일이 2개이상일땐 MultipartFile[] upFile 배열로 받아온다.
		b.setBoardWriter(member);
		System.out.println("이거 뭐니?? :  "+b.getBoardWriter());
		log.info("{}",b);
		//log.info("{}",upFile);

		//MultipartFile에서 제공하는 메소드를 이용해서
		//파일을 저장할 수 있음 -> transferTo()메소드를 이용
		//절대경로 가져오기
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		//파일명에 대한 renamed규칙을 설정
		//직접rename 규칙을 만들어서 저장해보자.
		//getOriginalFilename을 받아오는 이유는 끝에 붙은 확장자를 얻어오기 위해서 저장한다.
		//yyyyMMdd_HHmmssSSS_랜덤값.확장자
		
		//첨부파일이 1개일경우
//		String oriName=upFile.getOriginalFilename();
//		String ext=oriName.substring(oriName.lastIndexOf("."));
//		Date today=new Date(System.currentTimeMillis());
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
//		int rdn=(int)(Math.random()*10000)+1;
//		String rename=sdf.format(today)+"_"+rdn+ext;
//		try {
//			upFile.transferTo(new File(path+rename));
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		Attachment file=Attachment.builder().originalFilename(oriName).renamedFilename(rename).build();
//		b.setFile(file);
//		List<Attachment> files=new ArrayList();
		//첨부파일이 여러개일 경우
		if(upFile!=null) {
			for(MultipartFile mf: upFile) {
				if(!mf.isEmpty()) {
					String oriName=mf.getOriginalFilename();
					String ext=oriName.substring(oriName.lastIndexOf("."));
					Date today=new Date(System.currentTimeMillis());
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
					int rdn=(int)(Math.random()*10000)+1;
					String rename=sdf.format(today)+"_"+rdn+ext;
					try {
						mf.transferTo(new File(path+rename));
					}catch(IOException e) {
						e.printStackTrace();
					}
					Attachment file=Attachment.builder().originalFilename(oriName).renamedFilename(rename).build();
					b.getFile().add(file);
				}
			}
		}
		try {
			service.insertBoard(b);
		}catch(RuntimeException e) {
			for(Attachment a: b.getFile()) {
				File delFile=new File(path+a.getRenamedFilename());
				delFile.delete();
			}
			m.addAttribute("msg","글쓰기 등록 실패! :p");
			m.addAttribute("loc","/board/boardForm.do");
			return "common/msg";
		}
		return "redirect:/board/boardList.do";

	}
	
	@RequestMapping("/boardView.do")
	public String selectBoardByNo(Model m,int no){
		m.addAttribute("board",service.selectBoardByNo(no));
		return "board/boardView";
	}
	
	@RequestMapping("/filedownload")
	public void fileDown(String oriname, String rename, OutputStream out,
			@RequestHeader(value="user-agent")String header, 
			HttpSession session,HttpServletResponse res) {
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		File downloadFile=new File(path+rename);
		try(FileInputStream fis=new FileInputStream(downloadFile);
				BufferedInputStream bis=new BufferedInputStream(fis);
				BufferedOutputStream bos=new BufferedOutputStream(out)) {
			boolean isMs=header.contains("Trident")||header.contains("MSIE");
			String ecodeRename="";
			if(isMs){
				ecodeRename=URLEncoder.encode(oriname,"UTF-8");
				ecodeRename=ecodeRename.replaceAll("\\+","%20");
			}else {
				ecodeRename=new String(oriname.getBytes("UTF-8"),"ISO-8859-1");
			}
			res.setContentType("application/octet-stream;charset=utf-8");
			res.setHeader("Content-Disposition","attachment;filename=\""+ecodeRename+"\"");
			int read=01;
			while((read=bis.read())!=-1) {
				bos.write(read);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
