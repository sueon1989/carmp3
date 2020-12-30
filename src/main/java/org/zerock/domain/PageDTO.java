package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

  private int startPage;		// 페이징의 끝 번호
  private int endPage;			// 페이징의 시작 번호
  private boolean prev, next;	// 이전, 다음

  private int total;			// 전체 게시글 숫자
  private Criteria cri;			// pageNum(페이지 번호), amount(한 페이지당 몇 개의 데이터를 보여줄 것인지)

  // 생성자 정의
  public PageDTO(Criteria cri, int total) {

    this.cri = cri;
    this.total = total;

	// 페이징의 끝 번호 계산
	endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;

    // 페이징의 시작 번호 계산
    startPage = this.endPage - 9;

    // 실제 끝 페이지 계산
    int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));
    if (realEnd <= endPage) {	// 앞서 계산한 페이징의 끝 번호(endPage)보다 작다면
      endPage = realEnd;		// 끝 번호(endPage) 갱신
    }

    //  이전
	prev = startPage > 1;		// 해당 페이지의 시작 번호(startPage)가 1보다 큰 경우 존재

    // 다음
    next = endPage < realEnd;	//  실제 끝 페이지(realEnd)가 해당 페이지의 끝 번호(endPage)보다 큰 경우에만 갱신
  }
  
}

