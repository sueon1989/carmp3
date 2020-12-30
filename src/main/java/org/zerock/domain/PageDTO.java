package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

  private int startPage;		// ����¡�� �� ��ȣ
  private int endPage;			// ����¡�� ���� ��ȣ
  private boolean prev, next;	// ����, ����

  private int total;			// ��ü �Խñ� ����
  private Criteria cri;			// pageNum(������ ��ȣ), amount(�� �������� �� ���� �����͸� ������ ������)

  // ������ ����
  public PageDTO(Criteria cri, int total) {

    this.cri = cri;
    this.total = total;

	// ����¡�� �� ��ȣ ���
	endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;

    // ����¡�� ���� ��ȣ ���
    startPage = this.endPage - 9;

    // ���� �� ������ ���
    int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));
    if (realEnd <= endPage) {	// �ռ� ����� ����¡�� �� ��ȣ(endPage)���� �۴ٸ�
      endPage = realEnd;		// �� ��ȣ(endPage) ����
    }

    //  ����
	prev = startPage > 1;		// �ش� �������� ���� ��ȣ(startPage)�� 1���� ū ��� ����

    // ����
    next = endPage < realEnd;	//  ���� �� ������(realEnd)�� �ش� �������� �� ��ȣ(endPage)���� ū ��쿡�� ����
  }
  
}

