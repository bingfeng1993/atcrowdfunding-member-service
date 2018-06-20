package com.gwz.crowdfunding.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwz.crowdfunding.bean.Cert;
import com.gwz.crowdfunding.bean.Member;
import com.gwz.crowdfunding.bean.MemberCert;
import com.gwz.crowdfunding.bean.Ticket;
import com.gwz.crowdfunding.service.ActService;
import com.gwz.crowdfunding.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private ActService actService;
	
	@RequestMapping("/finishAuth")
	void finishAuth(@RequestBody Map<String, Object> varMap) {
		//更新会员的实名认证状态
		Member member = memberService.queryById((Integer)varMap.get("memberid"));
		member.setAuthstatus("2");
		memberService.updateAuthstatus(member);
		
		//流程继续执行
		actService.endProcess((String)varMap.get("taskid"));
		
		//更新流程审批单的状态
		Ticket t = memberService.queryTicketByMemberid((Integer)varMap.get("memberid"));
		t.setStatus("1");
		memberService.updateTicketStatus(t);
		
	}
	
	@RequestMapping("/queryMemberCertsByMemberid/{memberid}")
	List<MemberCert> queryMemberCertsByMemberid(@PathVariable("memberid")Integer memberid){
		return memberService.queryMemberCertsByMemberid(memberid);
	}
	
	@RequestMapping("/queryMemberByPiId/{piid}")
	Member queryMemberByPiId(@PathVariable("piid")String piid) {
		return memberService.queryMemberByPiId(piid);
	}
	
	@RequestMapping("/updateAuthstatus")
	public void updateAuthstatus(@RequestBody Member loginMember) {
		//更新会员实名认证状态
		memberService.updateAuthstatus(loginMember);
		//让流程继续执行
		actService.process(loginMember.getLoginacct());
	}
	
	@RequestMapping("/updateTicketAuthcode")
	public void updateTicketAuthcode(@RequestBody Ticket t) {
		memberService.updateTicketAuthcode(t);
	}
	
	@RequestMapping("/updateEmail")
	public void updateEmail(@RequestBody Member loginMember) {
		//更新邮箱地址
		memberService.updateEmail(loginMember);
		
		Ticket t = memberService.queryTicketByMemberid(loginMember.getId());
		t.setPstep("checkcode");
		//更新流程步骤，让流程继续执行
		String authcode = actService.sendMail(loginMember);
				
		t.setAuthcode(authcode);
		memberService.updateAuthcodeAndStep(t);
	}
	
	@RequestMapping("/insertMemberCerts")
	public void insertMemberCerts(@RequestBody List<MemberCert> mcs) {
		//增加会员的证明文件数据
		memberService.insertMemberCerts(mcs);
		
		Integer memberid = mcs.get(0).getMemberid();
		//获取登录账号
		Member loginMember = memberService.queryById(memberid);
		//更新流程步骤
		Ticket t = memberService.queryTicketByMemberid(memberid);
		t.setPstep("email");
		memberService.updateStep(t);
		//流程继续执行
		actService.nextProcess(loginMember.getLoginacct());
	}
	
	@RequestMapping("/queryCertsByAccountType/{accttype}")
	public List<Cert> queryCertsByAccountType(@PathVariable("accttype") String accttype){
		return memberService.queryCertsByAccountType(accttype);
	}
	
	@RequestMapping("/updateBasicInfo")
	public void updateBasicInfo(@RequestBody Member loginMember) {
		//更新会员基本信息
		memberService.updateBasicInfo(loginMember);
		//更新流程步骤
		Ticket t = memberService.queryTicketByMemberid(loginMember.getId());
		t.setPstep("cert");
		memberService.updateStep(t);
		//流程继续执行
		actService.nextProcess(loginMember.getLoginacct());
	}
	
	@RequestMapping("/updateAccountType")
	public void updateAccountType(@RequestBody Member loginMember) {
		//更新会员账户类型
		memberService.updateAccountType(loginMember);
		//更新流程步骤
		Ticket t = memberService.queryTicketByMemberid(loginMember.getId());
		t.setPstep("basicinfo");
		memberService.updateStep(t);
		//流程继续执行
		actService.process(loginMember.getLoginacct());
	}
	
	@RequestMapping("/insertTicket")
	public void insertTicket(@RequestBody Ticket t) {
		memberService.insertTicket(t);
	}
	
	@RequestMapping("/queryTicketByMemberid/{id}")
	public Ticket queryTicketByMemberid(@PathVariable("id") Integer id) {
		Ticket t = memberService.queryTicketByMemberid(id);
		return t;
	}
	
	@RequestMapping("/login/{loginacct}")
	public Object login(@PathVariable("loginacct") String loginacct) {
		
		Member member = memberService.queryMemberByLoginacct(loginacct);
		return member;
	}
}
