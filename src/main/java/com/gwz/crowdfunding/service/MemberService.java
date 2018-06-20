package com.gwz.crowdfunding.service;

import java.util.List;

import com.gwz.crowdfunding.bean.Cert;
import com.gwz.crowdfunding.bean.Member;
import com.gwz.crowdfunding.bean.MemberCert;
import com.gwz.crowdfunding.bean.Ticket;

public interface MemberService {

	Member queryMemberByLoginacct(String loginacct);

	Ticket queryTicketByMemberid(Integer id);

	void insertTicket(Ticket t);

	void updateAccountType(Member loginMember);

	void updateStep(Ticket t);

	void updateBasicInfo(Member loginMember);

	List<Cert> queryCertsByAccountType(String accttype);

	Member queryById(Integer memberid);

	void insertMemberCerts(List<MemberCert> mcs);

	void updateEmail(Member loginMember);

	void updateAuthcodeAndStep(Ticket t);

	void updateAuthstatus(Member loginMember);

	Member queryMemberByPiId(String piid);

	List<MemberCert> queryMemberCertsByMemberid(Integer memberid);

	void updateTicketStatus(Ticket t);

	void updateTicketAuthcode(Ticket t);

}
