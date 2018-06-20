package com.gwz.crowdfunding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwz.crowdfunding.bean.Cert;
import com.gwz.crowdfunding.bean.Member;
import com.gwz.crowdfunding.bean.MemberCert;
import com.gwz.crowdfunding.bean.Ticket;
import com.gwz.crowdfunding.dao.MemberDao;
import com.gwz.crowdfunding.service.MemberService;

@Service
@Transactional(readOnly=true)
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	public Member queryMemberByLoginacct(String loginacct) {
		return memberDao.queryMemberByLoginacct(loginacct);
	}

	@Override
	public Ticket queryTicketByMemberid(Integer id) {
		return memberDao.queryTicketByMemberid(id);
	}

	@Transactional
	public void insertTicket(Ticket t) {
		memberDao.insertTicket(t);
	}

	@Transactional
	public void updateAccountType(Member loginMember) {
		memberDao.updateAccountType(loginMember);
	}

	@Transactional
	public void updateStep(Ticket t) {
		memberDao.updateStep(t);
	}

	@Transactional
	public void updateBasicInfo(Member loginMember) {
		memberDao.updateBasicInfo(loginMember);
	}

	@Override
	public List<Cert> queryCertsByAccountType(String accttype) {
		return memberDao.queryCertsByAccountType(accttype);
	}

	@Override
	public Member queryById(Integer memberid) {
		return memberDao.queryById(memberid);
	}

	@Transactional
	public void insertMemberCerts(List<MemberCert> mcs) {
			memberDao.insertMemberCerts(mcs);
	}

	@Transactional
	public void updateEmail(Member loginMember) {
		memberDao.updateEmail(loginMember);
		
	}

	@Transactional
	public void updateAuthcodeAndStep(Ticket t) {
		memberDao.updateAuthcodeAndStep(t);
	}

	@Transactional
	public void updateAuthstatus(Member loginMember) {
		memberDao.updateAuthstatus(loginMember);
	}

	@Override
	public Member queryMemberByPiId(String piid) {
		return memberDao.queryMemberByPiId(piid);
	}

	@Override
	public List<MemberCert> queryMemberCertsByMemberid(Integer memberid) {
		return memberDao.queryMemberCertsByMemberid(memberid);
	}

	@Override
	public void updateTicketStatus(Ticket t) {
		memberDao.updateTicketStatus(t);
	}

	@Override
	public void updateTicketAuthcode(Ticket t) {
		memberDao.updateTicketAuthcode(t);
	}
}
