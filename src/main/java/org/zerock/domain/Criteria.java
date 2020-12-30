package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@ToString
public class Criteria {	// 검색의 기준

  private int pageNum;	// 페이지 번호
  private int amount;	// 한 페이지당 몇 개의 데이터를 보여줄 것인지
//  private int startNum;	// db에서 limit의 시작값
  
//  private String type;
//  private String keyword;

  	public Criteria() {		// 1방법) 기본 초기값을 주던지
  		this(1, 10);		// 기본값 1페이지, 10개
  	}

  	public Criteria(int pageNum, int amount) {	// 2방법) 초기값을 주던지
  		this.pageNum = pageNum;
  		this.amount = amount;
  	}

	public int getPageNum() {
		return pageNum;
	}
	
  	// BoardMapper.xml에서 가져올 때 자동으로 수정해서 가져오기
	public int getStartNum() {
		return (pageNum-1)*amount;	// db에서 limit의 시작값 계산
		// ex> 10개씩 보여줄 때 3페이지를 보고 싶다면 limit 20,10
	}
	
	public int getAmount() {
		return amount;
	}
  
  
  
//  public String[] getTypeArr() {
//    
//    return type == null? new String[] {}: type.split("");
//  }
}
