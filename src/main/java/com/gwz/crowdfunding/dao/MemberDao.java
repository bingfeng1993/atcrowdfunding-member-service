package com.gwz.crowdfunding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gwz.crowdfunding.bean.Cert;
import com.gwz.crowdfunding.bean.Member;
import com.gwz.crowdfunding.bean.MemberCert;
import com.gwz.crowdfunding.bean.Ticket;

public interface MemberDao {

	@Select("select * from t_member where loginacct = #{loginacct}")
	Member queryMemberByLoginacct(String loginacct);

	@Select("select * from t_ticket where memberid = #{memberid} and status = '0'")
	Ticket queryTicketByMemberid(Integer id);

	void insertTicket(Ticket t);

	void updateAccountType(Member loginMember);

	void updateStep(Ticket t);

	void updateBasicInfo(Member loginMember);

	List<Cert> queryCertsByAccountType(String accttype);

	@Select("select * from t_member where id = #{id}")
	Member queryById(Integer memberid);

	void insertMemberCerts(@Param("mcs") List<MemberCert> mcs);

	@Update("update t_member set email = #{email} where id = #{id}")
	void updateEmail(Member loginMember);

	void updateAuthcodeAndStep(Ticket t);
	
	@Update("update t_member set authstatus = #{authstatus} where id = #{id}")
	void updateAuthstatus(Member loginMember);

	Member queryMemberByPiId(String piid);

	List<MemberCert> queryMemberCertsByMemberid(Integer memberid);

	@Update("update t_ticket set status = #{status} where id = #{id}")
	void updateTicketStatus(Ticket t);

	@Update("update t_ticket set authcode = #{authcode} where id = #{id}")
	void updateTicketAuthcode(Ticket t);

}
