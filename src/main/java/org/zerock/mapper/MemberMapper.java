package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.MemberVO;

public interface MemberMapper {
	
	@Select("select * from member where member_no>0")
    public List<MemberVO> getList3();
    
    // ¸â¹ö µî·Ï
//    public void insert(MemberVO member);
}
