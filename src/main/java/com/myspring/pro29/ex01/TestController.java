package com.myspring.pro29.ex01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//<!-- @RestController����ϱ����� pom.xml���� ������ 4.1.1�� ���׷��̵� -->
@RestController
//@RequestMapping("/test/*")
public class TestController {

	 static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/mapping")
	String hoho(){
		return "�ش� Ŭ������ ���ΰ��� test/* �� ���� �ν��ؾ��ϱ⿡  http://localhost:8080/pro29/test/mapping�� ��û";
		
	}
	
	@RequestMapping("/member")
	public MemberVO member(){
		MemberVO member=new MemberVO();
		member.setId("hong");
		member.setPwd("1234");
		member.setName("ȫ�浿");
		member.setEmail("j_ar@naver.com");
		return member;
	}
	@RequestMapping("/membersList")
	public List<MemberVO> listMembers(){
		List<MemberVO> list= new ArrayList<MemberVO>();
		for(int i=0;i<10;i++) {
			MemberVO vo=new MemberVO();
			vo.setId("hong"+i);
			vo.setPwd("1234");
			vo.setName("ȫ�浿"+i);
			vo.setEmail("j_ar@naver.com"+i);
			
			list.add(vo);
		}
		return list;
	}
	
	@RequestMapping("/membersMap")
	public Map<Integer, MemberVO> membersMap(){
		Map<Integer, MemberVO> map= new HashMap<Integer ,MemberVO>();
		for(int i=0;i<10;i++) {
			MemberVO vo=new MemberVO();
			vo.setId("hong"+i);
			vo.setPwd("1234");
			vo.setName("ȫ�浿"+i);
			vo.setEmail("j_ar@naver.com"+i);
			
			map.put(i,vo);
		}
		return map;
		
		
	}
	@RequestMapping(value = "/notice/{num}",method = RequestMethod.GET)
	public String notice(@PathVariable(value = "num")String num)throws Exception{
		return num;
	}
	
	@RequestMapping(value = "/notice1/{num}",method = RequestMethod.GET)
	public int notice1(@PathVariable(value = "num")int num)throws Exception{
		return num;	
	}
	
	@RequestMapping(value="/info",method = RequestMethod.POST)
	void modify(@RequestBody MemberVO vo){
		logger.info(vo.toString());
	}
	
	//@RestController�� ������ �並 �������� ���� ä �����͸� �����ϹǷ� ���ް������� ���ܰ� �߻��� �� �ִ�
	//���ܿ� ���� �� �� ������ ��� �ʿ��� ��� ResponseEntityŬ���� ���
	//ResponseEntity�� HTTP�����ڵ带 �����Ͽ� ������ �� �ִ�
	//�ۿ��� HTTP���� �ڵ带 �ν��� �� �ִ� ����� �̿��� �ֹ� ���³� ���� �߻��� �˷���
	 @RequestMapping("/membersList2")
	  public  ResponseEntity<List<MemberVO>> listMembers2() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		for (int i = 0; i < 10; i++) {
		  MemberVO vo = new MemberVO();
		  vo.setId("lee" + i);
		  vo.setPwd("123"+i);
		  vo.setName("�̼���" + i);
	      vo.setEmail("lee"+i+"@test.com");
		  list.add(vo);
		}
	    return new ResponseEntity(list,HttpStatus.INTERNAL_SERVER_ERROR);
	  }	
	  
	  //ResponseEntity�� �̿��ϸ� json�Ӹ� �ƴ϶� html�̳� js�� �������� ������ �� �־�
	 //��� �޽����� �����޽����� ������ �� ����
		@RequestMapping(value = "/res3")
		public ResponseEntity res3() {
			HttpHeaders responseHeaders = new HttpHeaders();
		    responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		    String message = "<script>";
			message += " alert('�� ȸ���� ����մϴ�.');";
			message += " location.href='/pro29/test/membersList2'; ";
			message += " </script>";
			return  new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		
	
	
}
